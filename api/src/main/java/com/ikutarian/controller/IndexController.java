package com.ikutarian.controller;

import com.ikutarian.enums.YesOrNo;
import com.ikutarian.pojo.Carousel;
import com.ikutarian.pojo.Category;
import com.ikutarian.pojo.vo.CategoryVo;
import com.ikutarian.pojo.vo.NewItemsVo;
import com.ikutarian.pojo.vo.SubCategoryVo;
import com.ikutarian.service.CarouselService;
import com.ikutarian.service.CategoryService;
import com.ikutarian.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "首页", tags = {"首页展示相关的接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Autowired
    private CarouselService carouselService;
    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "获取首页轮播图列表", notes = "获取首页轮播图列表", httpMethod = "GET")
    @GetMapping("carousel")
    public ApiResult carousel() {
        List<Carousel> list = carouselService.queryAll(YesOrNo.YES.getType());
        return ApiResult.ok(list);
    }

    @ApiOperation(value = "获取商品分类（一级分类）", notes = "获取商品分类（一级分类）", httpMethod = "GET")
    @GetMapping("cats")
    public ApiResult cats() {
        List<Category> list = categoryService.queryAllRootLevelCat();
        return ApiResult.ok(list);
    }

    @ApiOperation(value = "获取商品子分类", notes = "获取商品子分类", httpMethod = "GET")
    @GetMapping("subCat/{rootCatId}")
    public ApiResult subCat(
            @ApiParam(name = "rootCatId", value = "一级分类的ID", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return ApiResult.error("分类不存在");
        }

        List<CategoryVo> list = categoryService.getSubCatList(rootCatId);
        return ApiResult.ok(list);
    }

    @ApiOperation(value = "查询每个一级分类下的6条最新数据", notes = "查询每个一级分类下的6条最新数据", httpMethod = "GET")
    @GetMapping("sixNewItems/{rootCatId}")
    public ApiResult sixNewItems(
            @ApiParam(name = "rootCatId", value = "一级分类的ID", required = true)
            @PathVariable Integer rootCatId) {
        if (rootCatId == null) {
            return ApiResult.error("分类不存在");
        }

        List<NewItemsVo> list = categoryService.getSixNewItemsLazy(rootCatId);
        return ApiResult.ok(list);
    }
}
