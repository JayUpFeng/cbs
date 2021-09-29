package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.mapper.RiskControlDataMapper;
import com.jinjike.cbs.mapper.ValuationDataMapper;
import com.jinjike.cbs.model.BasicInfo;
import com.jinjike.cbs.mapper.BasicInfoMapper;
import com.jinjike.cbs.model.RiskControlData;
import com.jinjike.cbs.model.ValuationData;
import com.jinjike.cbs.service.IBasicInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
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
public class BasicInfoServiceImpl extends ServiceImpl<BasicInfoMapper, BasicInfo> implements IBasicInfoService {

    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private ValuationDataMapper valuationDataMapper;
    @Autowired
    private RiskControlDataMapper riskControlDataMapper;
    @Override
    public BasicInfo createBasicInfo(Map<String, Object> detectionDataMap, Integer detectionDataId,boolean isUpdate,String orderNo,String dealerBuyPrice) {
        Map<String, Object> basicInfoMap = (Map<String, Object>) detectionDataMap.get("basicInfo");
        if (basicInfoMap != null) {
            BasicInfo basicInfo = new BasicInfo();
            if (isUpdate){
                Integer id = (Integer) basicInfoMap.get("id");
                basicInfo.setId(id);
                detectionDataId = (Integer) basicInfoMap.get("carModelDataId");
                String grade = (String) basicInfoMap.get("grade");
                basicInfo.setGrade(grade);
            }
            basicInfo.setDetectionDataId(detectionDataId);
            basicInfo.setOrderNo(orderNo);
            basicInfo.setDealerBuyPrice(dealerBuyPrice);
            String vin = (String) basicInfoMap.get("vin");
            String modelName = (String) basicInfoMap.get("modelName");
            String plateYear = (String) basicInfoMap.get("plateYear");
            BigDecimal watchMile = (BigDecimal) basicInfoMap.get("watchMile");
            String leftPicture = (String) basicInfoMap.get("leftPicture");
            String vinPicture = (String) basicInfoMap.get("vinPicture");
            String milePicture = (String) basicInfoMap.get("milePicture");
            String carLevel = (String) basicInfoMap.get("carLevel");
            String evaluateContent = (String) basicInfoMap.get("evaluateContent");
            String carNo = (String) basicInfoMap.get("carNo");
            String engineNo = (String) basicInfoMap.get("engineNo");
            //新增字段
            String extend = (String) basicInfoMap.get("extend");
            String carScore = (String) basicInfoMap.get("carScore");
            String importantScore = (String) basicInfoMap.get("importantScore");
            String repairScore = (String) basicInfoMap.get("repairScore");
            String reportUrl = (String) basicInfoMap.get("reportUrl");

            basicInfo.setVin(vin);
            basicInfo.setModelName(modelName);
            basicInfo.setPlateYear(plateYear);
            basicInfo.setWatchMile(watchMile);
            basicInfo.setLeftPicture(leftPicture);
            basicInfo.setVinPicture(vinPicture);
            basicInfo.setMilePicture(milePicture);
            basicInfo.setCarLevel(carLevel);
            basicInfo.setEvaluateContent(evaluateContent);
            basicInfo.setCarNo(carNo);
            basicInfo.setEngineNo(engineNo);
            //新增字段
            basicInfo.setExtend(extend);
            basicInfo.setCarScore(carScore);
            basicInfo.setImportantScore(importantScore);
            basicInfo.setRepairScore(repairScore);
            basicInfo.setReportUrl(reportUrl);
            //获取carLevel，如果为ABCDE则，赋值给grade
            String upperCase = carLevel.toUpperCase();
            if ("ABCDE".contains(upperCase)){
                basicInfo.setGrade(upperCase);
            }
            basicInfo.setCreateTime(new Date());
            save(basicInfo);
            return basicInfo;
        }
        return null;
    }

    @Override
    public IPage<BasicInfo> pageList(Page<BasicInfo> objectPage, String orderNo, String vin) {
        return basicInfoMapper.pageList(objectPage,orderNo,vin);
    }
    @Override
    @Transactional
    public ResponseData updateBasicInfo(String json) {
        Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(json);
        if (parse != null) {
            String orderNo = (String) parse.get("orderNo");
            if (StringUtils.isNoneBlank(orderNo)) {
                //修改basicInfo数据
                String grade = (String) parse.get("grade");
                String dealerBuyPrice = (String) parse.get("dealerBuyPrice");
                Object watch =  parse.get("watchMile");
                String modelName = (String) parse.get("modelName");
                BigDecimal watchMile=null;
                if (watch instanceof Integer){
                    watchMile=new BigDecimal(Integer.parseInt(watch.toString()));
                }
                if (watch instanceof BigDecimal){
                    watchMile=(BigDecimal)watch;
                }
                Integer isStart = (Integer) parse.get("isStart");
                BasicInfo basicInfo = getOne(new LambdaQueryWrapper<BasicInfo>().eq(BasicInfo::getOrderNo, orderNo));
                if (basicInfo != null) {
                    if (StringUtils.isNoneBlank(grade)) {
                        basicInfo.setGrade(grade);
                    }
                    if (StringUtils.isNoneBlank(dealerBuyPrice)) {
                        basicInfo.setDealerBuyPrice(dealerBuyPrice);
                    }
                    if (StringUtils.isNoneBlank(modelName)) {
                        basicInfo.setModelName(modelName);
                    }
                    if (watchMile!=null){
                        basicInfo.setWatchMile(watchMile);
                    }
                    if (isStart!=null){
                        basicInfo.setIsStart(isStart);
                    }
                    updateById(basicInfo);
                }
                ValuationData valuationData = valuationDataMapper.selectOne(new LambdaQueryWrapper<ValuationData>().eq(ValuationData::getOrderNo, orderNo));
                if (valuationData != null) {
                    String carLicenseLocation = (String) parse.get("carLicenseLocation");
                    if (StringUtils.isNoneBlank(carLicenseLocation)) {
                        valuationData.setCarLicenseLocation(carLicenseLocation);
                    }
                    String carColor = (String) parse.get("carColor");
                    if (StringUtils.isNoneBlank(carColor)) {
                        valuationData.setCarColor(carColor);
                    }
                    String firstPlateTime = (String) parse.get("firstPlateTime");
                    if (StringUtils.isNoneBlank(firstPlateTime)) {
                        valuationData.setFirstPlateTime(firstPlateTime);
                    }
                    valuationDataMapper.updateById(valuationData);
                }
                //修改riskcontrolData数据
                RiskControlData riskControlData = riskControlDataMapper.selectOne(new LambdaQueryWrapper<RiskControlData>().eq(RiskControlData::getOrderNo, orderNo));
                if (riskControlData!=null){
                    Integer syxz = (Integer) parse.get("syxz");
                    if (syxz!=null){
                        riskControlData.setSyxz(syxz);
                    }
                    riskControlDataMapper.updateById(riskControlData);
                }
            }
        }
        return ResponseData.success();
    }
}
