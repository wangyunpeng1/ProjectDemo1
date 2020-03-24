package com.wyp.common.enums;

import lombok.Getter;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 企业性质
 */
@Getter
public enum CompanyNatureEnum {
    STATE_ENTERPRISE(1,"国有企业"),
    COLLECTIVE_ENTERPRISE(2,"集体企业"),
    PRIVATE_ENTERPRISE(3,"私营企业"),
    OTHER_ENTERPRISE(4,"其他企业")
    ;
    private Integer code;

    private String name;

    CompanyNatureEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public static CompanyNatureEnum getByCode(Integer code){
        if (code != null){
            for (CompanyNatureEnum companyNatureEnum : values()){
                if (companyNatureEnum.getCode().equals(code)){
                    return companyNatureEnum;
                }
            }
        }
        return null;
    }
}
