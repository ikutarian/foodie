package com.ikutarian.controller;

import com.ikutarian.service.UserService;
import com.ikutarian.util.ApiResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("passport")
public class PassportController {

    @Autowired
    private UserService userService;

    @GetMapping("usernameIsExist")
    public ApiResult usernameIsExist(@RequestParam String username) {
        if (StringUtils.isBlank(username)) {
            return ApiResult.error("用户名不能为空");
        }

        boolean isExist = userService.queryUsernameIsExist(username);
        if (isExist) {
            return ApiResult.error("用户名已存在");
        }

        return ApiResult.ok();
    }
}
