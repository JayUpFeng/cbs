package com.jinjike.cbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.model.Records;

/**
 * @作者：zhanghe
 * @时间：2021-02-26 18:23:21
 * @注释：
 */
public interface IRecordsService extends IService<Records> {
    ResponseData getRecords(String orderNo);

    ResponseData updateRecords(String json);

    ResponseData updateBasicInfo(String json);

    ResponseData deleteRecords(String json);
}
