package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.util.List;

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
public class MaintenanceData extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应的订单编号
     */
    private String orderNo;

    /**
     * 品牌名称
     */
    private String brand;

    /**
     * ⻋型
     */
    private String modelName;

    /**
     * ⻋系
     */
    private String seriesName;

    /**
     * Vin码
     */
    private String vin;

    /**
     * 报告⽣成时间
     */
    private String makeReportDate;

    /**
     * 报告更新时间
     */
    private String updateTime;

    /**
     * ⽣成⼚商
     */
    private String manufacturer;

    /**
     * ⽣产年份
     */
    private String makeDate;

    /**
     * 产地
     */
    private String productionArea;

    /**
     * ⻋辆类型
     */
    private String carType;

    /**
     * 变速箱类型
     */
    private String transmissionType;

    /**
     * 排量
     */
    private String displacement;

    /**
     * 排放标准
     */
    private String effluentStandard;

    /**
     * 是否火烧（0 - 否 1 - 是）
     */
    private Integer carFireFlag;

    /**
     * 是否水泡（0 - 否 1 - 是）
     */
    private Integer carWaterFlag;

    /**
     * 重要组成部件是否有维修（0 - 否 1 - 是）
     */
    private Integer carComponentRecordsFlag;

    /**
     * 结构件是否有维修（0 - 否 1 - 是）
     */
    private Integer carConstructRecordsFlag;

    /**
     * 外观覆盖件是否有维修（0 - 否 1 - 是）
     */
    private Integer carOutsideRecordsFlag;

    /**
     * 公⾥数是否正常（0-否 1-是）
     */
    private Integer mileageIsNormalFlag;

    /**
     * 查博士预估公⾥数（如果为0说明没有估出来）
     */
    private Integer mileageEstimate;

    /**
     * 最后⼀次保养时间
     */
    private String lastMainTainTime;

    /**
     * 每年保养次数
     */
    private String mainTainTimes;

    /**
     * 最后⼀次维修时间
     */
    private String lastRepairTime;

    /**
     * 每年⾏驶公⾥数
     */
    private String mileageEveryYear;


    private String reportNo;

    /**
     * 该⻋所有的详细维修记录（普通报告）
     */
    @TableField(exist = false)
    private List<RepairRecords> normalRepairRecords;

    /**
     * 结构详细维修记录
     */
    @TableField(exist = false)
    private List<RepairRecords> constructAnalyzeRepairRecords;

    /**
     * 重要组成部件详细维修记录
     */
    @TableField(exist = false)
    private List<RepairRecords> componentAnalyzeRepairRecords;

    /**
     * 外观覆盖件详细维修记录
     */
    @TableField(exist = false)
    private List<RepairRecords> outsideAnalyzeRepairRecords;


}
