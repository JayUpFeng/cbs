package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.ControlConfig;
import com.jinjike.cbs.mapper.ControlConfigMapper;
import com.jinjike.cbs.service.IControlConfigService;
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
public class ControlConfigServiceImpl extends ServiceImpl<ControlConfigMapper, ControlConfig> implements IControlConfigService {

    @Override
    public ControlConfig createControlConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> controlConfigMap = (Map<String, Object>) carModelDataMap.get("controlConfig");
        if (controlConfigMap != null) {
            ControlConfig controlConfig = new ControlConfig();
            if (isUpdate){
                Integer id = (Integer) controlConfigMap.get("id");
                controlConfig.setId(id);
                carModelDataId = (Integer) controlConfigMap.get("carModelDataId");
            }
            controlConfig.setCarModelDataId(carModelDataId);
            String hqxhcsq = (String) controlConfigMap.get("hqxhcsq");
            String qqxhcsq = (String) controlConfigMap.get("qqxhcsq");
            String zycsqszgn = (String) controlConfigMap.get("zycsqszgn");
            String kbzxb = (String) controlConfigMap.get("kbzxb");
            String kqxj = (String) controlConfigMap.get("kqxj");
            String kbxj = (String) controlConfigMap.get("kbxj");
            String dphj = (String) controlConfigMap.get("dphj");
            String zdzc = (String) controlConfigMap.get("zdzc");
            String cswdkz = (String) controlConfigMap.get("cswdkz");
            String qylkz = (String) controlConfigMap.get("qylkz");
            String scfz = (String) controlConfigMap.get("scfz");
            String zdlfp = (String) controlConfigMap.get("zdlfp");
            String abs = (String) controlConfigMap.get("abs");
            controlConfig.setHqxhcsq(hqxhcsq);
            controlConfig.setQqxhcsq(qqxhcsq);
            controlConfig.setZycsqszgn(zycsqszgn);
            controlConfig.setKbzxb(kbzxb);
            controlConfig.setKqxj(kqxj);
            controlConfig.setKbxj(kbxj);
            controlConfig.setDphj(dphj);
            controlConfig.setZdzc(zdzc);
            controlConfig.setCswdkz(cswdkz);
            controlConfig.setQylkz(qylkz);
            controlConfig.setScfz(scfz);
            controlConfig.setZdlfp(zdlfp);
            controlConfig.setAbs(abs);
            save(controlConfig);
            return controlConfig;
        }
        return null;
    }
}
