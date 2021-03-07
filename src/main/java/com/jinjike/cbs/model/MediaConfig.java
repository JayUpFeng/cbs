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
public class MediaConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 多碟CD
     */
    private String duodcd;

    /**
     * ≥8喇叭扬声器系统
     */
    private String lbysqxt8;

    /**
     * 6-7喇叭扬声器系统
     */
    private String lbysqxt6;

    /**
     * 4-5喇叭扬声器系统
     */
    private String lbysqxt4;

    /**
     * 2-3喇叭扬声器系统
     */
    private String lbysqxt2;

    /**
     * 多碟DVD系统
     */
    private String dddvdxt;

    /**
     * 单碟DVD
     */
    private String dddvd;

    /**
     * 虚拟多碟CD
     */
    private String xnddcd;

    /**
     * 单碟CD
     */
    private String ddcd;

    /**
     * CD支持MP3/WMA
     */
    private String cdzc;

    /**
     * 外接音源接⼝(AUX/USB/iPod等)
     */
    private String wjyyjk;

    /**
     * 后排液晶屏
     */
    private String hpyjp;

    /**
     * 车载电视
     */
    private String czds;

    /**
     * 蓝牙/车载电话
     */
    private String lyczdh;

    /**
     * 内置硬盘
     */
    private String nzyp;

    /**
     * 人机交互系统
     */
    private String rjjhxt;

    /**
     * 定位互动服务
     */
    private String dwhdfw;

    /**
     * GPS导航系统
     */
    private String gps;


}
