package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.ItemSpecMapper;
import com.ikutarian.pojo.ItemSpec;
import com.ikutarian.service.ItemSpecService;
import org.springframework.stereotype.Service;

@Service
public class ItemSpecServiceImpl extends ServiceImpl<ItemSpecMapper, ItemSpec> implements ItemSpecService {
}
