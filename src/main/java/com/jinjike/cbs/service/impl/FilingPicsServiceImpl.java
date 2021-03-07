package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jinjike.cbs.model.FilingPics;
import com.jinjike.cbs.mapper.FilingPicsMapper;
import com.jinjike.cbs.service.IFilingPicsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class FilingPicsServiceImpl extends ServiceImpl<FilingPicsMapper, FilingPics> implements IFilingPicsService {

    @Override
    public List<FilingPics> saveFilingPics(JSONArray filingPics, Integer valuationDataId,boolean isUpdate) {
        if (filingPics != null) {
            List<FilingPics> list = new ArrayList<>();
            for (int i = 0; i < filingPics.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) filingPics.get(i);
                if (map != null) {
                    FilingPics pics = new FilingPics();
                    if (isUpdate){
                        Integer id = (Integer) map.get("id");
                        pics.setId(id);
                        valuationDataId = (Integer) map.get("valuationDataId");
                    }
                    pics.setValuationDataId(valuationDataId);
                    String name = (String) map.get("name");
                    String imgUrl = (String) map.get("imgUrl");
                    //新增字段
                    Integer picId = (Integer) map.get("picId");
                    pics.setName(name);
                    pics.setImgUrl(imgUrl);
                    //新增字段
                    pics.setPicId(picId);
                    list.add(pics);
                }
            }
            saveBatch(list);
            return list;
        }
        return null;
    }
}
