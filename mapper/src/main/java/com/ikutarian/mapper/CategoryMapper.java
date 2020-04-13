package com.ikutarian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ikutarian.pojo.Category;
import com.ikutarian.pojo.vo.CategoryVo;
import com.ikutarian.pojo.vo.NewItemsVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CategoryMapper extends BaseMapper<Category> {

    List<CategoryVo> getSubCatList(Integer rootCatId);

    List<NewItemsVo> getSixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}
