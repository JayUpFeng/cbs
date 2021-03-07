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
public class ControlConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 后桥限滑差速器/差速锁
     */
    private String hqxhcsq;

    /**
     * 前桥限滑差速器/差速锁
     */
    private String qqxhcsq;

    /**
     * 中央差速器锁止功能
     */
    private String zycsqszgn;

    /**
     * 可变转向比
     */
    private String kbzxb;

    /**
     * 空气悬架
     */
    private String kqxj;

    /**
     * 可变悬架
     */
    private String kbxj;

    /**
     * 陡坡缓降
     */
    private String dphj;

    /**
     * 自动驻车/上坡辅助
     */
    private String zdzc;

    /**
     * 车身稳定控制(ESC/ESP/DSC等)
     */
    private String cswdkz;

    /**
     * 牵引力控制(ASR/TCS/TRC等)
     */
    private String qylkz;

    /**
     * 刹车辅助(EBA/BAS/BA等)
     */
    private String scfz;

    /**
     * 制动力分配(EBD/CBC等)
     */
    private String zdlfp;

    /**
     * ABS防抱死
     */
    private String abs;


}
