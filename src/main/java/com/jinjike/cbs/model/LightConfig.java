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
public class LightConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 大灯随动调节
     */
    private String ddsdtj;

    /**
     * 感应雨刷
     */
    private String gyys;

    /**
     * 后雨刷
     */
    private String hys;

    /**
     * 遮阳板化妆镜
     */
    private String zybhzj;

    /**
     * 后排侧隐私玻璃
     */
    private String hpcysbl;

    /**
     * 后排侧遮阳帘
     */
    private String hpczyl;

    /**
     * 后风挡遮阳帘
     */
    private String hfdzyl;

    /**
     * 后视镜记忆
     */
    private String hsjjy;

    /**
     * 后视镜电动折叠
     */
    private String hsjddzd;

    /**
     * 外后视镜自动防眩目
     */
    private String whsjzdfxm;

    /**
     * 内后视镜自动防眩目
     */
    private String nhsjzdfxm;

    /**
     * 后视镜加热
     */
    private String hsjjr;

    /**
     * 后视镜电动调节
     */
    private String hsjddtj;

    /**
     * 防紫外线/隔热玻璃
     */
    private String fzwxbl;

    /**
     * 车窗防夹手功能
     */
    private String ccfjsgn;

    /**
     * 后电动车窗
     */
    private String hddcc;

    /**
     * 前电动车窗
     */
    private String qddcc;

    /**
     * 车内氛围灯
     */
    private String cnfwd;

    /**
     * 大灯清洗装置
     */
    private String ddqxzz;

    /**
     * 大灯高度可调
     */
    private String ddgdkt;

    /**
     * 前雾灯
     */
    private String qwd;

    /**
     * 转向头灯(辅助灯)
     */
    private String zxtd;

    /**
     * 自动头灯
     */
    private String zdtd;

    /**
     * 日间行车灯
     */
    private String rjxcd;

    /**
     * LED大灯
     */
    private String leddd;

    /**
     * 氙气大灯
     */
    private String xqdd;


}
