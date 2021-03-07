package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @作者：zhanghe
 * @时间：2021-02-28 12:58:03
 * @注释：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Records extends Model {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String orderNo;
    private String vin;
    @TableField(exist = false)
    private CarModelData carModelData;
    @TableField(exist = false)
    private DetectionData detectionData;
    @TableField(exist = false)
    private MaintenanceData maintenanceData;
    @TableField(exist = false)
    private RiskControlData riskControlData;
    @TableField(exist = false)
    private ValuationData valuationData;
}
