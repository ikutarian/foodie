package com.ikutarian.pojo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * 商品评价个数
 */
@Getter
@Setter
public class CommentLevelCountVo {

    private Integer totalCounts;
    private Integer goodCounts;
    private Integer normalCounts;
    private Integer badCounts;
}
