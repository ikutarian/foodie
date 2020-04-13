package com.ikutarian.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ikutarian.pojo.Carousel;

import java.util.List;

public interface CarouselService extends IService<Carousel> {

    /**
     * 查询所有轮播图列表
     */
    List<Carousel> queryAll(Integer isShow);
}
