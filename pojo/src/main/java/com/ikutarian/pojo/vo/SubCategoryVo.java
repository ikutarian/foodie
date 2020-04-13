package com.ikutarian.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 三级分类
 */
@Getter
@Setter
public class SubCategoryVo {

    private Integer subId;
    private String subName;
    private String subType;
    private Integer subFatherId;
}
