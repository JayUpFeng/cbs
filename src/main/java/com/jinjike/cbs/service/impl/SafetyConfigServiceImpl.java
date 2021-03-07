package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.SafetyConfig;
import com.jinjike.cbs.mapper.SafetyConfigMapper;
import com.jinjike.cbs.service.ISafetyConfigService;
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
public class SafetyConfigServiceImpl extends ServiceImpl<SafetyConfigMapper, SafetyConfig> implements ISafetyConfigService {

    @Override
    public SafetyConfig createSafetyConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> baseConfigMap = (Map<String, Object>) carModelDataMap.get("baseConfig");
        if (baseConfigMap != null) {
            SafetyConfig safetyConfig = new SafetyConfig();
            if (isUpdate){
                Integer id = (Integer) baseConfigMap.get("id");
                safetyConfig.setId(id);
                carModelDataId = (Integer) baseConfigMap.get("carModelDataId");
            }
            safetyConfig.setCarModelDataId(carModelDataId);
            String wysjrxt = (String) baseConfigMap.get("wysjrxt");
            String wysqdxt = (String) baseConfigMap.get("wysqdxt");
            String ykys = (String) baseConfigMap.get("ykys");
            String cnzks = (String) baseConfigMap.get("cnzks");
            String fdjdzfd = (String) baseConfigMap.get("fdjdzfd");
            String zyjk = (String) baseConfigMap.get("zyjk");
            String etzyjk = (String) baseConfigMap.get("etzyjk");
            String aqdwjts = (String) baseConfigMap.get("aqdwjts");
            String ltyjxxs = (String) baseConfigMap.get("ltyjxxs");
            String tyjczz = (String) baseConfigMap.get("tyjczz");
            String xbqn = (String) baseConfigMap.get("xbqn");
            String hptbqnql = (String) baseConfigMap.get("hptbqnql");
            String qptbqnql = (String) baseConfigMap.get("qptbqnql");
            String hpcqn = (String) baseConfigMap.get("hpcqn");
            String qpcqn = (String) baseConfigMap.get("qpcqn");
            String fjsaqqn = (String) baseConfigMap.get("fjsaqqn");
            String jszaqqn = (String) baseConfigMap.get("jszaqqn");
            safetyConfig.setWysjrxt(wysjrxt);
            safetyConfig.setWysqdxt(wysqdxt);
            safetyConfig.setYkys(ykys);
            safetyConfig.setCnzks(cnzks);
            safetyConfig.setFdjdzfd(fdjdzfd);
            safetyConfig.setZyjk(zyjk);
            safetyConfig.setEtzyjk(etzyjk);
            safetyConfig.setAqdwjts(aqdwjts);
            safetyConfig.setLtyjxxs(ltyjxxs);
            safetyConfig.setTyjczz(tyjczz);
            safetyConfig.setXbqn(xbqn);
            safetyConfig.setHptbqnql(hptbqnql);
            safetyConfig.setQptbqnql(qptbqnql);
            safetyConfig.setHpcqn(hpcqn);
            safetyConfig.setQpcqn(qpcqn);
            safetyConfig.setFjsaqqn(fjsaqqn);
            safetyConfig.setJszaqqn(jszaqqn);
            save(safetyConfig);
            return safetyConfig;
        }
        return null;
    }
}
