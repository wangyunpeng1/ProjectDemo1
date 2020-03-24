package com.wyp.service.dto.req;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/3/24
 * @description: 客户信息查询
 */
@Data
public class CustomerQueryReqDto {
    /**
     * 公司id
     */
    private Long companyId;

    /**
     * 客户id
     */
    private Long customerId;

    /**
     * 客户名字
     */
    private String customerName;

    /**
     * 客户性别
     */
    private String sex;

    /**
     * 客户年龄
     */
    private Integer age;

    /**
     * 手机号
     */
    private String phone;
}
