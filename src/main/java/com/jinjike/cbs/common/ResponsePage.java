package com.jinjike.cbs.common;

import lombok.Data;

/**
 * @作者：zhanghe
 * @时间：2021-02-28 20:17:23
 * @注释：
 */
@Data
public class ResponsePage extends ResponseData {
    private Integer msg;
    private Long total;
    private Long pageNum;
    private Long pages;
}
