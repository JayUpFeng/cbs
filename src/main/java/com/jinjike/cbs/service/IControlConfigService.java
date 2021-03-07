package com.jinjike.cbs.service;

import com.jinjike.cbs.model.ControlConfig;
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
public interface IControlConfigService extends IService<ControlConfig> {

    ControlConfig createControlConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate);
}
