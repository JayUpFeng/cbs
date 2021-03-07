package com.jinjike.cbs.service;

import com.jinjike.cbs.model.MediaConfig;
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
public interface IMediaConfigService extends IService<MediaConfig> {

    MediaConfig createMediaConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate);
}
