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
public class EngineConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 发动机描述
     */
    private String fdjms;

    /**
     * 比功率(最大功率(kw)/整备质量(kg))
     */
    private String bgl;

    /**
     * 升功率比(最大功率(kw)/排量(L))
     */
    private String sglb;

    /**
     * 环保标准
     */
    private String hbbz;

    /**
     * 缸盖材料
     */
    private String ggcl;

    /**
     * 缸体材料
     */
    private String gtcl;

    /**
     * 供油方式
     */
    private String gyfs;

    /**
     * 燃油标号
     */
    private String rybh;

    /**
     * 燃料形式
     */
    private String rlxs;

    /**
     * 发动机特有技术
     */
    private String fdjtyjs;

    /**
     * 最大扭矩转速(rpm)
     */
    private String zdnjzs;

    /**
     * 最大扭矩(N·m)
     */
    private String zdnj;

    /**
     * 最大功率转速(rpm)
     */
    private String zdglzs;

    /**
     * 最大功率(kW)
     */
    private String zdgl;

    /**
     * 最大马力(Ps)
     */
    private String zdml;

    /**
     * 行程
     */
    private String xc;

    /**
     * 缸径
     */
    private String gj;

    /**
     * 配气机构
     */
    private String pqjg;

    /**
     * 压缩比
     */
    private String ysb;

    /**
     * 每缸气门数(个)
     */
    private String mgqms;

    /**
     * 气缸数(个)
     */
    private String qgs;

    /**
     * 气缸排列形式
     */
    private String qgplxs;

    /**
     * 进气形式
     */
    private String jqxs;

    /**
     * 排量(mL)
     */
    private String pl;

    /**
     * 发动机型号
     */
    private String fdjxh;


}
