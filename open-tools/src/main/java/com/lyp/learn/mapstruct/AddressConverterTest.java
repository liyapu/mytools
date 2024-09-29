package com.lyp.learn.mapstruct;

import com.alibaba.fastjson.JSONObject;
import com.lyp.learn.mapstruct.bean.Item;
import com.lyp.learn.mapstruct.bean.Sku;
import com.lyp.learn.mapstruct.bo.AddressBO;
import com.lyp.learn.mapstruct.bo.CityBO;
import com.lyp.learn.mapstruct.covert.ItemConverter;
import com.lyp.learn.mapstruct.dto.SkuDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liyapu
 * @date 2024-05-23 11:40
 * @description
 */
@Slf4j
public class AddressConverterTest {

    @Test
    public void testBOToDTO1() {
        AddressBO addressBO = new AddressBO();
        addressBO.setProvinceCode(2000);
        addressBO.setProvinceName("河南");

        CityBO city1 = new CityBO();
        city1.setCityName("郑州");
        city1.setCityAge(10);

        CityBO city2 = new CityBO();
        city2.setCityName("商丘");
        city2.setCityAge(800);

        List<CityBO> cityList = new ArrayList<>();
        cityList.add(city1);
        cityList.add(city2);

        addressBO.setCityBOList(cityList);

        Item item = new Item(1L, "iPhone X");
        Sku sku = new Sku(2L, "phone12345", 1000000);
        SkuDTO skuDTO = ItemConverter.INSTANCE.domain2dto(item, sku);
        log.info("skuDTO========={}", JSONObject.toJSONString(skuDTO));

    }
}
