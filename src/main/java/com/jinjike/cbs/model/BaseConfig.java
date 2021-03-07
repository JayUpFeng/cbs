package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class BaseConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;
    /**
     * 品牌名称
     */
    private String brandName;

    /**
     * 品牌编码
     */
    private String brandCode;

    /**
     * 车系名称
     */
    private String familyName;

    /**
     * 车系编码
     */
    private String familyCode;

    /**
     * 车组名称
     */
    private String groupName;

    /**
     * 车组编码
     */
    private String groupCode;

    /**
     * 别名
     */
    private String vehicleAlias;

    /**
     * 厂商指导价
     */
    private BigDecimal listPrice;

    /**
     * 新车购置价
     */
    private BigDecimal purchasePrice;

    /**
     * 类比价
     */
    private BigDecimal kindredPrice;

    /**
     * 座位数
     */
    private Integer seat;

    /**
     * 变速箱（器）类型
     */
    private String gearboxType;

    /**
     * 配置等级
     */
    private String cfgLevel;

    /**
     * 国产/进口
     */
    private String importFlag;

    /**
     * 年款
     */
    private String yearPattern;

    /**
     * 上市年份
     */
    private String marketDate;

    /**
     * 外形尺寸
     */
    private String vehicleSize;

    /**
     * 气缸排列形式
     */
    private String arrayType;

    /**
     * 发动机描述
     */
    private String engineDesc;

    /**
     * 发动机型号
     */
    private String engineModel;

    /**
     * 车型俗称
     */
    private String commonName;

    /**
     * 厂家编码
     */
    private String companyCode;

    /**
     * 厂家名称
     */
    private String companyName;

    /**
     * 车型分类名称
     */
    private String vehicleClass;

    /**
     * ABS标示
     */
    private String absFlag;

    /**
     * 动力类型
     */
    private String antiTheft;

    /**
     * 动力类型
     */
    private String powerType;

    /**
     * 安全气囊数
     */
    private Integer airbagNum;

    /**
     * 排量
     */
    private String displacement;

    /**
     * 最小座位数
     */
    private Integer seatMin;

    /**
     * 最大座位数
     */
    private Integer seatMax;

    /**
     * 备注
     */
    private String remark;

    /**
     * 车险标识
     */
    private String riskFlag;

    /**
     * 轴距
     */
    private String wheelbase;

    /**
     * 燃油喷射形式
     */
    private String fuelJetType;

    /**
     * 发动机气门数(单缸气门数)
     */
    private Integer valveNum;

    /**
     * 变速器档数
     */
    private Integer gearNum;

    /**
     * 整备质量
     */
    private String fullWeight;

    /**
     * 最小整备质量
     */
    private String fullWeightMinKg;

    /**
     * 最大整备质量
     */
    private String fullWeightMaxKg;

    /**
     * 购置价含税
     */
    private BigDecimal purchasePriceTax;

    /**
     * 类比价含税
     */
    private BigDecimal kindredPriceTax;

    /**
     * 功率
     */
    private String power;

    /**
     * 车身结构
     */
    private String bodyType;

    /**
     * 排放依据标准
     */
    private String letStand;

    /**
     * 排放标准
     */
    private String effluentStandard;

    /**
     * 总质量
     */
    private String vehicleWeight;

    /**
     * 前轮距
     */
    private String trackFront;

    /**
     * 后轮距
     */
    private String trackRear;

    /**
     * 驱动形式
     */
    private String drivenType;

    /**
     * 变速箱
     */
    private String bsx;

    /**
     * 长×宽×高(mm)
     */
    private String ckg;

    /**
     * 车身结构
     */
    private String csjg;

    /**
     * 官方0-100km/h加速(s)
     */
    private String gfjs;

    /**
     * 实测0-100km/h加速(s)
     */
    private String scjs;

    /**
     * 实测100-0km/h制动(m)
     */
    private String sczd;

    /**
     * 实测油耗(L)
     */
    private String scyh;

    /**
     * 工信部综合油耗(L)
     */
    private String gxbzhyh;

    /**
     * 整车质保
     */
    private String zczb;

    /**
     * 生产状态
     */
    private String stopFlag;


}
