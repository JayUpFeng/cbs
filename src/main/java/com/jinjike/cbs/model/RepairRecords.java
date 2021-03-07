package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
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
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class RepairRecords extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 维修id
     */
    private Integer maintenanceDataId;

    /**
     * 记录类型
     */
    private String type;
    /**
     * 维修详细的内容
     */
    private String content;

    /**
     * 维修的日期(yyyy-MM-dd)
     */
    private String date;

    /**
     * 维修记录的时间(时间戳)
     */
    private Long mainTainDate;

    /**
     * 维修的材料
     */
    private String materal;

    /**
     * 维修的公里数
     */
    private Integer mileage;

    /**
     * 支付类型(为空)
     */
    private String payType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 个人卖车价
     */
    private BigDecimal personSoldPrice;

    /**
     * 个人卖车最低价
     */
    private BigDecimal personSoldPriceMin;

    /**
     * 个人卖车最高价
     */
    private BigDecimal personSoldPriceMax;

    /**
     * 区分种类：（0:normal、1:constructAnalyze、2:componentAnalyze、3:outsideAnalyze）
     */
    private Integer kind;


}
