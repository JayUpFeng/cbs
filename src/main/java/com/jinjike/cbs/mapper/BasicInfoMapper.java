package com.jinjike.cbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.model.BasicInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface BasicInfoMapper extends BaseMapper<BasicInfo> {
    IPage<BasicInfo> pageList(Page<BasicInfo> page, @Param("orderNo") String orderNo, @Param("vin")String vin);
}
