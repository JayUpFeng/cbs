package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinjike.cbs.mapper.CertificateMapper;
import com.jinjike.cbs.model.Certificate;
import com.jinjike.cbs.service.ICertificateService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
public class CertificateServiceImpl extends ServiceImpl<CertificateMapper, Certificate> implements ICertificateService {

    @Override
    public Object createCertificate(Map<String, Object> detectionDataMap, Integer detectionDataId, boolean isUpdate) {
        JSONArray certificateArray;
        Certificate certificate;
        try{
            certificateArray = (JSONArray) detectionDataMap.get("certificate");
            if (certificateArray != null && certificateArray.size() > 0) {
                List<Certificate> list = new ArrayList<>();
                for (int i = 0; i < certificateArray.size(); i++) {
                    Map<String, Object> certificateMap = (Map<String, Object>) certificateArray.get(i);
                    certificate = new Certificate();
                    if (isUpdate) {
                        Integer id = (Integer) certificateMap.get("id");
                        certificate.setId(id);
                        detectionDataId = (Integer) certificateMap.get("carModelDataId");
                    }
                    certificate.setDetectionDataId(detectionDataId);
                    String cLfp = (String) certificateMap.get("cLfp");
                    String dJzdey = (String) certificateMap.get("dJzdey");
                    String dJzdyy = (String) certificateMap.get("dJzdyy");
                    String gZsdyy = (String) certificateMap.get("gZsdyy");
                    String jQxsb = (String) certificateMap.get("jQxsb");
                    String jQxxb = (String) certificateMap.get("jQxxb");
                    String sYbdsb = (String) certificateMap.get("sYbdsb");
                    String xSzzmzp = (String) certificateMap.get("xSzzmzp");
                    String xXzclzp = (String) certificateMap.get("xXzclzp");
                    String xXzfyzp = (String) certificateMap.get("xXzfyzp");
                    //新增字段
                    String name = (String) certificateMap.get("name");
                    String netImg = (String) certificateMap.get("netImg");
                    certificate.setCLfp(cLfp);
                    certificate.setDJzdey(dJzdey);
                    certificate.setDJzdyy(dJzdyy);
                    certificate.setGZsdyy(gZsdyy);
                    certificate.setJQxsb(jQxsb);
                    certificate.setJQxxb(jQxxb);
                    certificate.setSYbdsb(sYbdsb);
                    certificate.setXSzzmzp(xSzzmzp);
                    certificate.setXXzclzp(xXzclzp);
                    certificate.setXXzfyzp(xXzfyzp);
                    //新增字段
                    certificate.setName(name);
                    certificate.setNetImg(netImg);
                    list.add(certificate);
                }
                saveBatch(list);
                return list;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            Map<String, Object> certificateMap = (Map<String, Object>) detectionDataMap.get("certificate");
            certificate = new Certificate();
            if (isUpdate) {
                Integer id = (Integer) certificateMap.get("id");
                certificate.setId(id);
                detectionDataId = (Integer) certificateMap.get("carModelDataId");
            }
            certificate.setDetectionDataId(detectionDataId);
            String cLfp = (String) certificateMap.get("cLFP");
            String dJzdey = (String) certificateMap.get("dJZDEY");
            String dJzdyy = (String) certificateMap.get("dJZDYY");
            String gZsdyy = (String) certificateMap.get("gZSDYY");
            String jQxsb = (String) certificateMap.get("jQXSB");
            String jQxxb = (String) certificateMap.get("jQXXB");
            String sYbdsb = (String) certificateMap.get("sYBDSB");
            String xSzzmzp = (String) certificateMap.get("xSZZMZP");
            String xXzclzp = (String) certificateMap.get("xXZCLZP");
            String xXzfyzp = (String) certificateMap.get("xXZFYZP");
            //新增字段
            String name = (String) certificateMap.get("name");
            String netImg = (String) certificateMap.get("netImg");
            certificate.setCLfp(cLfp);
            certificate.setDJzdey(dJzdey);
            certificate.setDJzdyy(dJzdyy);
            certificate.setGZsdyy(gZsdyy);
            certificate.setJQxsb(jQxsb);
            certificate.setJQxxb(jQxxb);
            certificate.setSYbdsb(sYbdsb);
            certificate.setXSzzmzp(xSzzmzp);
            certificate.setXXzclzp(xXzclzp);
            certificate.setXXzfyzp(xXzfyzp);
            //新增字段
            certificate.setName(name);
            certificate.setNetImg(netImg);
            save(certificate);
            return certificate;
        }
        return null;
    }
}
