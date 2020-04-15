package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.ItemParamMapper;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.service.ItemParamService;
import org.springframework.stereotype.Service;

@Service
public class ItemParamServiceImpl extends ServiceImpl<ItemParamMapper, ItemParam> implements ItemParamService {
}
