package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.Category;
import com.ikutarian.pojo.vo.CategoryVo;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 查询所有一级分类
     */
    List<Category> queryAllRootLevelCat();

    /**
     * 根据一级分类的ID，查询下一级的分类列表
     */
    List<CategoryVo> getSubCatList(Integer rootCatId);
}
