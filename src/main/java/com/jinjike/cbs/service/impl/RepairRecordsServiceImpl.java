package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinjike.cbs.mapper.BaseConfigMapper;
import com.jinjike.cbs.mapper.RepairRecordsMapper;
import com.jinjike.cbs.model.BaseConfig;
import com.jinjike.cbs.model.FilingPics;
import com.jinjike.cbs.model.RepairRecords;
import com.jinjike.cbs.service.IBaseConfigService;
import com.jinjike.cbs.service.IRepairRecordsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
@Service
public class RepairRecordsServiceImpl extends ServiceImpl<RepairRecordsMapper, RepairRecords> implements IRepairRecordsService {

    @Override
    public List<RepairRecords> saveRecords(JSONArray repairRecords, Integer maintenanceDataId, boolean isUpdate, Integer kind) {
        if (repairRecords != null) {
            List<RepairRecords> list = new ArrayList<>();
            for (int i = 0; i < repairRecords.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) repairRecords.get(i);
                if (map != null) {
                    RepairRecords records = new RepairRecords();
                    if (isUpdate) {
                        Integer id = (Integer) map.get("id");
                        records.setId(id);
                        maintenanceDataId = (Integer) map.get("maintenanceDataId");
                    }
                    records.setMaintenanceDataId(maintenanceDataId);
                    String type = (String) map.get("type");
                    String content = (String) map.get("content");
                    String date = (String) map.get("date");
                    Long mainTainDate = (Long) map.get("mainTainDate");
                    String materal = (String) map.get("materal");
                    Integer mileage = (Integer) map.get("mileage");
                    String payType = (String) map.get("payType");
                    String remark = (String) map.get("remark");
                    BigDecimal personSoldPrice = (BigDecimal) map.get("personSoldPrice");
                    BigDecimal personSoldPriceMin = (BigDecimal) map.get("personSoldPriceMin");
                    BigDecimal personSoldPriceMax = (BigDecimal) map.get("personSoldPriceMax");
                    records.setType(type);
                    records.setContent(content);
                    records.setDate(date);
                    records.setMainTainDate(mainTainDate);
                    records.setMateral(materal);
                    records.setMileage(mileage);
                    records.setPayType(payType);
                    records.setRemark(remark);
                    records.setPersonSoldPrice(personSoldPrice);
                    records.setPersonSoldPriceMax(personSoldPriceMax);
                    records.setPersonSoldPriceMin(personSoldPriceMin);
                    if (kind == 0) {
                        records.setKind(0);
                    } else if (kind == 1) {
                        records.setKind(1);
                    } else if (kind == 2) {
                        records.setKind(2);
                    } else if (kind == 3) {
                        records.setKind(3);
                    }
                    list.add(records);
                }
            }
            saveBatch(list);
            return list;
        }
        return null;
    }
}
