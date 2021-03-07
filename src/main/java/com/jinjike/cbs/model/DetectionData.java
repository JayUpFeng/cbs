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
 * @作者：zhanghe
 * @时间：2021-02-26 15:10:52
 * @注释：
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class DetectionData extends Model {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String orderNo;
    @TableField(exist = false)
    private BasicInfo basicInfo;
    @TableField(exist = false)
    private Object certificate;
    @TableField(exist = false)
    private List<DataInfo> data;
    @TableField(exist = false)
    private EvaluationInfo evaluationInfo;


}
