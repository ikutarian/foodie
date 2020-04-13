package com.ikutarian.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ikutarian.mapper.CarouselMapper;
import com.ikutarian.pojo.Carousel;
import com.ikutarian.service.CarouselService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarouselServiceImpl extends ServiceImpl<CarouselMapper, Carousel> implements CarouselService {

    @Override
    public List<Carousel> queryAll(Integer isShow) {
        return this.list(new LambdaQueryWrapper<Carousel>()
                .eq(Carousel::getIsShow, isShow)
                .orderByDesc(Carousel::getSort));
    }
}
