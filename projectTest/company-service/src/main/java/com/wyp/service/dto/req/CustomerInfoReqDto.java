package com.wyp.service.dto.req;

import lombok.Data;

import java.util.Date;

/**
 * @author: wyp
 * @date: 2020/2/5
 * @description: 客户信息
 */
@Data
public class CustomerInfoReqDto {
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
     * 年龄
     */
    private Integer age;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 加入时间
     */
    private Date createTime;
}
