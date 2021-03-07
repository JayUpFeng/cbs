package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.HightechConfig;
import com.jinjike.cbs.mapper.HightechConfigMapper;
import com.jinjike.cbs.service.IHightechConfigService;
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
public class HightechConfigServiceImpl extends ServiceImpl<HightechConfigMapper, HightechConfig> implements IHightechConfigService {

    @Override
    public HightechConfig createHightechConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> hightechConfigMap = (Map<String, Object>) carModelDataMap.get("hightechConfig");
        if (hightechConfigMap != null) {
            HightechConfig hightechConfig = new HightechConfig();
            if (isUpdate){
                Integer id = (Integer) hightechConfigMap.get("id");
                hightechConfig.setId(id);
                carModelDataId = (Integer) hightechConfigMap.get("carModelDataId");
            }
            hightechConfig.setCarModelDataId(carModelDataId);
            String qjsxt = (String) hightechConfigMap.get("qjsxt");
            String zsyxh = (String) hightechConfigMap.get("zsyxh");
            String zkyjpfpxs = (String) hightechConfigMap.get("zkyjpfpxs");
            String ysxt = (String) hightechConfigMap.get("ysxt");
            String ztzdzxxt = (String) hightechConfigMap.get("ztzdzxxt");
            String zdscxt = (String) hightechConfigMap.get("zdscxt");
            String cdplyjxt = (String) hightechConfigMap.get("cdplyjxt");
            String bxfz = (String) hightechConfigMap.get("bxfz");
            String zdbcrw = (String) hightechConfigMap.get("zdbcrw");
            String fdjqtjs = (String) hightechConfigMap.get("fdjqtjs");
            hightechConfig.setQjsxt(qjsxt);
            hightechConfig.setZsyxh(zsyxh);
            hightechConfig.setZkyjpfpxs(zkyjpfpxs);
            hightechConfig.setYsxt(ysxt);
            hightechConfig.setZtzdzxxt(ztzdzxxt);
            hightechConfig.setZdscxt(zdscxt);
            hightechConfig.setCdplyjxt(cdplyjxt);
            hightechConfig.setBxfz(bxfz);
            hightechConfig.setZdbcrw(zdbcrw);
            hightechConfig.setFdjqtjs(fdjqtjs);
            save(hightechConfig);
            return hightechConfig;
        }
        return null;
    }
}
