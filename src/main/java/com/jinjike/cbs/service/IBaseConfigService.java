package com.jinjike.cbs.service;

import com.jinjike.cbs.model.BaseConfig;
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
public interface IBaseConfigService extends IService<BaseConfig> {

    BaseConfig createBaseConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate);
}
