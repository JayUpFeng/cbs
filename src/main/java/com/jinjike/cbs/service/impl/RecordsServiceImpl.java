package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chaboshi.builder.CBSBuilder;
import com.jinjike.cbs.common.ResponseData;
import com.jinjike.cbs.mapper.*;
import com.jinjike.cbs.model.*;
import com.jinjike.cbs.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @作者：zhanghe
 * @时间：2021-02-26 18:24:03
 * @注释：
 */
@Service
@Slf4j
public class RecordsServiceImpl extends ServiceImpl<RecordsMapper, Records> implements IRecordsService {
    @Autowired
    private CarModelDataMapper carModelDataMapper;
    @Autowired
    private DetectionDataMapper detectionDataMapper;
    @Autowired
    private MaintenanceDataMapper maintenanceDataMapper;
    @Autowired
    private RiskControlDataMapper riskControlDataMapper;
    @Autowired
    private ValuationDataMapper valuationDataMapper;
    @Autowired
    private ISysPropertiesService sysPropertiesService;
    @Autowired
    private IRecordsService recordsService;

    //CarModelData
    @Autowired
    private IBaseConfigService baseConfigService;
    @Autowired
    private IHightechConfigService hightechConfigService;
    @Autowired
    private ILightConfigService lightConfigService;
    @Autowired
    private IMediaConfigService mediaConfigService;
    @Autowired
    private IExternalConfigService externalConfigService;
    @Autowired
    private IControlConfigService controlConfigService;
    @Autowired
    private ISafetyConfigService safetyConfigService;
    @Autowired
    private IGearboxAndWheelConfigService gearboxAndWheelConfigService;
    @Autowired
    private IEngineConfigService engineConfigService;
    @Autowired
    private ICarBodyConfigService carBodyConfigService;

    //DetectionData
    @Autowired
    private IBasicInfoService basicInfoService;
    @Autowired
    private IDataInfoService dataInfoService;
    @Autowired
    private ICertificateService certificateService;
    @Autowired
    private IEvaluationInfoService evaluationInfoService;
    @Autowired
    private IDescsService descsService;
    @Autowired
    private ICoordinateService coordinateService;

    //MaintenanceData
    @Autowired
    private IMaintenanceDataService maintenanceDataService;
    @Autowired
    private IRepairRecordsService repairRecordsService;


    //RiskControlData
    @Autowired
    private IRiskControlDataService riskControlDataService;
    //ValuationData
    @Autowired
    private IValuationDataService valuationDataService;
    @Autowired
    private IFilingPicsService filingPicsService;
    @Autowired
    private IPicsRelevanceComponetService picsRelevanceComponetService;
    @Autowired
    private IMonthPriceService monthPriceService;
    @Value("${env}")
    private boolean env;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseData getRecords(String orderNo) {
        //从basic_info查询状态，如果是0则不允许查数据
        BasicInfo one = basicInfoService.getOne(new LambdaQueryWrapper<BasicInfo>().select(BasicInfo::getStatus).eq(BasicInfo::getOrderNo, orderNo));
        if (one!=null){
            Integer status = one.getStatus();
            if (status == 0){
                return ResponseData.success("无法查询数据！");
            }
        }
        int count = recordsService.count(new LambdaQueryWrapper<Records>().eq(Records::getOrderNo, orderNo));
        //从数据库里面查询数据
        if (count > 0) {
            Records records = new Records();
            CarModelData carModelData = getCarModelData(orderNo);
            DetectionData detectionData = getDetectionData(orderNo);
            MaintenanceData maintenanceData = getMaintenanceData(orderNo);
            RiskControlData riskControlData = getRiskControlData(orderNo);
            ValuationData valuationData = getValuationData(orderNo);
            records.setCarModelData(carModelData);
            records.setDetectionData(detectionData);
            records.setMaintenanceData(maintenanceData);
            records.setRiskControlData(riskControlData);
            records.setValuationData(valuationData);
            records.setOrderNo(orderNo);
            if (detectionData != null) {
                BasicInfo basicInfo = detectionData.getBasicInfo();
                if (basicInfo != null) {
                    records.setVin(basicInfo.getVin());
                }
            }
            return ResponseData.success(records);
        } else {
            //保存数据到数据库
            List<SysProperties> list = sysPropertiesService.list();
            if (list != null && !list.isEmpty()) {
                SysProperties sysProperties = list.get(0);
                String json = "";
                if (sysProperties != null) {
                    String userId = sysProperties.getUserId();
                    String keySecret = sysProperties.getKeySecret();
                    Integer priceType = sysProperties.getPriceType();
                    CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, env);
                    json = getReport(cbsBuilder, orderNo,priceType);
//            String json = "{\"Code\":\"0\",\"Message\":\"request success\",\"data\":{\"carModelData\":{\"baseConfig\":null,\"carBodyConfig\":null,\"controlConfig\":null,\"engineConfig\":null,\"externalConfig\":null,\"gearboxAndWheelConfig\":null,\"hightechConfig\":null,\"lightConfig\":null,\"mediaConfig\":null,\"safetyConfig\":null},\"detectionData\":{\"basicInfo\":{\"carLevel\":\"R\",\"carNo\":\"新G5C6M6\",\"engineNo\":null,\"evaluateContent\":null,\"extend\":null,\"leftPicture\":null,\"milePicture\":null,\"modelName\":\"荣威荣威E502013款 纯电动车\",\"plateYear\":\"2018-02-26\",\"vin\":\"LSVCA2A49CN046373\",\"vinPicture\":null,\"watchMile\":51.3145},\"certificate\":null,\"data\":[{\"componmentId\":783,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后车门内部及隔音棉\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":780,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前车门内部及隔音棉\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":784,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后车门内部及隔音棉\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":782,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前车门内部及隔音棉\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":800,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":799,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":798,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":797,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":789,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右B柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":787,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左B柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":788,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右A柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":786,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左A柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":781,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板横梁内部\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":263,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左D柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":302,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后轮旋\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":46,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后减震器座\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":30,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后翼子板\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":27,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后门框边缘\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":774,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧门槛线束\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":772,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧门槛内部空腔\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":22,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧上边梁\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":21,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左C柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":8,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左B柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":15,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前门框边缘\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":241,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左A柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":93,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前轮旋\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":796,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱底板线束\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":200,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后防撞梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":49,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后纵梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":48,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后纵梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":50,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"备胎槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":343,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱后遮物板(铁质)\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":44,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱门框边缘\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":381,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱底板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":43,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后围板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":42,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后翼子板内侧\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":41,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后翼子板内侧\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":306,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后翼子板导水槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":304,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后尾灯框架\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":449,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后窗台板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":301,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后翼子板导水槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":368,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后纵梁连接板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":366,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后纵梁连接板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":300,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后尾灯框架\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":83,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"车身大顶\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":94,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前轮旋\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":67,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右B柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":74,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前门框边缘\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":775,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧门槛线束\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":773,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧门槛内部空腔\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":68,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧下边梁\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":66,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右A柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":355,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"燃油箱及管路\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":145,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱保险盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":801,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱电器插接件\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":371,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱裸露金属部件\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":130,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱线束及标签\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":92,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"防火墙\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":102,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前减震器座\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":101,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前减震器座\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":104,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前翼子板骨架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":91,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前纵梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":103,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前翼子板骨架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":90,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前纵梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":96,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前吸能盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":95,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前吸能盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":175,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"前防撞梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":100,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"水箱框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":271,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前大灯框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":272,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前大灯框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":369,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前纵梁连接板\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":367,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前纵梁连接板\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":795,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右侧底板出风口\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":794,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左侧底板出风口\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":793,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"副驾座椅轨道及骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":792,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"主驾座椅轨道及骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":791,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"OBD插接件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":813,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"室内电器插接件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":812,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"室内保险丝盒\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":785,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"后排座椅骨架及海绵\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":297,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"副驾驶气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":165,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"车内顶棚\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":290,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"仪表台骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":163,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":437,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":416,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":484,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":482,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":434,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":338,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"膝部气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":286,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"驾驶员气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":170,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"转向管柱\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":169,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"点烟器座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":771,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板水堵\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":770,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板隔音胶及封边胶\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":776,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"驾驶舱裸露金属部件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":134,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":507,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":132,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"仪表台\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":790,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底盘铁质部件\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":324,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底板纵梁\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":323,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底板横梁\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":185,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"车身底板\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":63,\"descs\":[{\"coordinate\":null,\"description\":\"凹陷\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＜3cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后门框边缘\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":58,\"descs\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧上边梁\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":246,\"descs\":[{\"coordinate\":null,\"description\":\"钣金修复\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＞10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右C柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":264,\"descs\":[{\"coordinate\":null,\"description\":\"凹陷\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"5cm-10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右D柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":303,\"descs\":[{\"coordinate\":null,\"description\":\"变形\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＞10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后轮旋\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":47,\"descs\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后减震器座\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":52,\"descs\":[{\"coordinate\":null,\"description\":\"钣金修复\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"重度\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后翼子板\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":9,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧下边梁\",\"nameGroup\":\"左侧\",\"qmInfos\":[]}],\"evaluationInfo\":{\"buyPrice\":null,\"dealerBuyPrice\":null,\"dealerBuyPriceMax\":null,\"dealerBuyPriceMin\":null,\"personSoldPrice\":null,\"personSoldPriceMax\":null,\"personSoldPriceMin\":null,\"sellPrice\":23.49}},\"maintenanceData\":{\"brand\":null,\"carComponentRecordsFlag\":0,\"carConstructRecordsFlag\":0,\"carFireFlag\":0,\"carOutsideRecordsFlag\":0,\"carType\":null,\"carWaterFlag\":0,\"componentAnalyzeRepairRecords\":null,\"constructAnalyzeRepairRecords\":null,\"displacement\":null,\"effluentStandard\":null,\"lastMainTainTime\":null,\"lastRepairTime\":null,\"mainTainTimes\":null,\"makeDate\":null,\"makeReportDate\":null,\"manufacturer\":null,\"mileageEstimate\":0,\"mileageEveryYear\":null,\"mileageIsNormalFlag\":0,\"modelName\":null,\"normalRepairRecords\":null,\"outsideAnalyzeRepairRecords\":null,\"productionArea\":null,\"reportNo\":null,\"seriesName\":null,\"transmissionType\":null,\"updateTime\":null,\"vin\":null},\"orderNo\":\"2aade2e9c31149998cd26ca35a08da3a\",\"riskControlData\":{\"djz\":1,\"mp\":1,\"sfdy\":0,\"sftp\":0,\"sfty\":0,\"syxz\":0,\"violationCount\":null,\"xsz\":1},\"valuationData\":{\"carAge\":3,\"carColor\":\"白色\",\"carLicenseLocation\":\"松原\",\"carNo\":null,\"cbsEstimatedMiles\":51,\"dealNums\":0,\"dealerBuyPrice\":\"3.52\",\"dealerBuyPriceMax\":\"3.66\",\"dealerBuyPriceMin\":\"3.31\",\"engineNo\":null,\"filingPics\":null,\"firstPlateTime\":\"2018-02-26\",\"miles\":51,\"modelAnaly\":null,\"monthsAgoPrice\":[{\"date\":\"2020-08\",\"price\":\"3.63\"},{\"date\":\"2020-09\",\"price\":\"3.61\"},{\"date\":\"2020-10\",\"price\":\"3.59\"},{\"date\":\"2020-11\",\"price\":\"3.58\"},{\"date\":\"2020-12\",\"price\":\"3.56\"},{\"date\":\"2021-01\",\"price\":\"3.54\"}],\"monthsPrice\":[{\"date\":\"2021-03\",\"price\":\"3.51\"},{\"date\":\"2021-04\",\"price\":\"3.49\"},{\"date\":\"2021-05\",\"price\":\"3.47\"},{\"date\":\"2021-06\",\"price\":\"3.45\"},{\"date\":\"2021-07\",\"price\":\"3.44\"},{\"date\":\"2021-08\",\"price\":\"3.42\"}],\"newCarGuidePrice\":\"23.49\",\"personSoldPrice\":\"3.92\",\"personSoldPriceMax\":\"4.07\",\"personSoldPriceMin\":\"3.68\",\"picsRelevanceComponet\":null}}}";
                    if (StringUtils.isBlank(json)) {
                        return ResponseData.error("查博士网站未查询到该订单号数据：" + orderNo);
                    }
                }
                return saveRecords(json, orderNo);
            } else {
                return ResponseData.serverError();
            }
        }
    }

    private ValuationData getValuationData(String orderNo) {
        ValuationData valuationData = valuationDataMapper.selectOne(new LambdaQueryWrapper<ValuationData>().eq(ValuationData::getOrderNo, orderNo));
        if (valuationData != null) {
            Integer valuationDataId = valuationData.getId();
            List<FilingPics> filingPics = filingPicsService.list(new LambdaQueryWrapper<FilingPics>().eq(FilingPics::getValuationDataId, valuationDataId));
            PicsRelevanceComponet picsRelevanceComponetServiceOne = picsRelevanceComponetService.getOne(new LambdaQueryWrapper<PicsRelevanceComponet>().eq(PicsRelevanceComponet::getValuationDataId, valuationDataId));
            List<MonthPrice> monthsAgoPrice = monthPriceService.list(new LambdaQueryWrapper<MonthPrice>().eq(MonthPrice::getValuationDataId, valuationDataId).eq(MonthPrice::getType, 0));
            List<MonthPrice> monthsPrice = monthPriceService.list(new LambdaQueryWrapper<MonthPrice>().eq(MonthPrice::getValuationDataId, valuationDataId).eq(MonthPrice::getType, 1));
            valuationData.setFilingPics(filingPics);
            valuationData.setPicsRelevanceComponet(picsRelevanceComponetServiceOne);
            valuationData.setMonthsAgoPrice(monthsAgoPrice);
            valuationData.setMonthsPrice(monthsPrice);
            return valuationData;
        }
        return null;
    }

    private RiskControlData getRiskControlData(String orderNo) {
        return riskControlDataMapper.selectOne(new LambdaQueryWrapper<RiskControlData>().eq(RiskControlData::getOrderNo, orderNo));
    }

    private MaintenanceData getMaintenanceData(String orderNo) {
        MaintenanceData maintenanceData = maintenanceDataService.getOne(new LambdaQueryWrapper<MaintenanceData>().eq(MaintenanceData::getOrderNo, orderNo));
        if (maintenanceData != null) {
            Integer maintenanceDataId = maintenanceData.getId();
            List<RepairRecords> normalRepairRecords = repairRecordsService.list(new LambdaQueryWrapper<RepairRecords>().eq(RepairRecords::getMaintenanceDataId, maintenanceDataId).eq(RepairRecords::getKind, 0));
            List<RepairRecords> constructAnalyzeRepairRecords = repairRecordsService.list(new LambdaQueryWrapper<RepairRecords>().eq(RepairRecords::getMaintenanceDataId, maintenanceDataId).eq(RepairRecords::getKind, 1));
            List<RepairRecords> componentAnalyzeRepairRecords = repairRecordsService.list(new LambdaQueryWrapper<RepairRecords>().eq(RepairRecords::getMaintenanceDataId, maintenanceDataId).eq(RepairRecords::getKind, 2));
            List<RepairRecords> outsideAnalyzeRepairRecords = repairRecordsService.list(new LambdaQueryWrapper<RepairRecords>().eq(RepairRecords::getMaintenanceDataId, maintenanceDataId).eq(RepairRecords::getKind, 3));
            maintenanceData.setNormalRepairRecords(normalRepairRecords);
            maintenanceData.setConstructAnalyzeRepairRecords(constructAnalyzeRepairRecords);
            maintenanceData.setComponentAnalyzeRepairRecords(componentAnalyzeRepairRecords);
            maintenanceData.setOutsideAnalyzeRepairRecords(outsideAnalyzeRepairRecords);
            return maintenanceData;
        }
        return null;
    }

    private DetectionData getDetectionData(String orderNo) {
        DetectionData detectionData = detectionDataMapper.selectOne(new LambdaQueryWrapper<DetectionData>().eq(DetectionData::getOrderNo, orderNo));
        if (detectionData != null) {
            Integer detectionDataId = detectionData.getId();
            BasicInfo basicInfo = basicInfoService.getOne(new LambdaQueryWrapper<BasicInfo>().eq(BasicInfo::getDetectionDataId, detectionDataId));
            List<Certificate> certificateList = certificateService.list(new LambdaQueryWrapper<Certificate>().eq(Certificate::getDetectionDataId, detectionDataId));
            List<DataInfo> dataInfoList = dataInfoService.list(new LambdaQueryWrapper<DataInfo>().eq(DataInfo::getDetectionDataId, detectionDataId));
            if (dataInfoList != null && !dataInfoList.isEmpty()) {
                for (DataInfo dataInfo : dataInfoList) {
                    Integer dataInfoId = dataInfo.getId();
                    List<Descs> descsList = descsService.list(new LambdaQueryWrapper<Descs>().eq(Descs::getDataId, dataInfoId));
                    if (descsList != null && !descsList.isEmpty()) {
                        for (Descs descs : descsList) {
                            Integer descsId = descs.getId();
                            List<Coordinate> coordinateList = coordinateService.list(new LambdaQueryWrapper<Coordinate>().eq(Coordinate::getDescId, descsId));
                            if (coordinateList != null) {
                                descs.setCoordinate(coordinateList);
                            }
                            List<Descs> descriptionList = new ArrayList<>();
                            Descs de = new Descs();
                            de.setId(descsId);
                            de.setDataId(dataInfoId);
                            de.setDescription(descs.getDescription());
                            de.setCoordinate(coordinateList);
                            de.setPicture(descs.getPicture());
                            descriptionList.add(de);
                            descs.setDescriptionList(descriptionList);
                        }
                    }
                    dataInfo.setDescs(descsList);
                }
            }
            EvaluationInfo evaluationInfo = evaluationInfoService.getOne(new LambdaQueryWrapper<EvaluationInfo>().eq(EvaluationInfo::getDetectionDataId, detectionDataId));
            detectionData.setBasicInfo(basicInfo);
            detectionData.setCertificate(certificateList);
            detectionData.setEvaluationInfo(evaluationInfo);
            detectionData.setData(dataInfoList);
            return detectionData;
        }
        return null;
    }

    private CarModelData getCarModelData(String orderNo) {
        CarModelData carModelData = carModelDataMapper.selectOne(new LambdaQueryWrapper<CarModelData>().eq(CarModelData::getOrderNo, orderNo));
        Integer carModelDataId = carModelData.getId();
        BaseConfig baseConfig = baseConfigService.getOne(new LambdaQueryWrapper<BaseConfig>().eq(BaseConfig::getCarModelDataId, carModelDataId));
        HightechConfig hightechConfig = hightechConfigService.getOne(new LambdaQueryWrapper<HightechConfig>().eq(HightechConfig::getCarModelDataId, carModelDataId));
        LightConfig lightConfig = lightConfigService.getOne(new LambdaQueryWrapper<LightConfig>().eq(LightConfig::getCarModelDataId, carModelDataId));
        MediaConfig mediaConfig = mediaConfigService.getOne(new LambdaQueryWrapper<MediaConfig>().eq(MediaConfig::getCarModelDataId, carModelDataId));
        ExternalConfig externalConfig = externalConfigService.getOne(new LambdaQueryWrapper<ExternalConfig>().eq(ExternalConfig::getCarModelDataId, carModelDataId));
        ControlConfig controlConfig = controlConfigService.getOne(new LambdaQueryWrapper<ControlConfig>().eq(ControlConfig::getCarModelDataId, carModelDataId));
        SafetyConfig safetyConfig = safetyConfigService.getOne(new LambdaQueryWrapper<SafetyConfig>().eq(SafetyConfig::getCarModelDataId, carModelDataId));
        GearboxAndWheelConfig gearboxAndWheelConfig = gearboxAndWheelConfigService.getOne(new LambdaQueryWrapper<GearboxAndWheelConfig>().eq(GearboxAndWheelConfig::getCarModelDataId, carModelDataId));
        EngineConfig engineConfig = engineConfigService.getOne(new LambdaQueryWrapper<EngineConfig>().eq(EngineConfig::getCarModelDataId, carModelDataId));
        CarBodyConfig carBodyConfig = carBodyConfigService.getOne(new LambdaQueryWrapper<CarBodyConfig>().eq(CarBodyConfig::getCarModelDataId, carModelDataId));
        carModelData.setBaseConfig(baseConfig);
        carModelData.setHightechConfig(hightechConfig);
        carModelData.setLightConfig(lightConfig);
        carModelData.setMediaConfig(mediaConfig);
        carModelData.setExternalConfig(externalConfig);
        carModelData.setControlConfig(controlConfig);
        carModelData.setSafetyConfig(safetyConfig);
        carModelData.setGearboxAndWheelConfig(gearboxAndWheelConfig);
        carModelData.setEngineConfig(engineConfig);
        carModelData.setCarBodyConfig(carBodyConfig);
        return carModelData;
    }

    public ResponseData saveRecords(String json, String orderNo) {
        Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(json);
        String code = (String) parse.get("Code");
        if (!"0".equals(code)) {
            String Message = (String) parse.get("Message");
            return ResponseData.error("查博士网站返回错误信息：" + Message);
        }
        Map<String, Object> data = (Map<String, Object>) parse.get("data");
        Map<String, Object> carModelDataMap = (Map<String, Object>) data.get("carModelData");
        Map<String, Object> detectionDataMap = (Map<String, Object>) data.get("detectionData");
        Map<String, Object> maintenanceDataMap = (Map<String, Object>) data.get("maintenanceData");
        Map<String, Object> riskControlDataMap = (Map<String, Object>) data.get("riskControlData");
        Map<String, Object> valuationDataMap = (Map<String, Object>) data.get("valuationData");
        CarModelData carModelData = saveCarModelData(carModelDataMap, orderNo);
        MaintenanceData maintenanceData = saveMaintenanceData(maintenanceDataMap, orderNo);
        RiskControlData riskControlData = saveRiskControlData(riskControlDataMap, orderNo);
        ValuationData valuationData = saveValuationData(valuationDataMap, orderNo);
        String dealerBuyPrice = valuationData.getDealerBuyPrice();
        DetectionData detectionData = saveDetectionData(detectionDataMap, orderNo, dealerBuyPrice);
        Records records = new Records();
        records.setOrderNo(orderNo);
        records.setCarModelData(carModelData);
        records.setDetectionData(detectionData);
        records.setMaintenanceData(maintenanceData);
        records.setRiskControlData(riskControlData);
        records.setValuationData(valuationData);
        if (detectionData != null) {
            BasicInfo basicInfo = detectionData.getBasicInfo();
            if (basicInfo != null) {
                records.setVin(basicInfo.getVin());
            }
        }
        recordsService.save(records);
        return ResponseData.success(records);
    }

    //TODO 没有用到这个方法
    @Override
    public ResponseData updateRecords(String json) {
        Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(json);
        Map<String, Object> data = (Map<String, Object>) parse.get("data");
        Map<String, Object> orderNoMap = (Map<String, Object>) data.get("orderNo");
        Map<String, Object> carModelDataMap = (Map<String, Object>) data.get("carModelData");
        Map<String, Object> detectionDataMap = (Map<String, Object>) data.get("detectionData");
        Map<String, Object> maintenanceDataMap = (Map<String, Object>) data.get("maintenanceData");
        Map<String, Object> riskControlDataMap = (Map<String, Object>) data.get("riskControlData");
        Map<String, Object> valuationDataMap = (Map<String, Object>) data.get("valuationData");
        String orderNo = (String) orderNoMap.get("orderNo");
        updateModelData(carModelDataMap);
        updateDetectionData(detectionDataMap, orderNo);
        updateMaintenanceData(maintenanceDataMap);
        updateRiskControlData(riskControlDataMap);
        updateValuationData(valuationDataMap);
        return ResponseData.success();
    }


    @Override
    @Transactional
    public ResponseData deleteRecords(String json) {
        Map<String, Object> parse = (Map<String, Object>) JSONObject.parse(json);
        String orderNo = (String) parse.get("orderNo");
        if (orderNo != null) {
            recordsService.remove(new LambdaQueryWrapper<Records>().eq(Records::getOrderNo,orderNo));
            delCarModelData(orderNo);
            delDetectionData(orderNo);
            delMaintenanceData(orderNo);
            delRiskControlData(orderNo);
            delValuationData(orderNo);
        }
        return ResponseData.success();
    }

    @Override
    public ResponseData saveExcelData(String orderNo) {
        //保存数据到数据库
        List<SysProperties> list = sysPropertiesService.list();
        if (list != null && !list.isEmpty()) {
            SysProperties sysProperties = list.get(0);
            String json = "";
            if (sysProperties != null) {
                String userId = sysProperties.getUserId();
                String keySecret = sysProperties.getKeySecret();
                Integer priceType = sysProperties.getPriceType();
                CBSBuilder cbsBuilder = CBSBuilder.newCBSBuilder(userId, keySecret, env);
                json = getReport(cbsBuilder, orderNo,priceType);
//            String json = "{\"Code\":\"0\",\"Message\":\"request success\",\"data\":{\"carModelData\":{\"baseConfig\":null,\"carBodyConfig\":null,\"controlConfig\":null,\"engineConfig\":null,\"externalConfig\":null,\"gearboxAndWheelConfig\":null,\"hightechConfig\":null,\"lightConfig\":null,\"mediaConfig\":null,\"safetyConfig\":null},\"detectionData\":{\"basicInfo\":{\"carLevel\":\"R\",\"carNo\":\"新G5C6M6\",\"engineNo\":null,\"evaluateContent\":null,\"extend\":null,\"leftPicture\":null,\"milePicture\":null,\"modelName\":\"荣威荣威E502013款 纯电动车\",\"plateYear\":\"2018-02-26\",\"vin\":\"LSVCA2A49CN046373\",\"vinPicture\":null,\"watchMile\":51.3145},\"certificate\":null,\"data\":[{\"componmentId\":783,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后车门内部及隔音棉\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":780,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前车门内部及隔音棉\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":784,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后车门内部及隔音棉\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":782,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前车门内部及隔音棉\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":800,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":799,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":798,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":797,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后安全带插座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":789,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右B柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":787,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左B柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":788,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右A柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":786,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左A柱夹层及周边\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":781,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板横梁内部\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":263,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左D柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":302,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后轮旋\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":46,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后减震器座\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":30,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后翼子板\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":27,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左后门框边缘\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":774,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧门槛线束\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":772,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧门槛内部空腔\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":22,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧上边梁\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":21,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左C柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":8,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左B柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":15,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前门框边缘\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":241,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左A柱\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":93,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左前轮旋\",\"nameGroup\":\"左侧\",\"qmInfos\":[]},{\"componmentId\":796,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱底板线束\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":200,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后防撞梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":49,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后纵梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":48,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后纵梁\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":50,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"备胎槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":343,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱后遮物板(铁质)\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":44,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱门框边缘\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":381,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"行李箱底板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":43,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后围板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":42,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后翼子板内侧\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":41,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后翼子板内侧\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":306,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后翼子板导水槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":304,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后尾灯框架\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":449,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"后窗台板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":301,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后翼子板导水槽\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":368,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后纵梁连接板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":366,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"右后纵梁连接板\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":300,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":2,\"nameComponment\":\"左后尾灯框架\",\"nameGroup\":\"后部\",\"qmInfos\":[]},{\"componmentId\":83,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"车身大顶\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":94,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前轮旋\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":67,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右B柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":74,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右前门框边缘\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":775,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧门槛线束\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":773,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧门槛内部空腔\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":68,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧下边梁\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":66,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右A柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":355,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"燃油箱及管路\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":145,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱保险盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":801,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱电器插接件\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":371,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱裸露金属部件\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":130,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"机舱线束及标签\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":92,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"防火墙\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":102,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前减震器座\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":101,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前减震器座\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":104,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前翼子板骨架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":91,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前纵梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":103,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前翼子板骨架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":90,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前纵梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":96,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前吸能盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":95,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前吸能盒\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":175,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"前防撞梁\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":100,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"水箱框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":271,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前大灯框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":272,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前大灯框架\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":369,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"左前纵梁连接板\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":367,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":4,\"nameComponment\":\"右前纵梁连接板\",\"nameGroup\":\"前部\",\"qmInfos\":[]},{\"componmentId\":795,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右侧底板出风口\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":794,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左侧底板出风口\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":793,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"副驾座椅轨道及骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":792,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"主驾座椅轨道及骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":791,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"OBD插接件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":813,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"室内电器插接件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":812,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"室内保险丝盒\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":785,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"后排座椅骨架及海绵\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":297,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"副驾驶气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":165,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"车内顶棚\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":290,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"仪表台骨架\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":163,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":437,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":416,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右前地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":484,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":482,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左后安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":434,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"右后安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":338,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"膝部气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":286,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"驾驶员气囊\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":170,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"转向管柱\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":169,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"点烟器座\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":771,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板水堵\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":770,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"底板隔音胶及封边胶\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":776,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"驾驶舱裸露金属部件\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":134,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前安全带\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":507,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"左前地毯(含周边饰条/饰板)\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":132,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":5,\"nameComponment\":\"仪表台\",\"nameGroup\":\"内部\",\"qmInfos\":[]},{\"componmentId\":790,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底盘铁质部件\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":324,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底板纵梁\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":323,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"底板横梁\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":185,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":6,\"nameComponment\":\"车身底板\",\"nameGroup\":\"底部\",\"qmInfos\":[]},{\"componmentId\":63,\"descs\":[{\"coordinate\":null,\"description\":\"凹陷\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＜3cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后门框边缘\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":58,\"descs\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右侧上边梁\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":246,\"descs\":[{\"coordinate\":null,\"description\":\"钣金修复\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＞10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右C柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":264,\"descs\":[{\"coordinate\":null,\"description\":\"凹陷\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"5cm-10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右D柱\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":303,\"descs\":[{\"coordinate\":null,\"description\":\"变形\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"＞10cm\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后轮旋\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":47,\"descs\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"更换\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后减震器座\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":52,\"descs\":[{\"coordinate\":null,\"description\":\"钣金修复\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"重度\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":3,\"nameComponment\":\"右后翼子板\",\"nameGroup\":\"右侧\",\"qmInfos\":[]},{\"componmentId\":9,\"descs\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":[{\"coordinate\":null,\"description\":\"未见异常\",\"descriptionList\":null,\"picture\":null}],\"picture\":null}],\"id\":1,\"nameComponment\":\"左侧下边梁\",\"nameGroup\":\"左侧\",\"qmInfos\":[]}],\"evaluationInfo\":{\"buyPrice\":null,\"dealerBuyPrice\":null,\"dealerBuyPriceMax\":null,\"dealerBuyPriceMin\":null,\"personSoldPrice\":null,\"personSoldPriceMax\":null,\"personSoldPriceMin\":null,\"sellPrice\":23.49}},\"maintenanceData\":{\"brand\":null,\"carComponentRecordsFlag\":0,\"carConstructRecordsFlag\":0,\"carFireFlag\":0,\"carOutsideRecordsFlag\":0,\"carType\":null,\"carWaterFlag\":0,\"componentAnalyzeRepairRecords\":null,\"constructAnalyzeRepairRecords\":null,\"displacement\":null,\"effluentStandard\":null,\"lastMainTainTime\":null,\"lastRepairTime\":null,\"mainTainTimes\":null,\"makeDate\":null,\"makeReportDate\":null,\"manufacturer\":null,\"mileageEstimate\":0,\"mileageEveryYear\":null,\"mileageIsNormalFlag\":0,\"modelName\":null,\"normalRepairRecords\":null,\"outsideAnalyzeRepairRecords\":null,\"productionArea\":null,\"reportNo\":null,\"seriesName\":null,\"transmissionType\":null,\"updateTime\":null,\"vin\":null},\"orderNo\":\"2aade2e9c31149998cd26ca35a08da3a\",\"riskControlData\":{\"djz\":1,\"mp\":1,\"sfdy\":0,\"sftp\":0,\"sfty\":0,\"syxz\":0,\"violationCount\":null,\"xsz\":1},\"valuationData\":{\"carAge\":3,\"carColor\":\"白色\",\"carLicenseLocation\":\"松原\",\"carNo\":null,\"cbsEstimatedMiles\":51,\"dealNums\":0,\"dealerBuyPrice\":\"3.52\",\"dealerBuyPriceMax\":\"3.66\",\"dealerBuyPriceMin\":\"3.31\",\"engineNo\":null,\"filingPics\":null,\"firstPlateTime\":\"2018-02-26\",\"miles\":51,\"modelAnaly\":null,\"monthsAgoPrice\":[{\"date\":\"2020-08\",\"price\":\"3.63\"},{\"date\":\"2020-09\",\"price\":\"3.61\"},{\"date\":\"2020-10\",\"price\":\"3.59\"},{\"date\":\"2020-11\",\"price\":\"3.58\"},{\"date\":\"2020-12\",\"price\":\"3.56\"},{\"date\":\"2021-01\",\"price\":\"3.54\"}],\"monthsPrice\":[{\"date\":\"2021-03\",\"price\":\"3.51\"},{\"date\":\"2021-04\",\"price\":\"3.49\"},{\"date\":\"2021-05\",\"price\":\"3.47\"},{\"date\":\"2021-06\",\"price\":\"3.45\"},{\"date\":\"2021-07\",\"price\":\"3.44\"},{\"date\":\"2021-08\",\"price\":\"3.42\"}],\"newCarGuidePrice\":\"23.49\",\"personSoldPrice\":\"3.92\",\"personSoldPriceMax\":\"4.07\",\"personSoldPriceMin\":\"3.68\",\"picsRelevanceComponet\":null}}}";
                if (StringUtils.isBlank(json)) {
                    return ResponseData.error("查博士网站未查询到该订单号数据：" + orderNo);
                }
            }
           return saveRecords(json, orderNo);
        } else {
            return  ResponseData.serverError();
        }
    }

    public void delCarModelData(String orderNo) {
        CarModelData carModelData = carModelDataMapper.selectOne(new LambdaQueryWrapper<CarModelData>().eq(CarModelData::getOrderNo, orderNo));
        if (carModelData != null) {
            Integer carModelDataId = carModelData.getId();
            if (carModelDataId != null) {
                baseConfigService.remove(new LambdaQueryWrapper<BaseConfig>().eq(BaseConfig::getCarModelDataId, carModelDataId));
                hightechConfigService.remove(new LambdaQueryWrapper<HightechConfig>().eq(HightechConfig::getCarModelDataId, carModelDataId));
                lightConfigService.remove(new LambdaQueryWrapper<LightConfig>().eq(LightConfig::getCarModelDataId, carModelDataId));
                mediaConfigService.remove(new LambdaQueryWrapper<MediaConfig>().eq(MediaConfig::getCarModelDataId, carModelDataId));
                externalConfigService.remove(new LambdaQueryWrapper<ExternalConfig>().eq(ExternalConfig::getCarModelDataId, carModelDataId));
                controlConfigService.remove(new LambdaQueryWrapper<ControlConfig>().eq(ControlConfig::getCarModelDataId, carModelDataId));
                safetyConfigService.remove(new LambdaQueryWrapper<SafetyConfig>().eq(SafetyConfig::getCarModelDataId, carModelDataId));
                gearboxAndWheelConfigService.remove(new LambdaQueryWrapper<GearboxAndWheelConfig>().eq(GearboxAndWheelConfig::getCarModelDataId, carModelDataId));
                engineConfigService.remove(new LambdaQueryWrapper<EngineConfig>().eq(EngineConfig::getCarModelDataId, carModelDataId));
                carBodyConfigService.remove(new LambdaQueryWrapper<CarBodyConfig>().eq(CarBodyConfig::getCarModelDataId, carModelDataId));
                carModelDataMapper.deleteById(carModelDataId);
            }
        }
    }

    public void delDetectionData(String orderNo) {
        DetectionData detectionData = detectionDataMapper.selectOne(new LambdaQueryWrapper<DetectionData>().eq(DetectionData::getOrderNo, orderNo));
        if (detectionData != null) {
            Integer detectionDataId = detectionData.getId();
            if (detectionDataId != null) {
                basicInfoService.remove(new LambdaQueryWrapper<BasicInfo>().eq(BasicInfo::getDetectionDataId,detectionDataId));
                certificateService.remove(new LambdaQueryWrapper<Certificate>().eq(Certificate::getDetectionDataId, detectionDataId));
                List<DataInfo> list = dataInfoService.list(new LambdaQueryWrapper<DataInfo>().eq(DataInfo::getDetectionDataId, detectionDataId));
                if (list != null && !list.isEmpty()) {
                    List<Integer> dataInfoIds = list.stream().map(DataInfo::getId).collect(Collectors.toList());
                    if (!dataInfoIds.isEmpty()) {
                        List<Descs> descsList = descsService.list(new LambdaQueryWrapper<Descs>().in(Descs::getDataId, dataInfoIds));
                        List<Integer> descIds = descsList.stream().map(Descs::getId).collect(Collectors.toList());
                        if (!descIds.isEmpty()) {
                            coordinateService.remove(new LambdaQueryWrapper<Coordinate>().in(Coordinate::getDescId, descIds));
                        }
                        descsService.removeByIds(descIds);
                    }
                    dataInfoService.removeByIds(dataInfoIds);
                }
                evaluationInfoService.remove(new LambdaQueryWrapper<EvaluationInfo>().eq(EvaluationInfo::getDetectionDataId,detectionDataId));
            }
            detectionDataMapper.deleteById(detectionDataId);
        }
    }

    public void delMaintenanceData(String orderNo) {
        MaintenanceData maintenanceData = maintenanceDataService.getOne(new LambdaQueryWrapper<MaintenanceData>().eq(MaintenanceData::getOrderNo, orderNo));
        if (maintenanceData != null) {
            Integer maintenanceDataId = maintenanceData.getId();
            repairRecordsService.remove(new LambdaQueryWrapper<RepairRecords>().eq(RepairRecords::getMaintenanceDataId, maintenanceDataId));
            maintenanceDataService.removeById(maintenanceDataId);
        }
    }

    public void delRiskControlData(String orderNo) {
        RiskControlData riskControlData = riskControlDataService.getOne(new LambdaQueryWrapper<RiskControlData>().eq(RiskControlData::getOrderNo, orderNo));
        if (riskControlData != null) {
            riskControlDataService.removeById(riskControlData.getId());
        }
    }

    public void delValuationData(String orderNo) {
        ValuationData valuationData = valuationDataService.getOne(new LambdaQueryWrapper<ValuationData>().eq(ValuationData::getOrderNo, orderNo));
        if (valuationData != null) {
            Integer valuationDataId = valuationData.getId();
            List<FilingPics> filingPicsList = filingPicsService.list(new LambdaQueryWrapper<FilingPics>().eq(FilingPics::getValuationDataId, valuationDataId));
            if (filingPicsList != null && !filingPicsList.isEmpty()) {
                List<Integer> filingPicIds = filingPicsList.stream().map(FilingPics::getId).collect(Collectors.toList());
                if (!filingPicIds.isEmpty()) {
                    filingPicsService.removeByIds(filingPicIds);
                }
            }
            PicsRelevanceComponet picsRelevanceComponet = picsRelevanceComponetService.getOne(new LambdaQueryWrapper<PicsRelevanceComponet>().eq(PicsRelevanceComponet::getValuationDataId, valuationDataId));
            if (picsRelevanceComponet != null) {
                Integer id = picsRelevanceComponet.getId();
                picsRelevanceComponetService.removeById(id);
            }
            List<MonthPrice> monthPriceList = monthPriceService.list(new LambdaQueryWrapper<MonthPrice>().eq(MonthPrice::getValuationDataId, valuationDataId));
            if (monthPriceList != null) {
                List<Integer> ids = monthPriceList.stream().map(MonthPrice::getId).collect(Collectors.toList());
                if (!ids.isEmpty()) {
                    monthPriceService.removeByIds(ids);
                }
            }
            valuationDataService.removeById(valuationDataId);
        }
    }

    private void updateValuationData(Map<String, Object> valuationDataMap) {
        valuationDataService.createValuationData("", valuationDataMap, true);
    }

    private void updateRiskControlData(Map<String, Object> riskControlDataMap) {
        riskControlDataService.createRiskControlData("", riskControlDataMap, true);
    }

    private void updateMaintenanceData(Map<String, Object> maintenanceDataMap) {
        maintenanceDataService.createMaintenanceData("", maintenanceDataMap, true);
    }

    private void updateDetectionData(Map<String, Object> detectionDataMap, String orderNo) {
        //TODO 没有用到这个方法
        basicInfoService.createBasicInfo(detectionDataMap, 0, true, orderNo, "");
        certificateService.createCertificate(detectionDataMap, 0, true);
        dataInfoService.createDataInfo(detectionDataMap, 0, true);
        evaluationInfoService.createEvaluationInfo(detectionDataMap, 0, true);
    }

    private void updateModelData(Map<String, Object> carModelDataMap) {
        baseConfigService.createBaseConfig(carModelDataMap, 0, true);
        hightechConfigService.createHightechConfig(carModelDataMap, 0, true);
        lightConfigService.createLightConfig(carModelDataMap, 0, true);
        mediaConfigService.createMediaConfig(carModelDataMap, 0, true);
        externalConfigService.createExternalConfig(carModelDataMap, 0, true);
        controlConfigService.createControlConfig(carModelDataMap, 0, true);
        safetyConfigService.createSafetyConfig(carModelDataMap, 0, true);
        gearboxAndWheelConfigService.createGearboxAndWheelConfig(carModelDataMap, 0, true);
        engineConfigService.createEngineConfig(carModelDataMap, 0, true);
        carBodyConfigService.createCarBodyConfig(carModelDataMap, 0, true);
    }

    public CarModelData saveCarModelData(Map<String, Object> carModelDataMap, String orderNo) {
        CarModelData carModelData = new CarModelData();
        carModelData.setOrderNo(orderNo);
        carModelDataMapper.insert(carModelData);
        Integer carModelDataId = carModelData.getId();
        BaseConfig baseConfig = baseConfigService.createBaseConfig(carModelDataMap, carModelDataId, false);
        HightechConfig hightechConfig = hightechConfigService.createHightechConfig(carModelDataMap, carModelDataId, false);
        LightConfig lightConfig = lightConfigService.createLightConfig(carModelDataMap, carModelDataId, false);
        MediaConfig mediaConfig = mediaConfigService.createMediaConfig(carModelDataMap, carModelDataId, false);
        ExternalConfig externalConfig = externalConfigService.createExternalConfig(carModelDataMap, carModelDataId, false);
        ControlConfig controlConfig = controlConfigService.createControlConfig(carModelDataMap, carModelDataId, false);
        SafetyConfig safetyConfig = safetyConfigService.createSafetyConfig(carModelDataMap, carModelDataId, false);
        GearboxAndWheelConfig gearboxAndWheelConfig = gearboxAndWheelConfigService.createGearboxAndWheelConfig(carModelDataMap, carModelDataId, false);
        EngineConfig engineConfig = engineConfigService.createEngineConfig(carModelDataMap, carModelDataId, false);
        CarBodyConfig carBodyConfig = carBodyConfigService.createCarBodyConfig(carModelDataMap, carModelDataId, false);
        carModelData.setBaseConfig(baseConfig);
        carModelData.setHightechConfig(hightechConfig);
        carModelData.setLightConfig(lightConfig);
        carModelData.setMediaConfig(mediaConfig);
        carModelData.setExternalConfig(externalConfig);
        carModelData.setControlConfig(controlConfig);
        carModelData.setSafetyConfig(safetyConfig);
        carModelData.setGearboxAndWheelConfig(gearboxAndWheelConfig);
        carModelData.setEngineConfig(engineConfig);
        carModelData.setCarBodyConfig(carBodyConfig);
        return carModelData;
    }

    public DetectionData saveDetectionData(Map<String, Object> detectionDataMap, String orderNo, String dealerBuyPrice) {
        DetectionData detectionData = new DetectionData();
        detectionData.setOrderNo(orderNo);
        detectionDataMapper.insert(detectionData);
        Integer detectionDataId = detectionData.getId();
        BasicInfo basicInfo = basicInfoService.createBasicInfo(detectionDataMap, detectionDataId, false, orderNo, dealerBuyPrice);
        Object certificate = certificateService.createCertificate(detectionDataMap, detectionDataId, false);
        List<DataInfo> data = dataInfoService.createDataInfo(detectionDataMap, detectionDataId, false);
        EvaluationInfo evaluationInfo = evaluationInfoService.createEvaluationInfo(detectionDataMap, detectionDataId, false);
        detectionData.setBasicInfo(basicInfo);
        detectionData.setCertificate(certificate);
        detectionData.setData(data);
        detectionData.setEvaluationInfo(evaluationInfo);
        return detectionData;
    }

    public MaintenanceData saveMaintenanceData(Map<String, Object> maintenanceDataMap, String orderNo) {
        MaintenanceData maintenanceData = maintenanceDataService.createMaintenanceData(orderNo, maintenanceDataMap, false);
        return maintenanceData;
    }

    public RiskControlData saveRiskControlData(Map<String, Object> riskControlDataMap, String orderNo) {
        RiskControlData riskControlData = riskControlDataService.createRiskControlData(orderNo, riskControlDataMap, false);
        return riskControlData;
    }

    public ValuationData saveValuationData(Map<String, Object> valuationDataMap, String orderNo) {
        ValuationData valuationData = valuationDataService.createValuationData(orderNo, valuationDataMap, false);
        return valuationData;
    }


    public String getReport(CBSBuilder cbsBuilder, String orderNo,Integer priceType) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("orderno", orderNo);
        params.put("pricetype", priceType);
        return cbsBuilder.sendPost("/valuation/customize/order/reportJSON", params);
    }
}
