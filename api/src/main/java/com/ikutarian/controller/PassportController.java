package com.ikutarian.controller;

import com.ikutarian.pojo.User;
import com.ikutarian.pojo.bo.UserBo;
import com.ikutarian.service.UserService;
import com.ikutarian.util.ApiResult;
import com.ikutarian.util.CookieUtils;
import com.ikutarian.util.JsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
    public ApiResult register(@RequestBody UserBo userBo,
                              HttpServletRequest request,
                              HttpServletResponse response) {
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

        User user = userService.createUser(userBo);
        CookieUtils.setCookie(request, response, "user", JsonUtils.objToJson(user), true);

        // TODO 生成用户的Token，存入Redis
        // TODO 同步购物车数据

        return ApiResult.ok();
    }

    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("login")
    public ApiResult login(@RequestBody UserBo userBo,
                           HttpServletRequest request,
                           HttpServletResponse response) {
        String username = userBo.getUsername();
        String password = userBo.getPassword();

        if (StringUtils.isAnyBlank(username, password)) {
            return ApiResult.error("用户名或密码不能为空");
        }

        User user = userService.queryUserForLogin(username, password);
        if (user == null) {
            return ApiResult.error("用户名或密码不正确");
        }
        CookieUtils.setCookie(request, response, "user", JsonUtils.objToJson(user), true);

        // TODO 生成用户的Token，存入Redis
        // TODO 同步购物车数据

        return ApiResult.ok(user);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("logout")
    public ApiResult logout(@RequestParam String userId,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        // 清除Cookie
        CookieUtils.deleteCookie(request, response, "user");
        // TODO 清空购物车
        // TODO 分布式会话中要清除用户数据

        return ApiResult.ok();
    }
}
