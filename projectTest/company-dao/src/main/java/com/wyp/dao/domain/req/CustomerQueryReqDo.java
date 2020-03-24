package com.wyp.dao.domain.req;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/3/24
 * @description: 客户信息查询
 */
@Data
public class CustomerQueryReqDo {
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

    /**
     * 总数
     */
    private int currIndex;

    /**
     * 每页多少个
     */
    private int pageSize;
}
