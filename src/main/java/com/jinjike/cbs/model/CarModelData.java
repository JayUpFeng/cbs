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
 * @时间：2021-02-26 14:53:01
 * @注释：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class CarModelData extends Model {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 对应的订单编号
     */

    private String orderNo;
    @TableField(exist = false)
    private BaseConfig baseConfig;
    @TableField(exist = false)
    private HightechConfig hightechConfig;
    @TableField(exist = false)
    private LightConfig lightConfig;
    @TableField(exist = false)
    private MediaConfig mediaConfig;
    @TableField(exist = false)
    private ExternalConfig externalConfig;
    @TableField(exist = false)
    private ControlConfig controlConfig;
    @TableField(exist = false)
    private SafetyConfig safetyConfig;
    @TableField(exist = false)
    private GearboxAndWheelConfig gearboxAndWheelConfig;
    @TableField(exist = false)
    private EngineConfig engineConfig;
    @TableField(exist = false)
    private CarBodyConfig carBodyConfig;

}
