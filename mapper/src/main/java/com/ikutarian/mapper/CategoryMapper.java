package com.ikutarian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ikutarian.pojo.Category;
import com.ikutarian.pojo.vo.CategoryVo;

import java.util.List;

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> getSubCatList(Integer rootCatId);
}
