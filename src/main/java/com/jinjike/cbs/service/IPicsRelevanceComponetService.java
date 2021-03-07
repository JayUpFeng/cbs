package com.jinjike.cbs.service;

import com.jinjike.cbs.model.PicsRelevanceComponet;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface IPicsRelevanceComponetService extends IService<PicsRelevanceComponet> {

    PicsRelevanceComponet savePic(Map<String, Object> picsRelevanceComponetMap, Integer valuationDataId,boolean isUpdate);
}
