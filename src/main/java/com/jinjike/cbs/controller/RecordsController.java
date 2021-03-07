package com.jinjike.cbs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.common.PageReq;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.common.ResponsePage;
import com.jinjike.cbs.model.BasicInfo;
import com.jinjike.cbs.service.IBasicInfoService;
import com.jinjike.cbs.service.IRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者：zhanghe
 * @时间：2021-02-26 18:21:28
 * @注释：
 */
@RestController
@RequestMapping("/cbs")
public class RecordsController {
    @Autowired
    private IRecordsService recordsService;
    @Autowired
    private IBasicInfoService basicInfoService;


    @PostMapping("/recordsList")
    public ResponsePage recordsList(@RequestBody PageReq pageReq) {
        IPage<BasicInfo> iPage = basicInfoService.pageList(new Page<>(pageReq.getPage(),pageReq.getLimit()),pageReq.getOrderNo(),pageReq.getVin());
        ResponsePage responsePage = new ResponsePage();
        responsePage.setCode(0);
        responsePage.setTotal(iPage.getTotal());
        responsePage.setPageNum(iPage.getCurrent());
        responsePage.setMsg(1);
        responsePage.setPages(iPage.getPages());
        responsePage.setData(iPage.getRecords());
        return responsePage;
    }

    @PostMapping("/getRecords")
    public ResponseData getRecords(@RequestBody String orderNo) {
        try {
            return recordsService.getRecords(orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
    }

    @PostMapping("/deleteRecords")
    public ResponseData deleteRecords(@RequestBody String json) {
        try {
            return recordsService.deleteRecords(json);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
    }
    @PostMapping("/updateBasicInfo")
    public ResponseData updateBasicInfo(@RequestBody String json) {
        try {
            return recordsService.updateBasicInfo(json);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
    }

//    @PostMapping("/updateRecords")
//    public ResponseData updateRecords(@RequestBody String json) {
//        try {
//            return recordsService.updateRecords(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return ResponseData.serverError();
//        }
//    }
    //    @PostMapping("/saveRecords")
//    public ResponseData saveRecords(String orderNo){
//        try {
//            return recordsService.saveRecords(orderNo);
//        }catch (Exception e){
//            e.printStackTrace();
//            return ResponseData.serverError();
//        }
//    }
}
