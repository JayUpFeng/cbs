package com.jinjike.cbs.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.model.BasicInfo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface IBasicInfoService extends IService<BasicInfo> {

    BasicInfo createBasicInfo(Map<String, Object> detectionDataMap, Integer detectionDataId,boolean isUpdate,String orderNo,String dealerBuyPrice);

    IPage<BasicInfo> pageList(Page<BasicInfo> objectPage, String orderNo, String vin);
}
