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
public class HightechConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 全景摄像头
     */
    private String qjsxt;

    /**
     * 自适应巡航
     */
    private String zsyxh;

    /**
     * 中控液晶屏分屏显示
     */
    private String zkyjpfpxs;

    /**
     * 夜视系统
     */
    private String ysxt;

    /**
     * 整体主动转向系统
     */
    private String ztzdzxxt;

    /**
     * 主动刹车/主动安全系统
     */
    private String zdscxt;

    /**
     * 车道偏离预警系统
     */
    private String cdplyjxt;

    /**
     * 并线辅助
     */
    private String bxfz;

    /**
     * 自动泊车入位
     */
    private String zdbcrw;

    /**
     * 发动机启停技术
     */
    private String fdjqtjs;


}
