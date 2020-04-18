package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.enums.CommentLevel;
import com.ikutarian.mapper.ItemMapper;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.pojo.ItemsComment;
import com.ikutarian.pojo.vo.CommentLevelCountVo;
import com.ikutarian.pojo.vo.ItemCommentVo;
import com.ikutarian.service.ItemImgService;
import com.ikutarian.service.ItemParamService;
import com.ikutarian.service.ItemService;
import com.ikutarian.service.ItemSpecService;
import com.ikutarian.service.ItemsCommentService;
import com.ikutarian.util.PageGridResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    private final ItemImgService itemImgService;
    private final ItemSpecService itemSpecService;
    private final ItemParamService itemParamService;
    private final ItemsCommentService itemsCommentService;

    public ItemServiceImpl(ItemImgService itemImgService,
                           ItemSpecService itemSpecService,
                           ItemParamService itemParamService,
                           ItemsCommentService itemsCommentService) {
        this.itemImgService = itemImgService;
        this.itemSpecService = itemSpecService;
        this.itemParamService = itemParamService;
        this.itemsCommentService = itemsCommentService;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Item queryItemById(String itemId) {
        return this.getById(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemImg> queryItemImageList(String itemId) {
        return itemImgService.list(new LambdaQueryWrapper<ItemImg>()
                .eq(ItemImg::getItemId, itemId));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<ItemSpec> queryItemSpecList(String itemId) {
        return itemSpecService.list(new LambdaQueryWrapper<ItemSpec>()
                .eq(ItemSpec::getItemId, itemId));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemParam queryItemParam(String itemId) {
        return itemParamService.getOne(new LambdaQueryWrapper<ItemParam>()
                .eq(ItemParam::getItemId, itemId));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public CommentLevelCountVo queryCommentCounts(String itemId) {
        Integer goodCounts = getCommentCounts(itemId, CommentLevel.GOOD.getType());
        Integer normalCounts = getCommentCounts(itemId, CommentLevel.NORMAL.getType());
        Integer badCounts = getCommentCounts(itemId, CommentLevel.BAD.getType());
        Integer totalCount = goodCounts + normalCounts + badCounts;  // 减少一次SQL查询操作

        CommentLevelCountVo countVo = new CommentLevelCountVo();
        countVo.setTotalCounts(totalCount);
        countVo.setGoodCounts(goodCounts);
        countVo.setNormalCounts(normalCounts);
        countVo.setBadCounts(badCounts);

        return countVo;
    }

    private Integer getCommentCounts(String itemId, Integer level) {
        return itemsCommentService.count(new LambdaQueryWrapper<ItemsComment>()
                .eq(ItemsComment::getCommentLevel, level));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public PageGridResult queryPagedComments(String itemId, Integer level,
                                                  Integer page, Integer pageSize) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("itemId", itemId);
        paramsMap.put("level", level);

        IPage<ItemCommentVo> iPage = this.getBaseMapper().queryItemComments(new Page<>(page, pageSize), paramsMap);
        return PageGridResult.fromIPage(iPage);
    }

}
