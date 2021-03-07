package com.jinjike.cbs.service;

import com.jinjike.cbs.model.Certificate;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface ICertificateService extends IService<Certificate> {

    Object createCertificate(Map<String, Object> detectionDataMap, Integer detectionDataId, boolean isUpdate);
}
