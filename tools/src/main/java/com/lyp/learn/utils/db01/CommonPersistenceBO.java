package com.lyp.learn.utils.db01;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: liya11
 * @create: 2023/3/30 16:36
 **/
@Getter
@Setter
public class CommonPersistenceBO<T> {

    private List<T> insertList;

    private List<T> deleteList;

    private List<T> updateList;
}
