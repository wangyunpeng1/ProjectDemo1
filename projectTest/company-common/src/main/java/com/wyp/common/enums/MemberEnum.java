package com.wyp.common.enums;

import lombok.Getter;

/**
 * @author: wyp
 * @date: 2020/2/3
 * @description: 权限
 */
@Getter
public enum MemberEnum {
    ADMIN(1,"管理员"),
    STAFF(2,"员工")
    ;
    private Integer code;
    private String message;

    MemberEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static MemberEnum getByCode(Integer code){
        if (code != null){
            for (MemberEnum memberEnum : values()){
                if (memberEnum.getCode().equals(code)){
                    return memberEnum;
                }
            }
        }
        return null;
    }
}
