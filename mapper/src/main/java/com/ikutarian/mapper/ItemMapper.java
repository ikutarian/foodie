package com.ikutarian.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.vo.ItemCommentVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface ItemMapper extends BaseMapper<Item> {

    IPage<ItemCommentVo> queryItemComments(Page<ItemCommentVo> page, @Param("paramsMap") Map<String, Object> map);
}
