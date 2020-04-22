package com.ikutarian.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 二级分类
 */
@Getter
@Setter
public class CategoryVo {

    private Integer id;
    private String name;
    private String type;
    private Integer fatherId;
    /**
     * 三级分类
     */
    private List<SubCategoryVo> subCatList;
}
