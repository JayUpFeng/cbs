package com.jinjike.cbs.common;

/**
 * @作者：zhanghe
 * @时间：2021-02-28 15:54:56
 * @注释：
 */
public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {
    private static final long serialVersionUID = 1L;

    public Page(Integer pageNow, Integer pageSize) {
        if (pageNow == null) {
            this.setCurrent(1);
        } else {
            this.setCurrent(pageNow);
        }
        if (pageSize == null) {
            this.setSize(10);
        } else {
            this.setSize(pageSize);
        }
    }
}
