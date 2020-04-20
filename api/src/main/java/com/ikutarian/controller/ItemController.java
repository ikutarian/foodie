package com.ikutarian.controller;

import com.ikutarian.constant.PageConstants;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.pojo.vo.CommentLevelCountVo;
import com.ikutarian.pojo.vo.ItemInfoVo;
import com.ikutarian.service.ItemService;
import com.ikutarian.util.ApiResult;
import com.ikutarian.util.PageGridResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(value = "商品接口", tags = {"商品信息展示相关的接口"})
@RestController
@RequestMapping("items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("info/{itemId}")
    public ApiResult itemInfo(@ApiParam(name = "itemId", value = "商品id", required = true)
                              @PathVariable String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return ApiResult.error("itemId不能为空");
        }

        Item item = itemService.queryItemById(itemId);
        List<ItemImg> itemImgList = itemService.queryItemImageList(itemId);
        List<ItemSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemParam itemParam = itemService.queryItemParam(itemId);

        ItemInfoVo itemInfoVo = new ItemInfoVo();
        itemInfoVo.setItem(item);
        itemInfoVo.setItemImgList(itemImgList);
        itemInfoVo.setItemSpecList(itemSpecList);
        itemInfoVo.setItemParams(itemParam);

        return ApiResult.ok(itemInfoVo);
    }

    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("commentLevel")
    public ApiResult commentLevel(@ApiParam(name = "itemId", value = "商品id", required = true)
                                  @RequestParam String itemId) {
        if (StringUtils.isBlank(itemId)) {
            return ApiResult.error("itemId不能为空");
        }

        CommentLevelCountVo countVo = itemService.queryCommentCounts(itemId);
        return ApiResult.ok(countVo);
    }

    @ApiOperation(value = "查询商品评价", notes = "查询商品评价", httpMethod = "GET")
    @GetMapping("comments")
    public ApiResult comments(@ApiParam(name = "itemId", value = "商品id", required = true)
                              @RequestParam String itemId,
                              @ApiParam(name = "level", value = "评价等级", required = true)
                              @RequestParam Integer level,
                              @ApiParam(name = "page", value = "当前页", required = true)
                              @RequestParam Integer page,
                              @ApiParam(name = "pageSize", value = "每页的查询个数", required = true)
                              @RequestParam Integer pageSize) {
        if (StringUtils.isBlank(itemId)) {
            return ApiResult.error("itemId不能为空");
        }
        if (page == null) {
            page = PageConstants.DEFAULT_PAGE;
        }
        if (pageSize == null) {
            pageSize = PageConstants.DEFAULT_PAGE_SIZE;
        }

        PageGridResult pageGridResult = itemService.queryPagedComments(itemId, level, page, pageSize);
        return ApiResult.ok(pageGridResult);
    }
}
