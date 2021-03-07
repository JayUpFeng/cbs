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
public class SafetyConfig extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer carModelDataId;

    /**
     * 无钥匙进入系统
     */
    private String wysjrxt;

    /**
     * 无钥匙启动系统
     */
    private String wysqdxt;

    /**
     * 遥控钥匙
     */
    private String ykys;

    /**
     * 车内中控锁
     */
    private String cnzks;

    /**
     * 发动机电子防盗
     */
    private String fdjdzfd;

    /**
     * LATCH座椅接口(兼容ISOFIX)
     */
    private String zyjk;

    /**
     * ISOFIX儿童座椅接口
     */
    private String etzyjk;

    /**
     * 安全带未系提示
     */
    private String aqdwjts;

    /**
     * 零胎压继续驾驶
     */
    private String ltyjxxs;

    /**
     * 胎压监测装置
     */
    private String tyjczz;

    /**
     * 膝部气囊
     */
    private String xbqn;

    /**
     * 后排头部气囊(气帘)
     */
    private String hptbqnql;

    /**
     * 前排头部气囊(气帘)
     */
    private String qptbqnql;

    /**
     * 后排侧气囊
     */
    private String hpcqn;

    /**
     * 前排侧气囊
     */
    private String qpcqn;

    /**
     * 副驾驶安全气囊
     */
    private String fjsaqqn;

    /**
     * 驾驶座安全气囊
     */
    private String jszaqqn;


}
