package com.ikutarian.controller;

import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.pojo.vo.ItemInfoVo;
import com.ikutarian.service.ItemService;
import com.ikutarian.util.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
            return ApiResult.error("item不能为空");
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
}
