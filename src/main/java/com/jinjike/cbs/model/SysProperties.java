package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @作者：zhanghe
 * @时间：2021-02-28 19:18:06
 * @注释：
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class SysProperties extends Model {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String userId;
    private String keySecret;
}
