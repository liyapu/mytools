package com.lyp.learn.mapstruct.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyapu
 * @date 2024-05-23 11:32
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDTO {

    private String cityName;
    private Integer cityAge;
}
