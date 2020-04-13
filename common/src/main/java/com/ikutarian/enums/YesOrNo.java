package com.ikutarian.enums;

import lombok.Getter;

@Getter
public enum YesOrNo {

    NO(0, "否"),
    YES(1, "是");

    private final Integer type;
    private final String value;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
