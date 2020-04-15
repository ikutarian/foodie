package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.ItemImgMapper;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.service.ItemImgService;
import org.springframework.stereotype.Service;

@Service
public class ItemImgServiceImpl extends ServiceImpl<ItemImgMapper, ItemImg> implements ItemImgService {
}
