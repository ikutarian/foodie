package com.ikutarian.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel(value = "用户对象BO", description = "从客户端传入的数据封装在此entity中")
@Getter
@Setter
public class UserBo {

    @ApiModelProperty(value = "用户名", name = "username", example = "ikutarian", required = true)
    private String username;

    @ApiModelProperty(value = "密码", name = "password", example = "123", required = true)
    private String password;

    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123", required = true)
    private String confirmPassword;
}
