package com.jinjike.cbs.service;

import com.jinjike.cbs.model.DataInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface IDataInfoService extends IService<DataInfo> {

    List<DataInfo> createDataInfo(Map<String, Object> detectionDataMap, Integer detectionDataId, boolean isUpdate);
}
