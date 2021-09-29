package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinjike.cbs.mapper.CoordinateMapper;
import com.jinjike.cbs.mapper.DataInfoMapper;
import com.jinjike.cbs.mapper.DescsMapper;
import com.jinjike.cbs.mapper.DetectionDataMapper;
import com.jinjike.cbs.model.Coordinate;
import com.jinjike.cbs.model.DataInfo;
import com.jinjike.cbs.model.Descs;
import com.jinjike.cbs.model.DetectionData;
import com.jinjike.cbs.service.IDataInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
@Service
public class DataInfoServiceImpl extends ServiceImpl<DataInfoMapper, DataInfo> implements IDataInfoService {

    @Autowired
    private DescsMapper descsMapper;
    @Autowired
    private CoordinateMapper coordinateMapper;
    @Autowired
    private DetectionDataMapper detectionDataMapper;
    @Autowired
    private DataInfoMapper dataInfoMapper;

    @Override
    public List<DataInfo> createDataInfo(Map<String, Object> detectionDataMap, Integer detectionDataId, boolean isUpdate) {
        JSONArray jsonArray = (JSONArray) detectionDataMap.get("data");
        if (jsonArray != null) {
            List<DataInfo> dataList = new ArrayList<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) jsonArray.get(i);
                DataInfo dataInfo = new DataInfo();
                if (isUpdate) {
                    Integer id = (Integer) map.get("id");
                    dataInfo.setId(id);
                    detectionDataId = (Integer) map.get("carModelDataId");
                }
                dataInfo.setDetectionDataId(detectionDataId);
                Integer componmentId = (Integer) map.get("componmentId");
                String nameComponment = (String) map.get("nameComponment");
                String nameGroup = (String) map.get("nameGroup");
                //新增字段
                String groupId = (String) map.get("groupId");
                String pic = (String) map.get("picture");
                String firstSysName = (String) map.get("firstSysName");
                String secondSysName = (String) map.get("secondSysName");
                Integer type = (Integer) map.get("type");
                JSONArray qmInfosArray = (JSONArray) map.get("qmInfos");
                if (qmInfosArray != null && qmInfosArray.size() > 0) {
                    List<String> list = new ArrayList<>();
                    for (int j = 0; j < qmInfosArray.size(); j++) {
                        String qmInfo = (String) qmInfosArray.get(j);
                        list.add(qmInfo);
                    }
                    String jsonString = JSONObject.toJSONString(list);
                    dataInfo.setQmInfos(jsonString);
                }
                dataInfo.setComponmentId(componmentId);
                dataInfo.setNameComponment(nameComponment);
                dataInfo.setNameGroup(nameGroup);
                //新增字段
                dataInfo.setGroupId(groupId);
                dataInfo.setPicture(pic);
                dataInfo.setFirstSysName(firstSysName);
                dataInfo.setSecondSysName(secondSysName);
                dataInfo.setType(type);
                save(dataInfo);
                Integer dataId = dataInfo.getId();
                JSONArray descs = (JSONArray) map.get("descs");
                List<Descs> descsList = new ArrayList<>();
                if (descs != null && descs.size() > 0) {
                    for (int j = 0; j < descs.size(); j++) {
                        Map<String, Object> mapInfo = (Map<String, Object>) descs.get(j);
                        if (mapInfo != null) {
                            Descs des = new Descs();
                            if (isUpdate) {
                                Integer id = (Integer) mapInfo.get("id");
                                des.setId(id);
                                dataId = (Integer) mapInfo.get("dataId");
                            }
                            des.setDataId(dataId);
                            String picture = (String) mapInfo.get("picture");
                            String description = (String) mapInfo.get("description");
                            des.setPicture(picture);
                            des.setDescription(description);
                            descsMapper.insert(des);
                            Integer desId = des.getId();
                            JSONArray coordinate = (JSONArray) mapInfo.get("coordinate");
                            List<Coordinate> coordinateList = new ArrayList<>();
                            if (coordinate != null && coordinate.size() > 0) {
                                for (int k = 0; k < coordinate.size(); k++) {
                                    Map<String, Object> mapCo = (Map<String, Object>) coordinate.get(k);
                                    if (mapCo != null) {
                                        Coordinate co = new Coordinate();
                                        if (isUpdate) {
                                            Integer id = (Integer) mapCo.get("id");
                                            co.setId(id);
                                            desId = (Integer) mapCo.get("descId");
                                        }
                                        co.setDescId(desId);
                                        BigDecimal data = (BigDecimal) mapCo.get("data");
                                        Integer left = (Integer) mapCo.get("left");
                                        Integer top = (Integer) mapCo.get("top");
                                        String name = (String) mapCo.get("name");
                                        co.setData(data);
                                        co.setLefts(left);
                                        co.setTop(top);
                                        co.setName(name);
                                        coordinateMapper.insert(co);
                                        coordinateList.add(co);
                                    }
                                }
                            }
                            des.setCoordinate(coordinateList);
                            descsList.add(des);
                        }
                    }
                }
                dataInfo.setDescs(descsList);
                dataList.add(dataInfo);
            }
            return dataList;
        }
        return null;
    }

    @Override
    public void delPic(String json) {
        if (StringUtils.isNoneBlank(json)){
            Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(json);
            String orderNo = (String) parse.get("orderNo");
            if (StringUtils.isNoneBlank(orderNo)){
                Integer id = (Integer) parse.get("id");
                String delPicId = (String) parse.get("delPicId");
                DetectionData detectionData = detectionDataMapper.selectOne(new LambdaQueryWrapper<DetectionData>().select(DetectionData::getId).eq(DetectionData::getOrderNo, orderNo));
                if(detectionData!=null){
                    Integer detectionDataId = detectionData.getId();
                    DataInfo dataInfo = dataInfoMapper.selectOne(new LambdaQueryWrapper<DataInfo>().eq(DataInfo::getDetectionDataId, detectionDataId).eq(DataInfo::getId,id));
                    if (dataInfo!=null){
                        String picture = dataInfo.getPicture();
                        if (StringUtils.isNoneBlank(picture)){
                            List<String> list = Arrays.asList(picture.split(","));
                            List<String> resultList=new ArrayList<>();
                            for(String pic:list){
                                if (!pic.equals(delPicId)){
                                    resultList.add(pic);
                                }
                            }
                            String joinPic = String.join(",", resultList);
                            dataInfo.setPicture(joinPic);
                            dataInfoMapper.updateById(dataInfo);
                        }
                    }
                }
            }
        }
    }
}
