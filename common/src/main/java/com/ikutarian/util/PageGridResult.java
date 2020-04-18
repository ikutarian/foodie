package com.ikutarian.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页结果包装类
 */
@Getter
@Setter
public class PageGridResult {

    /**
     * 当前页码
     */
    private long page;
    /**
     * 总页数
     */
    private long total;
    /**
     * 总记录数
     */
    private long records;
    /**
     * 每页显示的内容
     */
    private List<?> rows;

    public static PageGridResult fromIPage(IPage<?> iPage) {
        PageGridResult pageGridResult = new PageGridResult();
        pageGridResult.setPage(iPage.getCurrent());
        pageGridResult.setTotal(iPage.getTotal());
        pageGridResult.setRecords(iPage.getTotal());
        pageGridResult.setRows(iPage.getRecords());
        return pageGridResult;
    }
}
