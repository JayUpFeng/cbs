package com.jinjike.cbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.model.MaintenanceData;
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
public interface IMaintenanceDataService extends IService<MaintenanceData> {

    MaintenanceData createMaintenanceData(String orderNo, Map<String, Object> maintenanceDataMap,boolean isUpdate);

    IPage<MaintenanceData> pageList(Page<MaintenanceData> page,String orderNo,String vin);
}
