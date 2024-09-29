package com.lyp.learn.mapstruct.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author liyapu
 * @date 2024-05-23 11:33
 * @description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityBO {

    private String cityName;
    private Integer cityAge;
}
