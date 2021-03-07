package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

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
public class BasicInfo extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer detectionDataId;

    private String orderNo;
    /**
     * vin 码
     */
    private String vin;

    /**
     * 车辆评级
     */
    private String carLevel;

    /**
     * 车型
     */
    private String modelName;

    /**
     * 上牌年份
     */
    private String plateYear;

    /**
     * 表显里程(单位/万公里)
     */
    private BigDecimal watchMile;

    /**
     * 车牌号
     */
    private String carNo;


    /**
     * 车辆综述
     */
    private String evaluateContent;

    /**
     * 发动机号
     */
    private String engineNo;

    /**
     * 拍摄的左前 45 度照片
     */
    private String leftPicture;

    /**
     * 拍摄 vin 码的照片
     */
    private String vinPicture;

    /**
     * 拍摄表显里程的照片
     */
    private String milePicture;

    //自己新增字段，用来页面展示
    /**
     * 评级（编辑时传过来的字段）
     */
    private String grade;
    /**
     * 车商收车价 中
     */
    private String dealerBuyPrice;

    //元数据新增字段
    //extend
    //carScore
    //importantScore
    //repairScore
    //reportUrl
    /**
     * 对应的 58 品牌车系车型和颜色，为字符串格式
     */
    private String extend;
    /**
     * 	车辆评分
     */
    private String carScore;
    /**
     * 重要部件评分
     */
    private String importantScore;
    /**
     * 整备评分
     */
    private String repairScore;
    /**
     * 维保报告url
     */
    private String reportUrl;

    /**
     * 添加时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;


}
