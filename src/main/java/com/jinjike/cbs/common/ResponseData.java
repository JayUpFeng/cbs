package com.jinjike.cbs.common;

import lombok.Data;

/**
 * @作者：zhanghe
 * @时间：2021-02-26 18:30:26
 * @注释：
 */
@Data
public class ResponseData {
    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";

    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";

    public static final Integer DEFAULT_SUCCESS_CODE = 0;

    //请求错误
    public static final Integer DEFAULT_BAD_REQUEST_CODE = 400;

    //服务器错误
    public static final Integer DEFAULT_ERROR_CODE = 500;


    /**
     * 响应状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 响应对象
     */
    private Object data;

    public ResponseData() {
    }

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseData success() {
        return new ResponseData(DEFAULT_SUCCESS_CODE, "success");
    }

    public static ResponseData success(Object data) {
        return new ResponseData(DEFAULT_SUCCESS_CODE, "success", data);
    }

    public static ResponseData error(String message) {
        return new ResponseData(DEFAULT_BAD_REQUEST_CODE, message);
    }
    public static ResponseData badReq() {
        return new ResponseData(DEFAULT_BAD_REQUEST_CODE, "bad request!");
    }

    public static ResponseData serverError() {
        return new ResponseData(DEFAULT_ERROR_CODE, "server error!");
    }

}
