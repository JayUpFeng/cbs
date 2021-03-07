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
 * @since 2021-02-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class Coordinate extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer descId;
    /**
     * 测量数据
     */
    private BigDecimal data;

    /**
     * 距离图片左侧位置单位（单位/px）
     */
    private Integer lefts;

    /**
     * 距离图片上部位置（单位/px）
     */
    private Integer top;

    /**
     *  名称
     */
    private String name;


}
