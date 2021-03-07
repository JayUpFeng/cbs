package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class GearboxAndWheelConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 备胎规格
     */
    private String btgg;

    /**
     * 后轮胎规格
     */
    private String hltgg;

    /**
     * 前轮胎规格
     */
    private String qltgg;

    /**
     * 驻车制动类型
     */
    private String zczdlx;

    /**
     * 后制动器类型
     */
    private String hzdqlx;

    /**
     * 前制动器类型
     */
    private String qzdqlx;

    /**
     * 车体结构
     */
    private String ctjg;

    /**
     * 助力类型
     */
    private String zllx;

    /**
     * 四驱形式
     */
    private String sqxs;

    /**
     * 后悬架类型/后悬挂类型
     */
    private String hxglx;

    /**
     * 前悬架类型/前悬挂类型
     */
    private String qxglx;

    /**
     * 驱动方式
     */
    private String qdfs;

    /**
     * 变速箱类型
     */
    private String bxslx;

    /**
     * 简称
     */
    private String jc;

    /**
     * 档位数
     */
    private String dws;


}
