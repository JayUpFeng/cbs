package com.jinjike.cbs.service.impl;

import com.jinjike.cbs.model.PicsRelevanceComponet;
import com.jinjike.cbs.mapper.PicsRelevanceComponetMapper;
import com.jinjike.cbs.service.IPicsRelevanceComponetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class PicsRelevanceComponetServiceImpl extends ServiceImpl<PicsRelevanceComponetMapper, PicsRelevanceComponet> implements IPicsRelevanceComponetService {

    @Override
    public PicsRelevanceComponet savePic(Map<String, Object> picsRelevanceComponetMap, Integer valuationDataId,boolean isUpdate) {
        if (picsRelevanceComponetMap != null) {
            PicsRelevanceComponet componet = new PicsRelevanceComponet();
            if (isUpdate){
                Integer id = (Integer) picsRelevanceComponetMap.get("id");
                componet.setId(id);
                valuationDataId = (Integer) picsRelevanceComponetMap.get("valuationDataId");
            }
            componet.setValuationDataId(valuationDataId);
            String componentIds = (String) picsRelevanceComponetMap.get("componentIds");
            String url = (String) picsRelevanceComponetMap.get("url");
            componet.setComponentIds(componentIds);
            componet.setUrl(url);
            save(componet);
            return componet;
        }
        return null;
    }
}
