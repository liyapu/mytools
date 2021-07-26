package com.lyp.learn.mapstruct.covert;

import com.lyp.learn.mapstruct.bean.Item;
import com.lyp.learn.mapstruct.bean.Sku;
import com.lyp.learn.mapstruct.dto.SkuDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ItemConverter {
    ItemConverter INSTANCE = Mappers.getMapper(ItemConverter.class);

    /**
     * 多对一
     * MapStruct 可以将几种类型的对象映射为另外一种类型，比如将多个 DO 对象转换为 DTO
     *
     * 例子
     *
     * 两个 DO 对象 Item 和 Sku，一个 DTO 对象 SkuDTO
     * @param item
     * @param sku
     * @return
     */
    @Mappings({
                      @Mapping(source = "sku.id", target = "skuId"),
                      @Mapping(source = "sku.code",target = "skuCode"),
                      @Mapping(source = "sku.price",target = "skuPrice"),
                      @Mapping(source = "item.id",target = "itemId"),
                      @Mapping(source = "item.title",target = "itemName")
              })
    SkuDTO domain2dto(Item item, Sku sku);
}
