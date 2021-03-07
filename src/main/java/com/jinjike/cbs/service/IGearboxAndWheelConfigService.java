package com.jinjike.cbs.service;

import com.jinjike.cbs.model.GearboxAndWheelConfig;
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
public interface IGearboxAndWheelConfigService extends IService<GearboxAndWheelConfig> {

    GearboxAndWheelConfig createGearboxAndWheelConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate);
}
