package com.ikutarian.controller;

import com.ikutarian.pojo.bo.ShopCartBo;
import com.ikutarian.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(value = "购物车接口", tags = {"购物车相关的接口"})
@RestController
@RequestMapping("shopcart")
@Slf4j
public class ShopCartController {

    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("add")
    public ApiResult add(@RequestParam String userId,
                         @RequestBody ShopCartBo shopCartBo,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        if (StringUtils.isBlank(userId)) {
            return ApiResult.error("参数不能为空");
        }

        log.info("添加商品到购物车: {}", shopCartBo);
        // TODO 同步购物车数据到Redis

        return ApiResult.ok();
    }

    @ApiOperation(value = "从购物车中删除商品", notes = "从购物车中删除商品", httpMethod = "POST")
    @PostMapping("del")
    public ApiResult del(@RequestParam String userId,
                         @RequestParam String itemSpecId,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        if (StringUtils.isAnyBlank(userId, itemSpecId)) {
            return ApiResult.error("参数不能为空");
        }

        // TODO 同步购物车数据到Redis

        return ApiResult.ok();
    }
}
