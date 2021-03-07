package com.jinjike.cbs.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.jinjike.cbs.model.FilingPics;
import com.jinjike.cbs.model.MonthPrice;
import com.jinjike.cbs.mapper.MonthPriceMapper;
import com.jinjike.cbs.service.IMonthPriceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class MonthPriceServiceImpl extends ServiceImpl<MonthPriceMapper, MonthPrice> implements IMonthPriceService {

    @Override
    public List<MonthPrice> savePrice(JSONArray monthsAgoPrice, Integer valuationDataId,Integer type,boolean isUpdate) {
        if (monthsAgoPrice != null) {
            List<MonthPrice> list = new ArrayList<>();
            for (int i = 0; i < monthsAgoPrice.size(); i++) {
                Map<String, Object> map = (Map<String, Object>) monthsAgoPrice.get(i);
                if (map != null) {
                    MonthPrice monthPrice =new MonthPrice();
                    if (isUpdate){
                        Integer id = (Integer) map.get("id");
                        monthPrice.setId(id);
                        valuationDataId = (Integer) map.get("valuationDataId");
                    }
                    monthPrice.setValuationDataId(valuationDataId);
                    String date = (String) map.get("date");
                    String price = (String) map.get("price");
                    if (type==0){
                        monthPrice.setType(0);
                    }
                    if (type==1){
                        monthPrice.setType(1);
                    }
                    monthPrice.setDate(date);
                    monthPrice.setPrice(price);
                    list.add(monthPrice);
                }
            }
            saveBatch(list);
            return list;
        }
        return null;
    }
}
