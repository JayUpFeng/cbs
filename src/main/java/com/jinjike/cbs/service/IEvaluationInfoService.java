package com.jinjike.cbs.service;

import com.jinjike.cbs.model.EvaluationInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-26
 */
public interface IEvaluationInfoService extends IService<EvaluationInfo> {
    EvaluationInfo createEvaluationInfo(Map<String, Object> detectionDataMap, Integer detectionDataId,boolean isUpdate);
}
