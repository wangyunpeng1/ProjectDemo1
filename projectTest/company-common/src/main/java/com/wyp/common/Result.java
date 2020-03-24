package com.wyp.common;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/1/14
 * @description: 返回类
 * @param <T>
 */
@Data
public class Result<T> {
    private String message;
    private T data;
    private Integer code;
    private int count;

    public Result() {
    }

    public Result(T data, String message) {
        this.data = data;
        this.message = message;
    }
    public Result(Integer code,T data, String message) {
        this.data = data;
        this.message = message;
        this.code = code;
    }
    public Result(Integer code,T data,int count, String message) {
        this.data = data;
        this.message = message;
        this.count = count;
        this.code = code;
    }
}
