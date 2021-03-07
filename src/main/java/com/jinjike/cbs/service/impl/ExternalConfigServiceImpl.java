package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.ExternalConfig;
import com.jinjike.cbs.mapper.ExternalConfigMapper;
import com.jinjike.cbs.service.IExternalConfigService;
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
public class ExternalConfigServiceImpl extends ServiceImpl<ExternalConfigMapper, ExternalConfig> implements IExternalConfigService {

    @Override
    public ExternalConfig createExternalConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> externalConfigMap = (Map<String, Object>) carModelDataMap.get("externalConfig");
        if (externalConfigMap != null) {
            ExternalConfig externalConfig = new ExternalConfig();
            if (isUpdate){
                Integer id = (Integer) externalConfigMap.get("id");
                externalConfig.setId(id);
                carModelDataId = (Integer) externalConfigMap.get("carModelDataId");
            }
            externalConfig.setCarModelDataId(carModelDataId);
            String bcfz = (String) externalConfigMap.get("bcfz");
            String qzzyfs = (String) externalConfigMap.get("qzzyfs");
            String czbx = (String) externalConfigMap.get("czbx");
            String cnkqtj = (String) externalConfigMap.get("cnkqtj");
            String wdfqkz = (String) externalConfigMap.get("wdfqkz");
            String hzcfk = (String) externalConfigMap.get("hzcfk");
            String hpdlkt = (String) externalConfigMap.get("hpdlkt");
            String zdkt = (String) externalConfigMap.get("zdkt");
            String sdkt = (String) externalConfigMap.get("sdkt");
            String ddhbx = (String) externalConfigMap.get("ddhbx");
            String hpbj = (String) externalConfigMap.get("hpbj");
            String hzzyfs = (String) externalConfigMap.get("hzzyfs");
            String dspzy = (String) externalConfigMap.get("dspzy");
            String hpzyblfd = (String) externalConfigMap.get("hpzyblfd");
            String hpzyztfd = (String) externalConfigMap.get("hpzyztfd");
            String hpzyam = (String) externalConfigMap.get("hpzyam");
            String qpzyam = (String) externalConfigMap.get("qpzyam");
            String hpzytf = (String) externalConfigMap.get("hpzytf");
            String qpzytf = (String) externalConfigMap.get("qpzytf");
            String hpzyjr = (String) externalConfigMap.get("hpzyjr");
            String qpzyjr = (String) externalConfigMap.get("qpzyjr");
            String ddzyjy = (String) externalConfigMap.get("ddzyjy");
            String hpzyddtj = (String) externalConfigMap.get("hpzyddtj");
            String depzyyd = (String) externalConfigMap.get("depzyyd");
            String depkbjdtj = (String) externalConfigMap.get("depkbjdtj");
            String fjswddtj = (String) externalConfigMap.get("fjswddtj");
            String jswddtj = (String) externalConfigMap.get("jswddtj");
            String jbzctj = (String) externalConfigMap.get("jbzctj");
            String ybzctj = (String) externalConfigMap.get("ybzctj");
            String zygdtj = (String) externalConfigMap.get("zygdtj");
            String ydfgzy = (String) externalConfigMap.get("ydfgzy");
            String zpzy = (String) externalConfigMap.get("zpzy");
            String ttszxs = (String) externalConfigMap.get("ttszxs");
            String xcdnxsp = (String) externalConfigMap.get("xcdnxsp");
            String dcspyx = (String) externalConfigMap.get("dcspyx");
            String hdcld = (String) externalConfigMap.get("hdcld");
            String qld = (String) externalConfigMap.get("qld");
            String dsxh = (String) externalConfigMap.get("dsxh");
            String fxpjr = (String) externalConfigMap.get("fxpjr");
            String fxphd = (String) externalConfigMap.get("fxphd");
            String dgnfxp = (String) externalConfigMap.get("dgnfxp");
            String fxpddtj = (String) externalConfigMap.get("fxpddtj");
            String fxpqhtj = (String) externalConfigMap.get("fxpqhtj");
            String fxpsxtj = (String) externalConfigMap.get("fxpsxtj");
            String zpfxp = (String) externalConfigMap.get("zpfxp");
            String ddxhm = (String) externalConfigMap.get("ddxhm");
            String lhjly = (String) externalConfigMap.get("lhjly");
            String ydwgtj = (String) externalConfigMap.get("ydwgtj");
            String tyntc = (String) externalConfigMap.get("tyntc");
            String qjtc = (String) externalConfigMap.get("qjtc");
            String ddtc = (String) externalConfigMap.get("ddtc");
            externalConfig.setBcfz(bcfz);
            externalConfig.setQzzyfs(qzzyfs);
            externalConfig.setCzbx(czbx);
            externalConfig.setCnkqtj(cnkqtj);
            externalConfig.setWdfqkz(wdfqkz);
            externalConfig.setHzcfk(hzcfk);
            externalConfig.setHpdlkt(hpdlkt);
            externalConfig.setZdkt(zdkt);
            externalConfig.setSdkt(sdkt);
            externalConfig.setDdhbx(ddhbx);
            externalConfig.setHpbj(hpbj);
            externalConfig.setHzzyfs(hzzyfs);
            externalConfig.setDspzy(dspzy);
            externalConfig.setHpzyblfd(hpzyblfd);
            externalConfig.setHpzyztfd(hpzyztfd);
            externalConfig.setHpzyam(hpzyam);
            externalConfig.setQpzyam(qpzyam);
            externalConfig.setHpzytf(hpzytf);
            externalConfig.setQpzytf(qpzytf);
            externalConfig.setHpzyjr(hpzyjr);
            externalConfig.setQpzyjr(qpzyjr);
            externalConfig.setDdzyjy(ddzyjy);
            externalConfig.setHpzyddtj(hpzyddtj);
            externalConfig.setDepzyyd(depzyyd);
            externalConfig.setDepkbjdtj(depkbjdtj);
            externalConfig.setFjswddtj(fjswddtj);
            externalConfig.setJswddtj(jswddtj);
            externalConfig.setJbzctj(jbzctj);
            externalConfig.setYbzctj(ybzctj);
            externalConfig.setZygdtj(zygdtj);
            externalConfig.setYdfgzy(ydfgzy);
            externalConfig.setZpzy(zpzy);
            externalConfig.setTtszxs(ttszxs);
            externalConfig.setXcdnxsp(xcdnxsp);
            externalConfig.setDcspyx(dcspyx);
            externalConfig.setHdcld(hdcld);
            externalConfig.setQld(qld);
            externalConfig.setDsxh(dsxh);
            externalConfig.setFxpjr(fxpjr);
            externalConfig.setFxphd(fxphd);
            externalConfig.setDgnfxp(dgnfxp);
            externalConfig.setFxpddtj(fxpddtj);
            externalConfig.setFxpqhtj(fxpqhtj);
            externalConfig.setFxpsxtj(fxpsxtj);
            externalConfig.setZpfxp(zpfxp);
            externalConfig.setDdxhm(ddxhm);
            externalConfig.setLhjly(lhjly);
            externalConfig.setYdwgtj(ydwgtj);
            externalConfig.setTyntc(tyntc);
            externalConfig.setQjtc(qjtc);
            externalConfig.setDdtc(ddtc);
            save(externalConfig);
            return externalConfig;
        }
        return null;
    }
}
