package com.jinjike.cbs.controller;

import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.service.IDataInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者：zhanghe
 * @时间：2021-03-26 09:41:33
 * @注释：
 */
@RestController
@RequestMapping("/cbs")
public class DataInfoController {
    @Autowired
    private IDataInfoService dataInfoService;

    /**
     * 删除指定的图片
     *
     * @param json
     * @return
     */
    @PostMapping("/data/delPic")
    public ResponseData delPic(@RequestBody String json) {
        try {
            dataInfoService.delPic(json);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
        return ResponseData.success();
    }
}
