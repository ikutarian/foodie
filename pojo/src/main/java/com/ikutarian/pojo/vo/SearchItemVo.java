package com.ikutarian.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品搜索结果
 */
@Getter
@Setter
public class SearchItemVo {

    private String itemId;
    private String itemName;
    private Integer sellCounts;
    private String imgUrl;
    private Integer price;
}
