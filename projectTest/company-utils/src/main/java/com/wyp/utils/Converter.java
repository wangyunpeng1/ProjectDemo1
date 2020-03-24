package com.wyp.utils;

/**
 * @author: wyp
 * @date: 2020/2/1
 * @description: 接口
 */
public interface Converter<F, T> {
    void convert(F from, T to);
}

