package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.ItemMapper;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.service.ItemImgService;
import com.ikutarian.service.ItemParamService;
import com.ikutarian.service.ItemService;
import com.ikutarian.service.ItemSpecService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    private final ItemImgService itemImgService;
    private final ItemSpecService itemSpecService;
    private final ItemParamService itemParamService;

    public ItemServiceImpl(ItemImgService itemImgService, ItemSpecService itemSpecService, ItemParamService itemParamService) {
        this.itemImgService = itemImgService;
        this.itemSpecService = itemSpecService;
        this.itemParamService = itemParamService;
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
}
