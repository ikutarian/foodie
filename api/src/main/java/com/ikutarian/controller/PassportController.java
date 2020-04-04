package com.ikutarian.controller;

import com.ikutarian.pojo.bo.UserBo;
import com.ikutarian.service.UserService;
import com.ikutarian.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "注册登录", tags = {"用于注册登录相关的接口"})
@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("usernameIsExist")
    public ApiResult usernameIsExist(String username, HttpServletRequest request) {
        if (StringUtils.isBlank(username)) {
            return ApiResult.error("用户名不能为空");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ApiResult.error("用户名已存在");
        }

        return ApiResult.ok();
    }

    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    @PostMapping("regist")
    public ApiResult register(@RequestBody UserBo userBo) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();
        String confirmPassword = userBo.getConfirmPassword();

        if (StringUtils.isAnyBlank(username, password, confirmPassword)) {
            return ApiResult.error("用户名或密码不能为空");
        }
        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ApiResult.error("用户名已存在");
        }
        if (StringUtils.length(password) < 6) {
            return ApiResult.error("密码长度不能小于6");
        }
        if (!StringUtils.equals(password, confirmPassword)) {
            return ApiResult.error("两次密码输入不一致");
        }

        userService.createUser(userBo);
        return ApiResult.ok();
    }
}
