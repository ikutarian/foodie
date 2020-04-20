package com.ikutarian.pojo.vo;

import com.ikutarian.annotation.Desensitized;
import com.ikutarian.enums.SensitiveType;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ItemCommentVo {

    private Integer commentLevel;
    private String content;
    private String specName;
    private Date createdTime;
    private String userFace;

    @Desensitized(type = SensitiveType.CHINESE_NAME)
    private String nickname;
}
