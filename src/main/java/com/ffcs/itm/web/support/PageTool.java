package com.ffcs.itm.web.support;

import com.ffcs.itm.common.mybatis.plugin.domain.PageParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.List;

/**
 * 封装 mybatis 分页查询结果
 */
public class PageTool {
    public static <M> Page<M> getPage(List<M> entityList, PageParam pageParam) {
        return new PageImpl<>(entityList, null, pageParam.getTotalElements());
    }
}
