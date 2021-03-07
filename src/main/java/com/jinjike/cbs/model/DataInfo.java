package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("data")
public class DataInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer detectionDataId;
    /**
     * 部件 ID
     */
    private Integer componmentId;

    /**
     * 部件的具体描述
     */
    @TableField(exist = false)
    private List<Descs> descs;


    /**
     * 部件名称
     */
    private String nameComponment;

    /**
     * 分组名称
     */
    private String nameGroup;
    //以下为：新版本新增字段
    /**
     * 分组 ID
     */
    private String groupId;
    /**
     * 图片地址多个地址逗号分割
     */
    private String picture;
    /**
     * 一级系统名称
     */
    private String firstSysName;
    /**
     * 二级系统名称
     */
    private String secondSysName;
    /**
     * 数据类型 0:漆膜数据 2:部件描述和照片 3:拆解
     */
    private Integer type;
    /**
     * 漆膜数据
     */
    private String qmInfos;


}
