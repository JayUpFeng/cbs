package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jinjike.cbs.model.FilingPics;
import com.jinjike.cbs.model.MonthPrice;
import com.jinjike.cbs.model.PicsRelevanceComponet;
import com.jinjike.cbs.model.ValuationData;
import com.jinjike.cbs.mapper.ValuationDataMapper;
import com.jinjike.cbs.service.IFilingPicsService;
import com.jinjike.cbs.service.IMonthPriceService;
import com.jinjike.cbs.service.IPicsRelevanceComponetService;
import com.jinjike.cbs.service.IValuationDataService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
@Service
public class ValuationDataServiceImpl extends ServiceImpl<ValuationDataMapper, ValuationData> implements IValuationDataService {

    @Autowired
    private IFilingPicsService filingPicsService;
    @Autowired
    private IPicsRelevanceComponetService picsRelevanceComponetService;
    @Autowired
    private IMonthPriceService monthPriceService;

    @Override
    public ValuationData createValuationData(String orderNo, Map<String, Object> valuationDataMap, boolean isUpdate) {
        if (valuationDataMap != null) {
            ValuationData valuationData = new ValuationData();
            if (isUpdate) {
                Integer id = (Integer) valuationDataMap.get("id");
                valuationData.setId(id);
                orderNo = (String) valuationDataMap.get("orderNo");
            }
            valuationData.setOrderNo(orderNo);
            String newCarGuidePrice = (String) valuationDataMap.get("newCarGuidePrice");
            Integer carAge = (Integer) valuationDataMap.get("carAge");
            Integer miles = (Integer) valuationDataMap.get("miles");
            String firstPlateTime = (String) valuationDataMap.get("firstPlateTime");
            Integer cbsEstimatedMiles = (Integer) valuationDataMap.get("cbsEstimatedMiles");
            String carLicenseLocation = (String) valuationDataMap.get("carLicenseLocation");
            String carNo = (String) valuationDataMap.get("carNo");
            String engineNo = (String) valuationDataMap.get("engineNo");
            Integer dealNums = (Integer) valuationDataMap.get("dealNums");
            String carColor = (String) valuationDataMap.get("carColor");
            Integer modelAnaly = (Integer) valuationDataMap.get("modelAnaly");
            String dealerBuyPriceMax = (String) valuationDataMap.get("dealerBuyPriceMax");
            String dealerBuyPrice = (String) valuationDataMap.get("dealerBuyPrice");
            String dealerBuyPriceMin = (String) valuationDataMap.get("dealerBuyPriceMin");
            String personSoldPriceMax = (String) valuationDataMap.get("personSoldPriceMax");
            String personSoldPrice = (String) valuationDataMap.get("personSoldPrice");
            String personSoldPriceMin = (String) valuationDataMap.get("personSoldPriceMin");
            valuationData.setNewCarGuidePrice(newCarGuidePrice);
            valuationData.setCarAge(carAge);
            valuationData.setMiles(miles);
            valuationData.setFirstPlateTime(firstPlateTime);
            valuationData.setCbsEstimatedMiles(cbsEstimatedMiles);
            valuationData.setCarLicenseLocation(carLicenseLocation);
            valuationData.setCarNo(carNo);
            valuationData.setEngineNo(engineNo);
            valuationData.setDealNums(dealNums);
            valuationData.setCarColor(carColor);
            valuationData.setModelAnaly(modelAnaly);
            valuationData.setDealerBuyPriceMax(dealerBuyPriceMax);
            valuationData.setDealerBuyPrice(dealerBuyPrice);
            valuationData.setDealerBuyPriceMin(dealerBuyPriceMin);
            valuationData.setPersonSoldPriceMax(personSoldPriceMax);
            valuationData.setPersonSoldPrice(personSoldPrice);
            valuationData.setPersonSoldPriceMin(personSoldPriceMin);
            save(valuationData);
            Integer valuationDataId = valuationData.getId();
            JSONArray filingPics = (JSONArray) valuationDataMap.get("filingPics");
            Map<String, Object> picsRelevanceComponetMap = (Map<String, Object>) valuationDataMap.get("picsRelevanceComponet");
            JSONArray monthsAgoPrice = (JSONArray) valuationDataMap.get("monthsAgoPrice");
            JSONArray monthsPrice = (JSONArray) valuationDataMap.get("monthsPrice");
            List<FilingPics> filingPicsList = filingPicsService.saveFilingPics(filingPics, valuationDataId, false);
            PicsRelevanceComponet picsRelevanceComponet = picsRelevanceComponetService.savePic(picsRelevanceComponetMap, valuationDataId, false);
            List<MonthPrice> monthAgoPriceList = monthPriceService.savePrice(monthsAgoPrice, valuationDataId, 0, false);
            List<MonthPrice> monthPriceList = monthPriceService.savePrice(monthsPrice, valuationDataId, 1,false);
            valuationData.setFilingPics(filingPicsList);
            valuationData.setPicsRelevanceComponet(picsRelevanceComponet);
            valuationData.setMonthsAgoPrice(monthAgoPriceList);
            valuationData.setMonthsPrice(monthPriceList);
            return valuationData;
        }
        return null;
    }
}
