package com.jinjike.cbs.service;

import com.alibaba.fastjson.JSONArray;
import com.jinjike.cbs.model.MonthPrice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface IMonthPriceService extends IService<MonthPrice> {

    List<MonthPrice> savePrice(JSONArray monthsAgoPrice, Integer valuationDataId,Integer type,boolean isUpdate);
}
