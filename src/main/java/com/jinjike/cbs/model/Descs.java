package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

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
public class Descs extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer dataId;

    /**
     * 部件图片
     */
    private String picture;

    /**
     * 描述
     */
    private String description;

    /**
     * 描述集合
     */
    @TableField(exist = false)
    private List<Descs> descriptionList;

    /**
     *  漆膜数据集合
     */
    @TableField(exist = false)
    private List<Coordinate> coordinate;


}
