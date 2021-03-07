package com.jinjike.cbs.common;

import lombok.Data;

/**
 * @作者：zhanghe
 * @时间：2021-03-06 09:39:38
 * @注释：
 */
@Data
public class PageReq {
    private Integer page;
    private Integer limit;
    private String orderNo;
    private String vin;
}
