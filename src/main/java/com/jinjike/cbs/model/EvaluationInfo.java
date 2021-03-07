package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class EvaluationInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer detectionDataId;

    private BigDecimal buyPrice;

    private String dealerBuyPrice;

    private String dealerBuyPriceMax;

    private String dealerBuyPriceMin;

    private String personSoldPrice;

    private String personSoldPriceMax;

    private String personSoldPriceMin;

    private BigDecimal sellPrice;


}
