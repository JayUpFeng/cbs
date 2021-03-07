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
public class ExternalConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 泊车辅助
     */
    private String bcfz;

    /**
     * 前座中央扶手
     */
    private String qzzyfs;

    /**
     * 车载冰箱
     */
    private String czbx;

    /**
     * 车内空气调节/花粉过滤
     */
    private String cnkqtj;

    /**
     * 温度分区控制
     */
    private String wdfqkz;

    /**
     * 后座出风口
     */
    private String hzcfk;

    /**
     * 后排独立空调
     */
    private String hpdlkt;

    /**
     * 自动空调
     */
    private String zdkt;

    /**
     * 手动空调
     */
    private String sdkt;

    /**
     * 电动后备厢
     */
    private String ddhbx;

    /**
     * 后排杯架
     */
    private String hpbj;

    /**
     * 后座中央扶手
     */
    private String hzzyfs;

    /**
     * 第三排座椅
     */
    private String dspzy;

    /**
     * 后排座椅比例放倒
     */
    private String hpzyblfd;

    /**
     * 后排座椅整体放倒
     */
    private String hpzyztfd;

    /**
     * 后排座椅按摩
     */
    private String hpzyam;

    /**
     * 前排座椅按摩
     */
    private String qpzyam;

    /**
     * 后排座椅通风
     */
    private String hpzytf;

    /**
     * 前排座椅通风
     */
    private String qpzytf;

    /**
     * 后排座椅加热
     */
    private String hpzyjr;

    /**
     * 前排座椅加热
     */
    private String qpzyjr;

    /**
     * 电动座椅记忆
     */
    private String ddzyjy;

    /**
     * 后排座椅电动调节
     */
    private String hpzyddtj;

    /**
     * 第二排座椅移动
     */
    private String depzyyd;

    /**
     * 第二排靠背角度调节
     */
    private String depkbjdtj;

    /**
     * 副驾驶位电动调节
     */
    private String fjswddtj;

    /**
     * 驾驶位电动调节
     */
    private String jswddtj;

    /**
     * 肩部支撑调节
     */
    private String jbzctj;

    /**
     * 腰部支撑调节
     */
    private String ybzctj;

    /**
     * 座椅高低调节
     */
    private String zygdtj;

    /**
     * 运动风格座椅
     */
    private String ydfgzy;

    /**
     * 真皮/仿皮座椅
     */
    private String zpzy;

    /**
     * HUD抬头数字显示
     */
    private String ttszxs;

    /**
     * 行车电脑显示屏
     */
    private String xcdnxsp;

    /**
     * 倒车视频影像
     */
    private String dcspyx;

    /**
     * 后倒车雷达
     */
    private String hdcld;

    /**
     * 前雷达
     */
    private String qld;

    /**
     * 定速巡航
     */
    private String dsxh;

    /**
     * 方向盘加热
     */
    private String fxpjr;

    /**
     * 方向盘换挡
     */
    private String fxphd;

    /**
     * 多功能方向盘
     */
    private String dgnfxp;

    /**
     * 方向盘电动调节
     */
    private String fxpddtj;

    /**
     * 方向盘前后调节
     */
    private String fxpqhtj;

    /**
     * 方向盘上下调节
     */
    private String fxpsxtj;

    /**
     * 真皮方向盘
     */
    private String zpfxp;

    /**
     * 电动吸合门
     */
    private String ddxhm;

    /**
     * 铝合金轮毂
     */
    private String lhjly;

    /**
     * 运动外观套件
     */
    private String ydwgtj;

    /**
     * 太阳能天窗
     */
    private String tyntc;

    /**
     * 全景天窗
     */
    private String qjtc;

    /**
     * 电动天窗
     */
    private String ddtc;


}
