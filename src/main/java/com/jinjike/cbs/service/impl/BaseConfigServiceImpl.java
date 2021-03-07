package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.BaseConfig;
import com.jinjike.cbs.mapper.BaseConfigMapper;
import com.jinjike.cbs.service.IBaseConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
public class BaseConfigServiceImpl extends ServiceImpl<BaseConfigMapper, BaseConfig> implements IBaseConfigService {

    @Override
    public BaseConfig createBaseConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> baseConfigMap = (Map<String, Object>) carModelDataMap.get("baseConfig");
        if (baseConfigMap != null) {
            BaseConfig baseConfig = new BaseConfig();
            if (isUpdate){
                Integer id = (Integer) baseConfigMap.get("id");
                baseConfig.setId(id);
                carModelDataId = (Integer) baseConfigMap.get("carModelDataId");
            }
            baseConfig.setCarModelDataId(carModelDataId);
            String brandName = (String) baseConfigMap.get("brandName");
            String brandCode = (String) baseConfigMap.get("brandCode");
            String familyName = (String) baseConfigMap.get("familyName");
            String familyCode = (String) baseConfigMap.get("familyCode");
            String groupName = (String) baseConfigMap.get("groupName");
            String groupCode = (String) baseConfigMap.get("groupCode");
            String vehicleAlias = (String) baseConfigMap.get("vehicleAlias");
            BigDecimal listPrice = (BigDecimal) baseConfigMap.get("listPrice");
            BigDecimal purchasePrice = (BigDecimal) baseConfigMap.get("purchasePrice");
            BigDecimal kindredPrice = (BigDecimal) baseConfigMap.get("kindredPrice");
            Integer seat = (Integer) baseConfigMap.get("seat");
            String gearboxType = (String) baseConfigMap.get("gearboxType");
            String cfgLevel = (String) baseConfigMap.get("cfgLevel");
            String importFlag = (String) baseConfigMap.get("importFlag");
            String yearPattern = (String) baseConfigMap.get("yearPattern");
            String marketDate = (String) baseConfigMap.get("marketDate");
            String vehicleSize = (String) baseConfigMap.get("vehicleSize");
            String arrayType = (String) baseConfigMap.get("arrayType");
            String engineDesc = (String) baseConfigMap.get("engineDesc");
            String engineModel = (String) baseConfigMap.get("engineModel");
            String commonName = (String) baseConfigMap.get("commonName");
            String companyCode = (String) baseConfigMap.get("companyCode");
            String companyName = (String) baseConfigMap.get("companyName");
            String vehicleClass = (String) baseConfigMap.get("vehicleClass");
            String absFlag = (String) baseConfigMap.get("absFlag");
            String antiTheft = (String) baseConfigMap.get("antiTheft");
            String powerType = (String) baseConfigMap.get("powerType");
            Integer airbagNum = (Integer) baseConfigMap.get("airbagNum");
            String displacement = (String) baseConfigMap.get("displacement");
            Integer seatMin = (Integer) baseConfigMap.get("seatMin");
            Integer seatMax = (Integer) baseConfigMap.get("seatMax");
            String remark = (String) baseConfigMap.get("remark");
            String riskFlag = (String) baseConfigMap.get("riskFlag");
            String wheelbase = (String) baseConfigMap.get("wheelbase");
            String fuelJetType = (String) baseConfigMap.get("fuelJetType");
            Integer valveNum = (Integer) baseConfigMap.get("valveNum");
            Integer gearNum = (Integer) baseConfigMap.get("gearNum");
            String fullWeight = (String) baseConfigMap.get("fullWeight");
            String fullWeightMinKg = (String) baseConfigMap.get("fullWeightMinKg");
            String fullWeightMaxKg = (String) baseConfigMap.get("fullWeightMaxKg");
            BigDecimal purchasePriceTax = (BigDecimal) baseConfigMap.get("purchasePriceTax");
            BigDecimal kindredPriceTax = (BigDecimal) baseConfigMap.get("kindredPriceTax");
            String power = (String) baseConfigMap.get("power");
            String bodyType = (String) baseConfigMap.get("bodyType");
            String letStand = (String) baseConfigMap.get("letStand");
            String effluentStandard = (String) baseConfigMap.get("effluentStandard");
            String vehicleWeight = (String) baseConfigMap.get("vehicleWeight");
            String trackFront = (String) baseConfigMap.get("trackFront");
            String trackRear = (String) baseConfigMap.get("trackRear");
            String drivenType = (String) baseConfigMap.get("drivenType");
            String bsx = (String) baseConfigMap.get("bsx");
            String ckg = (String) baseConfigMap.get("ckg");
            String csjg = (String) baseConfigMap.get("csjg");
            String gfjs = (String) baseConfigMap.get("gfjs");
            String scjs = (String) baseConfigMap.get("scjs");
            String sczd = (String) baseConfigMap.get("sczd");
            String scyh = (String) baseConfigMap.get("scyh");
            String gxbzhyh = (String) baseConfigMap.get("gxbzhyh");
            String zczb = (String) baseConfigMap.get("zczb");
            String stopFlag = (String) baseConfigMap.get("stopFlag");
            baseConfig.setBrandName(brandName);
            baseConfig.setBrandCode(brandCode);
            baseConfig.setFamilyName(familyName);
            baseConfig.setFamilyCode(familyCode);
            baseConfig.setGroupName(groupName);
            baseConfig.setGroupCode(groupCode);
            baseConfig.setVehicleAlias(vehicleAlias);
            baseConfig.setListPrice(listPrice);
            baseConfig.setPurchasePrice(purchasePrice);
            baseConfig.setKindredPrice(kindredPrice);
            baseConfig.setSeat(seat);
            baseConfig.setGearboxType(gearboxType);
            baseConfig.setCfgLevel(cfgLevel);
            baseConfig.setImportFlag(importFlag);
            baseConfig.setYearPattern(yearPattern);
            baseConfig.setMarketDate(marketDate);
            baseConfig.setVehicleSize(vehicleSize);
            baseConfig.setArrayType(arrayType);
            baseConfig.setEngineDesc(engineDesc);
            baseConfig.setEngineModel(engineModel);
            baseConfig.setCommonName(commonName);
            baseConfig.setCompanyCode(companyCode);
            baseConfig.setCompanyName(companyName);
            baseConfig.setVehicleClass(vehicleClass);
            baseConfig.setAbsFlag(absFlag);
            baseConfig.setAntiTheft(antiTheft);
            baseConfig.setPowerType(powerType);
            baseConfig.setAirbagNum(airbagNum);
            baseConfig.setDisplacement(displacement);
            baseConfig.setSeatMin(seatMin);
            baseConfig.setSeatMax(seatMax);
            baseConfig.setRemark(remark);
            baseConfig.setRiskFlag(riskFlag);
            baseConfig.setWheelbase(wheelbase);
            baseConfig.setFuelJetType(fuelJetType);
            baseConfig.setValveNum(valveNum);
            baseConfig.setGearNum(gearNum);
            baseConfig.setFullWeight(fullWeight);
            baseConfig.setFullWeightMinKg(fullWeightMinKg);
            baseConfig.setFullWeightMaxKg(fullWeightMaxKg);
            baseConfig.setPurchasePriceTax(purchasePriceTax);
            baseConfig.setKindredPriceTax(kindredPriceTax);
            baseConfig.setPower(power);
            baseConfig.setBodyType(bodyType);
            baseConfig.setLetStand(letStand);
            baseConfig.setEffluentStandard(effluentStandard);
            baseConfig.setVehicleWeight(vehicleWeight);
            baseConfig.setTrackFront(trackFront);
            baseConfig.setTrackRear(trackRear);
            baseConfig.setDrivenType(drivenType);
            baseConfig.setBsx(bsx);
            baseConfig.setCkg(ckg);
            baseConfig.setCsjg(csjg);
            baseConfig.setGfjs(gfjs);
            baseConfig.setScjs(scjs);
            baseConfig.setSczd(sczd);
            baseConfig.setScyh(scyh);
            baseConfig.setGxbzhyh(gxbzhyh);
            baseConfig.setZczb(zczb);
            baseConfig.setStopFlag(stopFlag);
            save(baseConfig);
            return baseConfig;
        }
        return null;
    }
}
