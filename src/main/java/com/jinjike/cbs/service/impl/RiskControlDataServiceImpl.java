package com.jinjike.cbs.service.impl;

import com.jinjike.cbs.model.RiskControlData;
import com.jinjike.cbs.mapper.RiskControlDataMapper;
import com.jinjike.cbs.service.IRiskControlDataService;
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
public class RiskControlDataServiceImpl extends ServiceImpl<RiskControlDataMapper, RiskControlData> implements IRiskControlDataService {

    @Override
    public RiskControlData createRiskControlData(String orderNo, Map<String, Object> riskControlDataMap,boolean isUpdate) {
        if (riskControlDataMap != null) {
            RiskControlData riskControlData=new RiskControlData();
            if (isUpdate){
                Integer id = (Integer) riskControlDataMap.get("id");
                riskControlData.setId(id);
                orderNo = (String) riskControlDataMap.get("orderNo");
            }
            riskControlData.setOrderNo(orderNo);
            Integer syxz = (Integer) riskControlDataMap.get("syxz");
            Integer mp = (Integer) riskControlDataMap.get("mp");
            Integer sfty = (Integer) riskControlDataMap.get("sfty");
            Integer xsz = (Integer) riskControlDataMap.get("xsz");
            Integer djz = (Integer) riskControlDataMap.get("djz");
            Integer sftp = (Integer) riskControlDataMap.get("sftp");
            Integer violationCount = (Integer) riskControlDataMap.get("violationCount");
            Integer sfdy = (Integer) riskControlDataMap.get("sfdy");
            riskControlData.setSyxz(syxz);
            riskControlData.setMp(mp);
            riskControlData.setSfty(sfty);
            riskControlData.setXsz(xsz);
            riskControlData.setDjz(djz);
            riskControlData.setSftp(sftp);
            riskControlData.setSfdy(sfdy);
            riskControlData.setViolationCount(violationCount);
            save(riskControlData);
            return riskControlData;
        }
        return null;
    }
}
