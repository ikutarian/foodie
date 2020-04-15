package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;

import java.util.List;

public interface ItemService extends IService<Item> {

    /**
     * 根据id查询商品详情
     */
    Item queryItemById(String itemId);

    /**
     * 根据id查询商品的图片列表
     */
    List<ItemImg> queryItemImageList(String itemId);

    /**
     * 根据id查询商品的规格列表
     */
    List<ItemSpec> queryItemSpecList(String itemId);

    /**
     * 根据id查询商品的参数
     */
    ItemParam queryItemParam(String itemId);
}
