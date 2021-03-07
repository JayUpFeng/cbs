package com.jinjike.cbs.service;

import com.alibaba.fastjson.JSONArray;
import com.jinjike.cbs.model.FilingPics;
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
public interface IFilingPicsService extends IService<FilingPics> {

    List<FilingPics> saveFilingPics(JSONArray filingPics, Integer valuationDataId,boolean isUpdate);
}
