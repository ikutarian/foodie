package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.CategoryMapper;
import com.ikutarian.pojo.Category;
import com.ikutarian.pojo.vo.CategoryVo;
import com.ikutarian.pojo.vo.NewItemsVo;
import com.ikutarian.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Category> queryAllRootLevelCat() {
        return this.list(new LambdaQueryWrapper<Category>()
            .eq(Category::getType, 1));
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<CategoryVo> getSubCatList(Integer rootCatId) {
        return this.getBaseMapper().getSubCatList(rootCatId);
    }

    @Override
    public List<NewItemsVo> getSixNewItemsLazy(Integer rootCatId) {
        Map<String, Object> map = new HashMap<>();
        map.put("rootCatId", rootCatId);
        return this.getBaseMapper().getSixNewItemsLazy(map);
    }
}
