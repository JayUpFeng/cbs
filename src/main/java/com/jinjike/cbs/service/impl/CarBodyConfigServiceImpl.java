package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.CarBodyConfig;
import com.jinjike.cbs.mapper.CarBodyConfigMapper;
import com.jinjike.cbs.service.ICarBodyConfigService;
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
public class CarBodyConfigServiceImpl extends ServiceImpl<CarBodyConfigMapper, CarBodyConfig> implements ICarBodyConfigService {

    @Override
    public CarBodyConfig createCarBodyConfig(Map<String, Object> carModelDataMap, Integer carModelDataId,boolean isUpdate) {
        Map<String, Object> carBodyConfigMap = (Map<String, Object>) carModelDataMap.get("carBodyConfig");
        if (carBodyConfigMap != null) {
            CarBodyConfig carBodyConfig = new CarBodyConfig();
            if (isUpdate){
                Integer id = (Integer) carBodyConfigMap.get("id");
                carBodyConfig.setId(id);
                carModelDataId = (Integer) carBodyConfigMap.get("carModelDataId");
            }
            carBodyConfig.setCarModelDataId(carModelDataId);
            String cscsjg = (String) carBodyConfigMap.get("cscsjg");
            String yjtz = (String) carBodyConfigMap.get("yjtz");
            String dpmj = (String) carBodyConfigMap.get("dpmj");
            String xlxrj = (String) carBodyConfigMap.get("xlxrj");
            String yxrj = (String) carBodyConfigMap.get("yxrj");
            String zws = (String) carBodyConfigMap.get("zws");
            String cms = (String) carBodyConfigMap.get("cms");
            String zbzl = (String) carBodyConfigMap.get("zbzl");
            String zj = (String) carBodyConfigMap.get("zj");
            String hlj = (String) carBodyConfigMap.get("hlj");
            String qlj = (String) carBodyConfigMap.get("qlj");
            String gd = (String) carBodyConfigMap.get("gd");
            String kd = (String) carBodyConfigMap.get("kd");
            String cd = (String) carBodyConfigMap.get("cd");
            carBodyConfig.setCscsjg(cscsjg);
            carBodyConfig.setYjtz(yjtz);
            carBodyConfig.setDpmj(dpmj);
            carBodyConfig.setXlxrj(xlxrj);
            carBodyConfig.setYxrj(yxrj);
            carBodyConfig.setZws(zws);
            carBodyConfig.setCms(cms);
            carBodyConfig.setZbzl(zbzl);
            carBodyConfig.setZj(zj);
            carBodyConfig.setHlj(hlj);
            carBodyConfig.setQlj(qlj);
            carBodyConfig.setGd(gd);
            carBodyConfig.setKd(kd);
            carBodyConfig.setCd(cd);
            save(carBodyConfig);
            return carBodyConfig;
        }
        return null;
    }
}
