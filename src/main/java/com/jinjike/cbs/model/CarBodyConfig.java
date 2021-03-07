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
public class CarBodyConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 车身结构
     */
    private String cscsjg;

    /**
     * 硬件特征
     */
    private String yjtz;

    /**
     * 底盘面积
     */
    private String dpmj;

    /**
     * 行李厢容积(L)
     */
    private String xlxrj;

    /**
     * 油箱容积(L)
     */
    private String yxrj;

    /**
     * 座位数(个)
     */
    private String zws;

    /**
     * 车门数(个)
     */
    private String cms;

    /**
     * 整备质量(Kg)
     */
    private String zbzl;

    /**
     * 轴距(mm)
     */
    private String zj;

    /**
     * 后轮距(mm)
     */
    private String hlj;

    /**
     * 前轮距(mm)
     */
    private String qlj;

    /**
     * 高度(mm)
     */
    private String gd;

    /**
     * 宽度(mm)
     */
    private String kd;

    /**
     * 长度(mm)
     */
    private String cd;


}
