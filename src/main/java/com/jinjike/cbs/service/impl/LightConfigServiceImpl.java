package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.LightConfig;
import com.jinjike.cbs.mapper.LightConfigMapper;
import com.jinjike.cbs.service.ILightConfigService;
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
public class LightConfigServiceImpl extends ServiceImpl<LightConfigMapper, LightConfig> implements ILightConfigService {

    @Override
    public LightConfig createLightConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> lightConfigMap = (Map<String, Object>) carModelDataMap.get("lightConfig");
        if (lightConfigMap != null) {
            LightConfig lightConfig = new LightConfig();
            if (isUpdate){
                Integer id = (Integer) lightConfigMap.get("id");
                lightConfig.setId(id);
                carModelDataId = (Integer) lightConfigMap.get("carModelDataId");
            }
            lightConfig.setCarModelDataId(carModelDataId);
            String ddsdtj = (String) lightConfigMap.get("ddsdtj");
            String gyys = (String) lightConfigMap.get("gyys");
            String hys = (String) lightConfigMap.get("hys");
            String zybhzj = (String) lightConfigMap.get("zybhzj");
            String hpcysbl = (String) lightConfigMap.get("hpcysbl");
            String hpczyl = (String) lightConfigMap.get("hpczyl");
            String hfdzyl = (String) lightConfigMap.get("hfdzyl");
            String hsjjy = (String) lightConfigMap.get("hsjjy");
            String hsjddzd = (String) lightConfigMap.get("hsjddzd");
            String whsjzdfxm = (String) lightConfigMap.get("whsjzdfxm");
            String nhsjzdfxm = (String) lightConfigMap.get("nhsjzdfxm");
            String hsjjr = (String) lightConfigMap.get("hsjjr");
            String hsjddtj = (String) lightConfigMap.get("hsjddtj");
            String fzwxbl = (String) lightConfigMap.get("fzwxbl");
            String ccfjsgn = (String) lightConfigMap.get("ccfjsgn");
            String hddcc = (String) lightConfigMap.get("hddcc");
            String qddcc = (String) lightConfigMap.get("qddcc");
            String cnfwd = (String) lightConfigMap.get("cnfwd");
            String ddqxzz = (String) lightConfigMap.get("ddqxzz");
            String ddgdkt = (String) lightConfigMap.get("ddgdkt");
            String qwd = (String) lightConfigMap.get("qwd");
            String zxtd = (String) lightConfigMap.get("zxtd");
            String zdtd = (String) lightConfigMap.get("zdtd");
            String rjxcd = (String) lightConfigMap.get("rjxcd");
            String leddd = (String) lightConfigMap.get("leddd");
            String xqdd = (String) lightConfigMap.get("xqdd");
            lightConfig.setDdsdtj(ddsdtj);
            lightConfig.setGyys(gyys);
            lightConfig.setHys(hys);
            lightConfig.setZybhzj(zybhzj);
            lightConfig.setHpcysbl(hpcysbl);
            lightConfig.setHpczyl(hpczyl);
            lightConfig.setHfdzyl(hfdzyl);
            lightConfig.setHsjjy(hsjjy);
            lightConfig.setHsjddzd(hsjddzd);
            lightConfig.setWhsjzdfxm(whsjzdfxm);
            lightConfig.setNhsjzdfxm(nhsjzdfxm);
            lightConfig.setHsjjr(hsjjr);
            lightConfig.setHsjddtj(hsjddtj);
            lightConfig.setFzwxbl(fzwxbl);
            lightConfig.setCcfjsgn(ccfjsgn);
            lightConfig.setHddcc(hddcc);
            lightConfig.setQddcc(qddcc);
            lightConfig.setCnfwd(cnfwd);
            lightConfig.setDdqxzz(ddqxzz);
            lightConfig.setDdgdkt(ddgdkt);
            lightConfig.setQwd(qwd);
            lightConfig.setZxtd(zxtd);
            lightConfig.setZdtd(zdtd);
            lightConfig.setRjxcd(rjxcd);
            lightConfig.setLeddd(leddd);
            lightConfig.setXqdd(xqdd);
            save(lightConfig);
            return lightConfig;
        }
        return null;
    }
}
