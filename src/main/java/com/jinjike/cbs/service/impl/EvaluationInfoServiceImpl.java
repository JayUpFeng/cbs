package com.jinjike.cbs.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jinjike.cbs.model.EvaluationInfo;
import com.jinjike.cbs.mapper.EvaluationInfoMapper;
import com.jinjike.cbs.service.IEvaluationInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-26
 */
@Service
public class EvaluationInfoServiceImpl extends ServiceImpl<EvaluationInfoMapper, EvaluationInfo> implements IEvaluationInfoService {

    @Override
    public EvaluationInfo createEvaluationInfo(Map<String, Object> detectionDataMap, Integer detectionDataId,boolean isUpdate) {
        Map<String, Object> evaluationInfoMap = (Map<String, Object>) detectionDataMap.get("evaluationInfo");
        if (evaluationInfoMap != null) {
            EvaluationInfo evaluationInfo = new EvaluationInfo();
            if (isUpdate){
                Integer id = (Integer) evaluationInfoMap.get("id");
                evaluationInfo.setId(id);
                detectionDataId = (Integer) evaluationInfoMap.get("carModelDataId");
            }
            evaluationInfo.setDetectionDataId(detectionDataId);
            BigDecimal buyPrice = (BigDecimal) evaluationInfoMap.get("buyPrice");
            String dealerBuyPrice = (String) evaluationInfoMap.get("dealerBuyPrice");
            String dealerBuyPriceMax = (String) evaluationInfoMap.get("dealerBuyPriceMax");
            String dealerBuyPriceMin = (String) evaluationInfoMap.get("dealerBuyPriceMin");
            String personSoldPrice = (String) evaluationInfoMap.get("personSoldPrice");
            String personSoldPriceMax = (String) evaluationInfoMap.get("personSoldPriceMax");
            String personSoldPriceMin = (String) evaluationInfoMap.get("personSoldPriceMin");
            BigDecimal sellPrice = (BigDecimal) evaluationInfoMap.get("sellPrice");
            evaluationInfo.setBuyPrice(buyPrice);
            evaluationInfo.setDealerBuyPrice(dealerBuyPrice);
            evaluationInfo.setDealerBuyPriceMax(dealerBuyPriceMax);
            evaluationInfo.setDealerBuyPriceMin(dealerBuyPriceMin);
            evaluationInfo.setPersonSoldPrice(personSoldPrice);
            evaluationInfo.setPersonSoldPriceMax(personSoldPriceMax);
            evaluationInfo.setPersonSoldPriceMin(personSoldPriceMin);
            evaluationInfo.setSellPrice(sellPrice);
            save(evaluationInfo);
            return evaluationInfo;
        }
        return null;
    }
}
