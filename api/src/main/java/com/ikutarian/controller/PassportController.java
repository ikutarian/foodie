package com.ikutarian.controller;

import com.ikutarian.pojo.bo.UserBo;
import com.ikutarian.service.UserService;
import com.ikutarian.util.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

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

    @PostMapping("register")
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
