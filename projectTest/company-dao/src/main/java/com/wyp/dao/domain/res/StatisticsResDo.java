package com.wyp.dao.domain.res;

import lombok.Data;

/**
 * @author： wyp
 * @date 2020/3/29
 * @description: 统计返回信息
 */
@Data
public class StatisticsResDo {

    /**
     * 员工id
     */
    private Long companyId;

    /**
     * 员工总数
     */
    private int staffCount;

    /**
     * 客户总数
     */
    private int customerCount;

    /**
     * 客户男生数
     */
    private int boyCount;

    /**
     * 客户女生数
     */
    private int girlCount;

    /**
     * 最近一周新增客户数
     */
    private int weekConsumerCount;

    /**
     * 最近一个月新增员工人数
     */
    private int monthStaffCount;
}
