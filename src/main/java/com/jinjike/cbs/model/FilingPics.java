package com.jinjike.cbs.model;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class FilingPics extends Model {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 对应的valuationData
     */
    private Integer valuationDataId;

    /**
     * 图片名称
     */
    private String name;

    /**
     * 图片url
     */
    private String imgUrl;
    //新增字段
    /**
     *  图片id
     */
    private Integer picId;


}
