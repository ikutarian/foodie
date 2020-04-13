package com.ikutarian.pojo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 最新商品
 */
@Getter
@Setter
public class NewItemsVo {

    private Integer rootCatId;
    private String rootCatName;
    private String slogan;
    private String catImage;
    private String bgColor;
    private List<SimpleItemVo> simpleItemList;
}
