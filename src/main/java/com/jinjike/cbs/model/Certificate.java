package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
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
public class Certificate extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer detectionDataId;
    /**
     * 车辆发票
     */
    @TableField("c_LFP")
    private String cLfp;

    /**
     * 登记证第二页
     */
    @TableField("d_JZDEY")
    private String dJzdey;

    /**
     * 登记证第一页
     */
    @TableField("d_JZDYY")
    private String dJzdyy;

    /**
     * 购置税第一页
     */
    @TableField("g_ZSDYY")
    private String gZsdyy;

    /**
     * 交强险上部
     */
    @TableField("j_QXSB")
    private String jQxsb;

    /**
     * 交强险下部
     */
    @TableField("j_QXXB")
    private String jQxxb;

    /**
     * 商业保单上部
     */
    @TableField("s_YBDSB")
    private String sYbdsb;

    /**
     * 行驶证正面照片
     */
    @TableField("x_SZZMZP")
    private String xSzzmzp;

    /**
     * 行驶证车辆照片
     */
    @TableField("x_XZCLZP")
    private String xXzclzp;

    /**
     * 行驶证副页照片
     */
    @TableField("x_XZFYZP")
    private String xXzfyzp;

    //元数据新增字段
    /**
     * 图片名称
     */
    private String name;
    /**
     * 图片地址 (需加图片前缀)
     */
    private String netImg;

}
