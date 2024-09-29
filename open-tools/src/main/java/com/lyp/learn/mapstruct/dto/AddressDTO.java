package com.lyp.learn.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author liyapu
 * @date 2024-05-23 11:29
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    /**
     * 省code
     */
    private Integer provinceCode;

    /**
     * 省名称
     */
    private String provinceName;


    private List<CityDTO> cityDTOList;
}
