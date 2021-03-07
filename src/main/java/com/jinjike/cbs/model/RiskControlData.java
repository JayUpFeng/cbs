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
public class RiskControlData extends Model {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 对应的订单编号
     */
    private String orderNo;

    /**
     * 车辆使用性质（0 : 非营运，1 : 营运）
     */
    private Integer syxz;

    /**
     * 铭牌是否一致（0 : 不一致，1 : 一致）
     */
    private Integer mp;

    /**
     * 是否逃逸（0 : 否，1 ：是）
     */
    private Integer sfty;

    /**
     * 行驶证是否一致（0 : 不一致，1 ：一致）
     */
    private Integer xsz;

    /**
     * 登记证是否一致（0 : 不一致，1 ：一致）
     */
    private Integer djz;

    /**
     * 是否套牌（0 : 否，1 ：是）
     */
    private Integer sftp;


    private Integer sfdy;

    /**
     * 违章次数
     */
    private Integer violationCount;


}
