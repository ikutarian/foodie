package com.ikutarian.util;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateTimeHandler implements MetaObjectHandler {

    /**
     * 新增数据时执行
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Date now = new Date();
        this.strictInsertFill(metaObject, "createdTime", Date.class, now);
        this.strictInsertFill(metaObject, "updatedTime", Date.class, now);
    }

    /**
     * 更新数据时执行
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "createdTime", Date.class, new Date());
    }
}
