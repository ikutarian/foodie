package com.ikutarian.enums;

import lombok.Getter;

/**
 * 商品评价登录
 */
@Getter
public enum CommentLevel {

    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    private final Integer type;
    private final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
