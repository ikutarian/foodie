package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.pojo.vo.CommentLevelCountVo;
import com.ikutarian.pojo.vo.ShopCartVo;
import com.ikutarian.util.PageGridResult;

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

    /**
     * 根据id查询商品评价数量
     */
    CommentLevelCountVo queryCommentCounts(String itemId);

    /**
     * 根据id查询商品评价（分页）
     */
    PageGridResult queryPagedComments(String itemId, Integer level, Integer page, Integer pageSize);

    /**
     * 根据商品名称搜索商品
     */
    PageGridResult searchItems(String keyword, String sort, Integer page, Integer pageSize);

    /**
     * 根据所属分类搜索商品
     */
    PageGridResult searchItems(Integer catId, String sort, Integer page, Integer pageSize);

    /**
     * 根据规格id查询商品规格信息
     *
     * @param specIds 用逗号分隔的规格id列表
     */
    List<ShopCartVo> queryItemsBySpecIds(String specIds);
}
