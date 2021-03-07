package com.jinjike.cbs.service;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jinjike.cbs.model.BaseConfig;
import com.jinjike.cbs.model.RepairRecords;

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
public interface IRepairRecordsService extends IService<RepairRecords> {

    List<RepairRecords> saveRecords(JSONArray normalRepairRecords, Integer maintenanceDataId,boolean isUpdate,Integer kind);
}
