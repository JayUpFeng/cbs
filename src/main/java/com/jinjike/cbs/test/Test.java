package com.jinjike.cbs.test;

import com.chaboshi.builder.CBSBuilder;

import java.util.HashMap;

/**
 * @作者：zhanghe
 * @时间：2021-02-24 21:00:36
 * @注释：
 */
public class Test {
    public static void newCBSBuilder() {
        String userId = "1956635";
        String keySecret = "aac362b07080096119b81609b0e3d93e";
//        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, false);
        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, true);
        //获取所有车系
//        getCarSeries(cbsBuilder);
        //获取⻋型List
//        getCarType(cbsBuilder);
        //获取估价城市
//        getEvaluateCity(cbsBuilder);
        //宝骏
//        createOrder1(cbsBuilder);
        //奔腾
//        createOrder2(cbsBuilder);
        //查看报告
//        getReport(cbsBuilder,"0775377c130d4add9107d786f200ded3");
//        getReport(cbsBuilder,"0f709d73c31648d8a37ddcdba4b83736");
//        getReport(cbsBuilder,"0775377c130d4add9107d786f200ded3");
//        getReport(cbsBuilder,"585fce4b0a8e4b6f93c1caec2f296f39");
//        String report = getReport(cbsBuilder, "14a6a54254b84eb68892978b337c020d");
//        String report = getReport(cbsBuilder, "2aade2e9c31149998cd26ca35a08da3a");
//        String report = getReport(cbsBuilder, "79b5c8c1d03e4832a59018abe74d3570");
//        String report = getReport(cbsBuilder, "f1572b282cdd48f7bd993b4c1d642f40");
//        String report = getReport(cbsBuilder, "ddb3d550429e4144b6390301f2030918");
//        String report = getReport(cbsBuilder, "585fce4b0a8e4b6f93c1caec2f296f39");
        String report = getReport(cbsBuilder, "0f709d73c31648d8a37ddcdba4b83736");
        System.out.println(report);


        //获取订单
//        String data = cbsBuilder.sendPost("/valuation/customize/order/reportJSON", params);
    }
    //获取估价城市
    public static void getEvaluateCity(CBSBuilder cbsBuilder){
        String city = cbsBuilder.sendGet("/evaluate/getallcity");
        System.out.println(city);
    }
    public static String getReport(CBSBuilder cbsBuilder, String orderNo){
        HashMap<String, Object> params = new HashMap<String, Object>();
        //宝骏
//        params.put("orderno","2aade2e9c31149998cd26ca35a08da3a");
        //{"Code":"0","Message":"request success","data":{"orderNo":"2aade2e9c31149998cd26ca35a08da3a"}}
        //{"Code":"0","Message":"request success","data":{"orderNo":"79b5c8c1d03e4832a59018abe74d3570"}}
        params.put("orderno",orderNo);
        String post = cbsBuilder.sendPost("/valuation/customize/order/reportJSON", params);
//        System.out.println(post);
        return post;
    }
    public static void getCarSeries(CBSBuilder cbsBuilder){
        HashMap<String, Object> params = new HashMap<String, Object>();
        //宝骏
//        params.put("brandid",445);
        //奔腾
        params.put("brandid",442);
        String data = cbsBuilder.sendGet("/support/getseries", params);
        System.out.println(data);
    }
    public static void getCarType(CBSBuilder cbsBuilder){
        HashMap<String, Object> params = new HashMap<String, Object>();
        //宝骏
//        params.put("seriesid",7533);
        //奔腾
        params.put("seriesid",7487);
        String data = cbsBuilder.sendGet("/support/getmodels", params);
        System.out.println(data);
    }
    //宝骏
    public static  void createOrder1(CBSBuilder cbsBuilder){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("vin", "LZWADAGA2JB835260");
        params.put("carmodelid", 326769);
        //四平市
        params.put("carlicenselocationid", 948);
        params.put("carlicensetime", "2018-11-27");
        params.put("displaymileage", 48143);
        params.put("usageproperty", false);
        params.put("carcolor", "白色");
        params.put("dealnums", 0);
        params.put("callbackurl", "http://www.baidu.com");
        params.put("contactname", "宫艳霞");
        params.put("phone", "13204310015");
        //长春市
        params.put("locatedcity", "98");
        params.put("address", "长春市");
        params.put("skuno", "22ac337d845a4835b7ade88c2620fb11");
        //创建订单
        String data = cbsBuilder.sendPost("/valuation/customize/order/create", params);
        System.out.println(data);
    }
    //奔腾
    public static  void createOrder2(CBSBuilder cbsBuilder){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("vin", "LFP83APC1K1D88551");
        //292021  292010 292006

        params.put("carmodelid", 292021);
//        params.put("carmodelid", 292010);
//        params.put("carmodelid", 292006);
        //松原市
        params.put("carlicenselocationid", 969);
        params.put("carlicensetime", "2019-07-08");
        params.put("displaymileage", 13206);
        params.put("usageproperty", false);
        params.put("carcolor", "白色");
        params.put("dealnums", 0);
        params.put("callbackurl", "http://www.baidu.com");
        params.put("contactname", "宫艳霞");
//        params.put("contactname", "宫2");
        params.put("phone", "13204310015");
//        params.put("phone", "13204311234");
        //长春市
        params.put("locatedcity", "98");
        params.put("address", "长春市");
        params.put("skuno", "22ac337d845a4835b7ade88c2620fb11");
        //创建订单
        String data = cbsBuilder.sendPost("/valuation/customize/order/create", params);
        System.out.println(data);
    }

    public static void main(String[] args) {
        newCBSBuilder();
//        String userId = "1956635";
//        String keySecret = "aac362b07080096119b81609b0e3d93e";
//        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, false);
////        CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, true);
//        getHtml(cbsBuilder);

    }
    public static void getHtml(CBSBuilder cbsBuilder){
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("orderno", "2aade2e9c31149998cd26ca35a08da3a");
//        params.put("pagetype", 0);
//        params.put("pagetype", 1);
        String post = cbsBuilder.sendPost("/valuation/customize/order/report", params);
        System.out.println(post);
    }
}
