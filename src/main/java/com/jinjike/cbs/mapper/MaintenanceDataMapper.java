package com.jinjike.cbs.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.jinjike.cbs.common.Page;
import com.jinjike.cbs.model.MaintenanceData;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhanghe
 * @since 2021-02-25
 */
public interface MaintenanceDataMapper extends BaseMapper<MaintenanceData> {
    IPage<MaintenanceData> pageList(Page<MaintenanceData> page, @Param("orderNo") String orderNo, @Param("vin")String vin);
}
