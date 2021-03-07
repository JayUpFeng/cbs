package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.EngineConfig;
import com.jinjike.cbs.mapper.EngineConfigMapper;
import com.jinjike.cbs.service.IEngineConfigService;
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
public class EngineConfigServiceImpl extends ServiceImpl<EngineConfigMapper, EngineConfig> implements IEngineConfigService {

    @Override
    public EngineConfig createEngineConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> engineConfigMap = (Map<String, Object>) carModelDataMap.get("engineConfig");
        if (engineConfigMap != null) {
            EngineConfig engineConfig = new EngineConfig();
            if (isUpdate){
                Integer id = (Integer) engineConfigMap.get("id");
                engineConfig.setId(id);
                carModelDataId = (Integer) engineConfigMap.get("carModelDataId");
            }
            engineConfig.setCarModelDataId(carModelDataId);
            String fdjms = (String) engineConfigMap.get("fdjms");
            String bgl = (String) engineConfigMap.get("bgl");
            String sglb = (String) engineConfigMap.get("sglb");
            String hbbz = (String) engineConfigMap.get("hbbz");
            String ggcl = (String) engineConfigMap.get("ggcl");
            String gtcl = (String) engineConfigMap.get("gtcl");
            String gyfs = (String) engineConfigMap.get("gyfs");
            String rybh = (String) engineConfigMap.get("rybh");
            String rlxs = (String) engineConfigMap.get("rlxs");
            String fdjtyjs = (String) engineConfigMap.get("fdjtyjs");
            String zdnjzs = (String) engineConfigMap.get("zdnjzs");
            String zdnj = (String) engineConfigMap.get("zdnj");
            String zdglzs = (String) engineConfigMap.get("zdglzs");
            String zdgl = (String) engineConfigMap.get("zdgl");
            String zdml = (String) engineConfigMap.get("zdml");
            String xc = (String) engineConfigMap.get("xc");
            String gj = (String) engineConfigMap.get("gj");
            String pqjg = (String) engineConfigMap.get("pqjg");
            String ysb = (String) engineConfigMap.get("ysb");
            String mgqms = (String) engineConfigMap.get("mgqms");
            String qgs = (String) engineConfigMap.get("qgs");
            String qgplxs = (String) engineConfigMap.get("qgplxs");
            String jqxs = (String) engineConfigMap.get("jqxs");
            String pl = (String) engineConfigMap.get("pl");
            String fdjxh = (String) engineConfigMap.get("fdjxh");
            engineConfig.setFdjms(fdjms);
            engineConfig.setBgl(bgl);
            engineConfig.setSglb(sglb);
            engineConfig.setHbbz(hbbz);
            engineConfig.setGgcl(ggcl);
            engineConfig.setGtcl(gtcl);
            engineConfig.setGyfs(gyfs);
            engineConfig.setRybh(rybh);
            engineConfig.setRlxs(rlxs);
            engineConfig.setFdjtyjs(fdjtyjs);
            engineConfig.setZdnjzs(zdnjzs);
            engineConfig.setZdnj(zdnj);
            engineConfig.setZdglzs(zdglzs);
            engineConfig.setZdgl(zdgl);
            engineConfig.setZdml(zdml);
            engineConfig.setXc(xc);
            engineConfig.setGj(gj);
            engineConfig.setPqjg(pqjg);
            engineConfig.setYsb(ysb);
            engineConfig.setMgqms(mgqms);
            engineConfig.setQgs(qgs);
            engineConfig.setQgplxs(qgplxs);
            engineConfig.setJqxs(jqxs);
            engineConfig.setPl(pl);
            engineConfig.setFdjxh(fdjxh);
            save(engineConfig);
            return engineConfig;
        }
        return null;
    }
}
