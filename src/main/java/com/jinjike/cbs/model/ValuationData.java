package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class ValuationData extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 对应的订单编号
     */
    private String orderNo;

    /**
     * 新车指导价
     */
    private String newCarGuidePrice;

    /**
     * 车龄
     */
    private Integer carAge;

    /**
     * 行驶里程
     */
    private Integer miles;

    /**
     * 首次上牌时间
     */
    private String firstPlateTime;

    /**
     * 查博士预估公里数
     */
    private Integer cbsEstimatedMiles;

    /**
     * 上牌地点
     */
    private String carLicenseLocation;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * 过户次数
     */
    private Integer dealNums;

    /**
     * 车身颜色
     */
    private String carColor;

    /**
     * 是否热门车型:值大于 1 ：属于热门车型，值大于 0 并且 小于 1 ：不属于热大型，
     */
    private Integer modelAnaly;

    /**
     * 车商收车价 高
     */
    private String dealerBuyPriceMax;

    /**
     * 车商收车价 中
     */
    private String dealerBuyPrice;

    /**
     * 车商收车价 低
     */
    private String dealerBuyPriceMin;

    /**
     * 个人卖车价 高
     */
    private String personSoldPriceMax;

    /**
     * 个人卖车价 中
     */
    private String personSoldPrice;

    /**
     * 个人卖车价 低
     */
    private String personSoldPriceMin;

    /**
     * 车辆备案图片(格式为数组)
     */
    @TableField(exist = false)
    private List<FilingPics> filingPics;

    /**
     * 创建订单时上传的照片与检测部件的对应关系，格式为JSON
     */
    @TableField(exist = false)
    private PicsRelevanceComponet picsRelevanceComponet;
    /**
     * 过去6个月价格(格式为数组)
     */
    @TableField(exist = false)
    private List<MonthPrice> monthsAgoPrice;

    /**
     * 未来6个月价格(格式为数组)
     */
    @TableField(exist = false)
    private List<MonthPrice> monthsPrice;
}
