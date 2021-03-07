package com.jinjike.cbs.service;

import com.jinjike.cbs.model.CarBodyConfig;
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
public interface ICarBodyConfigService extends IService<CarBodyConfig> {

    CarBodyConfig createCarBodyConfig(Map<String, Object> carModelDataMap, Integer carModelDataId,boolean isUpdate);
}
