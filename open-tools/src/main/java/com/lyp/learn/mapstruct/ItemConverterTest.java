package com.lyp.learn.mapstruct;

import com.alibaba.fastjson.JSONObject;
import com.lyp.learn.mapstruct.bean.Item;
import com.lyp.learn.mapstruct.bean.Sku;
import com.lyp.learn.mapstruct.covert.ItemConverter;
import com.lyp.learn.mapstruct.dto.SkuDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author liyapu
 * @date 2021-07-26 16:45
 * @desc
 */
@Slf4j
public class ItemConverterTest {

    @Test
    public void testDomain2dto() {
        Item item = new Item(1L, "iPhone X");
        Sku sku = new Sku(2L, "phone12345", 1000000);
        SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);
        log.info("skuDTO========={}", JSONObject.toJSONString(skuDTO));

    }
}
