package com.jinjike.cbs.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.common.PageReq;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.common.ResponsePage;
import com.jinjike.cbs.model.BasicInfo;
import com.jinjike.cbs.service.IBasicInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @作者：zhanghe
 * @时间：2021-03-26 09:42:33
 * @注释：
 */
@RestController
@RequestMapping("/cbs")
public class BasicInfoController {
    @Autowired
    private IBasicInfoService basicInfoService;
    /**
     * 列表页面
     * @param pageReq
     * @return
     */
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

    /**
     * 更新basicinfo
     * @param json
     * @return
     */
    @PostMapping("/updateBasicInfo")
    public ResponseData updateBasicInfo(@RequestBody String json) {
        try {
            return basicInfoService.updateBasicInfo(json);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
    }
}
