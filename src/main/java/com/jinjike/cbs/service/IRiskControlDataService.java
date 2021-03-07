package com.jinjike.cbs.service;

import com.jinjike.cbs.model.RiskControlData;
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
public interface IRiskControlDataService extends IService<RiskControlData> {

    RiskControlData createRiskControlData(String orderNo, Map<String, Object> riskControlDataMap,boolean isUpdate);
}
