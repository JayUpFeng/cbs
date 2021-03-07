package com.jinjike.cbs.service;

import com.jinjike.cbs.model.ValuationData;
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
public interface IValuationDataService extends IService<ValuationData> {

    ValuationData createValuationData(String orderNo, Map<String, Object> valuationDataMap,boolean isUpdate);
}
