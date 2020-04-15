package com.ikutarian.pojo.vo;

import com.ikutarian.pojo.Item;
import com.ikutarian.pojo.ItemImg;
import com.ikutarian.pojo.ItemParam;
import com.ikutarian.pojo.ItemSpec;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 商品信息
 */
@Getter
@Setter
public class ItemInfoVo {

    private Item item;
    private List<ItemImg> itemImgList;
    private List<ItemSpec> itemSpecList;
    private ItemParam itemParams;
}
