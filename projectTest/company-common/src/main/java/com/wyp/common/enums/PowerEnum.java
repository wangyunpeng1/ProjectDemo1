package com.wyp.common.enums;

import lombok.Getter;

/**
 * @author: wyp
 * @date: 2020/2/6
 * @description: 是否有权限
 */
@Getter
public enum PowerEnum {
    EXIST_POWER(1,"有权限"),
    NOT_EXIST_POWER(0,"无权限"),
    ;
    private Integer code;
    private String message;

    PowerEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static PowerEnum getByCode(Integer code){
        if (code != null){
            for (PowerEnum powerEnum : values()){
                if (powerEnum.getCode().equals(code)){
                    return powerEnum;
                }
            }
        }
        return null;
    }
}
