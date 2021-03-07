package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.MediaConfig;
import com.jinjike.cbs.mapper.MediaConfigMapper;
import com.jinjike.cbs.service.IMediaConfigService;
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
public class MediaConfigServiceImpl extends ServiceImpl<MediaConfigMapper, MediaConfig> implements IMediaConfigService {

    @Override
    public MediaConfig createMediaConfig(Map<String, Object> carModelDataMap, int carModelDataId,boolean isUpdate) {
        Map<String, Object> mediaConfigMap = (Map<String, Object>) carModelDataMap.get("mediaConfig");
        if (mediaConfigMap != null) {
            MediaConfig mediaConfig = new MediaConfig();
            if (isUpdate){
                Integer id = (Integer) mediaConfigMap.get("id");
                mediaConfig.setId(id);
                carModelDataId = (Integer) mediaConfigMap.get("carModelDataId");
            }
            mediaConfig.setCarModelDataId(carModelDataId);
            String duodcd = (String) mediaConfigMap.get("duodcd");
            String lbysqxt8 = (String) mediaConfigMap.get("lbysqxt8");
            String lbysqxt6 = (String) mediaConfigMap.get("lbysqxt6");
            String lbysqxt4 = (String) mediaConfigMap.get("lbysqxt4");
            String lbysqxt2 = (String) mediaConfigMap.get("lbysqxt2");
            String dddvdxt = (String) mediaConfigMap.get("dddvdxt");
            String dddvd = (String) mediaConfigMap.get("dddvd");
            String xnddcd = (String) mediaConfigMap.get("xnddcd");
            String ddcd = (String) mediaConfigMap.get("ddcd");
            String cdzc = (String) mediaConfigMap.get("cdzc");
            String wjyyjk = (String) mediaConfigMap.get("wjyyjk");
            String hpyjp = (String) mediaConfigMap.get("hpyjp");
            String czds = (String) mediaConfigMap.get("czds");
            String lyczdh = (String) mediaConfigMap.get("lyczdh");
            String nzyp = (String) mediaConfigMap.get("nzyp");
            String rjjhxt = (String) mediaConfigMap.get("rjjhxt");
            String dwhdfw = (String) mediaConfigMap.get("dwhdfw");
            String gps = (String) mediaConfigMap.get("gps");
            mediaConfig.setDuodcd(duodcd);
            mediaConfig.setLbysqxt8(lbysqxt8);
            mediaConfig.setLbysqxt6(lbysqxt6);
            mediaConfig.setLbysqxt4(lbysqxt4);
            mediaConfig.setLbysqxt2(lbysqxt2);
            mediaConfig.setDddvdxt(dddvdxt);
            mediaConfig.setDddvd(dddvd);
            mediaConfig.setXnddcd(xnddcd);
            mediaConfig.setDdcd(ddcd);
            mediaConfig.setCdzc(cdzc);
            mediaConfig.setWjyyjk(wjyyjk);
            mediaConfig.setHpyjp(hpyjp);
            mediaConfig.setCzds(czds);
            mediaConfig.setLyczdh(lyczdh);
            mediaConfig.setNzyp(nzyp);
            mediaConfig.setRjjhxt(rjjhxt);
            mediaConfig.setDwhdfw(dwhdfw);
            mediaConfig.setGps(gps);
            save(mediaConfig);
            return mediaConfig;
        }
        return null;
    }
}
