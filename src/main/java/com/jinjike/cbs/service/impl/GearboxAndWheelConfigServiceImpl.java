package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.GearboxAndWheelConfig;
import com.jinjike.cbs.mapper.GearboxAndWheelConfigMapper;
import com.jinjike.cbs.service.IGearboxAndWheelConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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
public class GearboxAndWheelConfigServiceImpl extends ServiceImpl<GearboxAndWheelConfigMapper, GearboxAndWheelConfig> implements IGearboxAndWheelConfigService {

    @Override
    public GearboxAndWheelConfig createGearboxAndWheelConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> gearboxAndWheelConfigMap = (Map<String, Object>) carModelDataMap.get("gearboxAndWheelConfig");
        if (gearboxAndWheelConfigMap != null) {
            GearboxAndWheelConfig gearboxAndWheelConfig = new GearboxAndWheelConfig();
            if (isUpdate){
                Integer id = (Integer) gearboxAndWheelConfigMap.get("id");
                gearboxAndWheelConfig.setId(id);
                carModelDataId = (Integer) gearboxAndWheelConfigMap.get("carModelDataId");
            }
            gearboxAndWheelConfig.setCarModelDataId(carModelDataId);
            String btgg = (String) gearboxAndWheelConfigMap.get("btgg");
            String hltgg = (String) gearboxAndWheelConfigMap.get("hltgg");
            String qltgg = (String) gearboxAndWheelConfigMap.get("qltgg");
            String zczdlx = (String) gearboxAndWheelConfigMap.get("zczdlx");
            String hzdqlx = (String) gearboxAndWheelConfigMap.get("hzdqlx");
            String qzdqlx = (String) gearboxAndWheelConfigMap.get("qzdqlx");
            String ctjg = (String) gearboxAndWheelConfigMap.get("ctjg");
            String zllx = (String) gearboxAndWheelConfigMap.get("zllx");
            String sqxs = (String) gearboxAndWheelConfigMap.get("sqxs");
            String hxglx = (String) gearboxAndWheelConfigMap.get("hxglx");
            String qxglx = (String) gearboxAndWheelConfigMap.get("qxglx");
            String qdfs = (String) gearboxAndWheelConfigMap.get("qdfs");
            String bxslx = (String) gearboxAndWheelConfigMap.get("bxslx");
            String jc = (String) gearboxAndWheelConfigMap.get("jc");
            String dws = (String) gearboxAndWheelConfigMap.get("dws");
            gearboxAndWheelConfig.setBtgg(btgg);
            gearboxAndWheelConfig.setHltgg(hltgg);
            gearboxAndWheelConfig.setQltgg(qltgg);
            gearboxAndWheelConfig.setZczdlx(zczdlx);
            gearboxAndWheelConfig.setHzdqlx(hzdqlx);
            gearboxAndWheelConfig.setQzdqlx(qzdqlx);
            gearboxAndWheelConfig.setCtjg(ctjg);
            gearboxAndWheelConfig.setZllx(zllx);
            gearboxAndWheelConfig.setSqxs(sqxs);
            gearboxAndWheelConfig.setHxglx(hxglx);
            gearboxAndWheelConfig.setQxglx(qxglx);
            gearboxAndWheelConfig.setQdfs(qdfs);
            gearboxAndWheelConfig.setBxslx(bxslx);
            gearboxAndWheelConfig.setJc(jc);
            gearboxAndWheelConfig.setDws(dws);
            save(gearboxAndWheelConfig);
            return gearboxAndWheelConfig;
        }
        return null;
    }
}
