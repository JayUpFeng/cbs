package com.jinjike.cbs.controller;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chaboshi.builder.CBSBuilder;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.common.PageReq;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.common.ResponsePage;
import com.jinjike.cbs.model.BasicInfo;
import com.jinjike.cbs.model.Records;
import com.jinjike.cbs.model.SysProperties;
import com.jinjike.cbs.service.IBasicInfoService;
import com.jinjike.cbs.service.IRecordsService;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @作者：zhanghe
 * @时间：2021-02-26 18:21:28
 * @注释：
 */
@RestController
@RequestMapping("/cbs")
public class RecordsController {
    @Value("${excelPath}")
    private String excelPath;
    @Autowired
    private IRecordsService recordsService;
    @Autowired
    private RedissonClient redissonClient;

    /**
     * 根据订单id获取数据
     *
     * @param orderNo
     * @return
     */
    @PostMapping("/getRecords")
    public ResponseData getRecords(@RequestBody String orderNo) {
        Map<String, Object> jsonOrderNo = (Map<String, Object>) JSONObject.parse(orderNo);
        String key = (String) jsonOrderNo.get("orderNo");
        RLock lock = redissonClient.getLock("lock:"+key);
        try {
            if (lock.tryLock(30, 30, TimeUnit.SECONDS)) {
                RBucket<Integer> consumeCount = redissonClient.getBucket("seed:"+key);
                if (!consumeCount.isExists() || consumeCount.get() == 0) {
                    return recordsService.getRecords(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return ResponseData.serverError();
    }

    /**
     * 删除记录
     *
     * @param json
     * @return
     */
    @PostMapping("/deleteRecords")
    public ResponseData deleteRecords(@RequestBody String json) {
        try {
            return recordsService.deleteRecords(json);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseData.serverError();
        }
    }

@RequestMapping("/uploadCarExcel")
@ResponseBody
public Map uploadCarExcel(@RequestPart("file") MultipartFile multipartFile, HttpServletRequest request) {
    String name = multipartFile.getOriginalFilename();
    request.getSession().setAttribute("upFile", name);
    String fileSavePath = excelPath + UUID.randomUUID().toString().replaceAll("-", "");
    try {
        multipartFile.transferTo(new File(fileSavePath + name));
    } catch (Exception e) {
        e.printStackTrace();
    }
    File file = new File(fileSavePath + name);
    AtomicInteger success = new AtomicInteger(0);
    AtomicInteger failed = new AtomicInteger(0);
    StringBuilder builder=new StringBuilder();
    Map<String, Object> m = new HashMap<>();
    try {
        ImportParams params = new ImportParams();
        params.setTitleRows(0);
        params.setHeadRows(1);
        List<Records> result = ExcelImportUtil.importExcel(file, Records.class, params);
        if (result != null && !result.isEmpty()) {
            //记录excel中重复的车架号，如果有则失败条数+1，如果没有则保存入库
            Map<String,Integer> frameMap=new HashMap<>();
            for (int i = 0; i < result.size(); i++) {
                Records records = result.get(i);
                String orderNo = records.getOrderNo();
                if (orderNo != null && !"null".equals(orderNo)) {
                    if (frameMap.containsKey(orderNo)){
                        //失败条数增1
                        failed.incrementAndGet();
                        builder.append(orderNo).append(",");
                    }else{
                        frameMap.put(orderNo,0);
                        //1、记录导入成功数量和失败数量
                        //2、车架号和数据库一样，不导入，excel中车架号重复，不导入
                        //数据库中有记录，不导入
                        int count = recordsService.count(new LambdaQueryWrapper<Records>().eq(Records::getOrderNo, orderNo));
                        if (count>0){
                            //失败条数增1
                            failed.incrementAndGet();
                            builder.append(orderNo).append(",");
                        }else{
                            ResponseData responseData = recordsService.saveExcelData(orderNo);
                            Integer code = responseData.getCode();
                            if (code!=0){
                                failed.incrementAndGet();
                                builder.append(orderNo).append(",");
                            }else{
                                success.incrementAndGet();
                            }
                        }
                    }
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        m.put("msg", "后台服务器异常，请联系管理员！");
    }
    String msg = builder.toString();
    Integer count=success.get();
    if (!StringUtils.isEmpty(msg)){
        if (count>0){
            m.put("msg",count+"条导入成功！ "+ "订单编号："+msg+"导入失败！");
        }else{
            m.put("msg","订单编号："+msg+"导入失败！");
        }
    }
    m.put("code", 0);
    m.put("success", count);
    m.put("failed", failed.get());
    return m;
}

}
