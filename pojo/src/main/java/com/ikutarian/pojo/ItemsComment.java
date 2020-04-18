package com.ikutarian.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品评价
 */
@Getter
@Setter
@TableName("items_comments")
public class ItemsComment extends BaseEntity {

    /**
     * id主键
     */
    @TableId
    private String id;

    /**
     * 用户id 用户名须脱敏
     */
    private String userId;

    /**
     * 商品id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品规格id 可为空
     */
    private String itemSpecId;

    /**
     * 规格名称 可为空
     */
    private String sepcName;

    /**
     * 评价等级 1：好评 2：中评 3：差评
     */
    private Integer commentLevel;

    /**
     * 评价内容
     */
    private String content;
}
