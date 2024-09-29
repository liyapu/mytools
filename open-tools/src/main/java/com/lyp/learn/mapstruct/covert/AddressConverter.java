package com.lyp.learn.mapstruct.covert;

import com.lyp.learn.mapstruct.bo.AddressBO;
import com.lyp.learn.mapstruct.dto.AddressDTO;
import org.mapstruct.Mapper;

/**
 * @author liyapu
 * @date 2024-05-23 11:36
 * @description
 */
@Mapper
public interface AddressConverter {

    /**
     * 地址对象转换
     *
     * @param addressBO
     * @return
     */
    AddressDTO addressDtoConverterFromBO(AddressBO addressBO);

}
