package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.model.MaintenanceData;
import com.jinjike.cbs.mapper.MaintenanceDataMapper;
import com.jinjike.cbs.model.RepairRecords;
import com.jinjike.cbs.service.IMaintenanceDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinjike.cbs.service.IRepairRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class MaintenanceDataServiceImpl extends ServiceImpl<MaintenanceDataMapper, MaintenanceData> implements IMaintenanceDataService {
    @Autowired
    private IRepairRecordsService repairRecordsService;

    @Autowired
    private MaintenanceDataMapper maintenanceDataMapper;

    @Override
    public MaintenanceData createMaintenanceData(String orderNo, Map<String, Object> maintenanceDataMap,boolean isUpdate) {
        if (maintenanceDataMap != null) {
            MaintenanceData maintenanceData = new MaintenanceData();
            if (isUpdate){
                Integer id = (Integer) maintenanceDataMap.get("id");
                maintenanceData.setId(id);
                orderNo = (String) maintenanceDataMap.get("orderNo");
            }
            maintenanceData.setOrderNo(orderNo);
            //先设置selectOne
            String brand = (String) maintenanceDataMap.get("brand");
            String modelName = (String) maintenanceDataMap.get("modelName");
            String seriesName = (String) maintenanceDataMap.get("seriesName");
            String vin = (String) maintenanceDataMap.get("vin");
            String makeReportDate = (String) maintenanceDataMap.get("makeReportDate");
            String updateTime = (String) maintenanceDataMap.get("updateTime");
            String manufacturer = (String) maintenanceDataMap.get("manufacturer");
            String makeDate = (String) maintenanceDataMap.get("makeDate");
            String productionArea = (String) maintenanceDataMap.get("productionArea");
            String carType = (String) maintenanceDataMap.get("carType");
            String transmissionType = (String) maintenanceDataMap.get("transmissionType");
            String displacement = (String) maintenanceDataMap.get("displacement");
            String effluentStandard = (String) maintenanceDataMap.get("effluentStandard");
            Integer carFireFlag = (Integer) maintenanceDataMap.get("carFireFlag");
            Integer carWaterFlag = (Integer) maintenanceDataMap.get("carWaterFlag");
            Integer carComponentRecordsFlag = (Integer) maintenanceDataMap.get("carComponentRecordsFlag");
            Integer carConstructRecordsFlag = (Integer) maintenanceDataMap.get("carConstructRecordsFlag");
            Integer carOutsideRecordsFlag = (Integer) maintenanceDataMap.get("carOutsideRecordsFlag");
            Integer mileageIsNormalFlag = (Integer) maintenanceDataMap.get("mileageIsNormalFlag");
            Integer mileageEstimate = (Integer) maintenanceDataMap.get("mileageEstimate");
            String lastMainTainTime = (String) maintenanceDataMap.get("lastMainTainTime");
            String mainTainTimes = (String) maintenanceDataMap.get("mainTainTimes");
            String lastRepairTime = (String) maintenanceDataMap.get("lastRepairTime");
            String mileageEveryYear = (String) maintenanceDataMap.get("mileageEveryYear");
            String reportNo = (String) maintenanceDataMap.get("reportNo");
            maintenanceData.setBrand(brand);
            maintenanceData.setModelName(modelName);
            maintenanceData.setSeriesName(seriesName);
            maintenanceData.setVin(vin);
            maintenanceData.setMakeReportDate(makeReportDate);
            maintenanceData.setUpdateTime(updateTime);
            maintenanceData.setManufacturer(manufacturer);
            maintenanceData.setMakeDate(makeDate);
            maintenanceData.setProductionArea(productionArea);
            maintenanceData.setCarType(carType);
            maintenanceData.setTransmissionType(transmissionType);
            maintenanceData.setDisplacement(displacement);
            maintenanceData.setEffluentStandard(effluentStandard);
            maintenanceData.setCarFireFlag(carFireFlag);
            maintenanceData.setCarWaterFlag(carWaterFlag);
            maintenanceData.setCarComponentRecordsFlag(carComponentRecordsFlag);
            maintenanceData.setCarConstructRecordsFlag(carConstructRecordsFlag);
            maintenanceData.setCarOutsideRecordsFlag(carOutsideRecordsFlag);
            maintenanceData.setMileageIsNormalFlag(mileageIsNormalFlag);
            maintenanceData.setMileageEstimate(mileageEstimate);
            maintenanceData.setLastMainTainTime(lastMainTainTime);
            maintenanceData.setMainTainTimes(mainTainTimes);
            maintenanceData.setLastRepairTime(lastRepairTime);
            maintenanceData.setMileageEveryYear(mileageEveryYear);
            maintenanceData.setReportNo(reportNo);
            save(maintenanceData);
            Integer maintenanceDataId = maintenanceData.getId();
            JSONArray normalJson= (JSONArray) maintenanceDataMap.get("normalRepairRecords");
            JSONArray constructAnalyzeJson = (JSONArray) maintenanceDataMap.get("constructAnalyzeRepairRecords");
            JSONArray componentAnalyzeJson = (JSONArray) maintenanceDataMap.get("componentAnalyzeRepairRecords");
            JSONArray outsideAnalyzeJson = (JSONArray) maintenanceDataMap.get("outsideAnalyzeRepairRecords");
            List<RepairRecords> normalRepairRecords = repairRecordsService.saveRecords(normalJson, maintenanceDataId, false,0);
            List<RepairRecords> constructAnalyzeRepairRecords =repairRecordsService.saveRecords(constructAnalyzeJson, maintenanceDataId,false,1);
            List<RepairRecords> componentAnalyzeRepairRecords =repairRecordsService.saveRecords(componentAnalyzeJson, maintenanceDataId,false,2);
            List<RepairRecords> outsideAnalyzeRepairRecords =repairRecordsService.saveRecords(outsideAnalyzeJson, maintenanceDataId,false,3);
            maintenanceData.setNormalRepairRecords(normalRepairRecords);
            maintenanceData.setConstructAnalyzeRepairRecords(constructAnalyzeRepairRecords);
            maintenanceData.setComponentAnalyzeRepairRecords(componentAnalyzeRepairRecords);
            maintenanceData.setOutsideAnalyzeRepairRecords(outsideAnalyzeRepairRecords);
            return maintenanceData;
        }
        return null;
    }

    @Override
    public IPage<MaintenanceData> pageList(Page<MaintenanceData> page, String orderNo, String vin) {
        return maintenanceDataMapper.pageList(page,orderNo,vin);
    }
}
