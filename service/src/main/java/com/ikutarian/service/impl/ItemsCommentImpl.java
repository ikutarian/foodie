package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.ItemsCommentMapper;
import com.ikutarian.pojo.ItemsComment;
import com.ikutarian.service.ItemsCommentService;
import org.springframework.stereotype.Service;

@Service
public class ItemsCommentImpl extends ServiceImpl<ItemsCommentMapper, ItemsComment> implements ItemsCommentService {
}
