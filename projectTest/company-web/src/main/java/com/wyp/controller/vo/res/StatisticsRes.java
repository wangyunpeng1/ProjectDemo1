package com.wyp.controller.vo.res;

import lombok.Data;

import java.util.List;

/**
 * @author wyp
 * @date 2020/3/26
 * @description 统计信息
 */
@Data
public class StatisticsRes {

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

    /**
     * 公司客户周增加率
     */
    private Double customerAdd;

    /**
     * 成立天数
     */
    private Long establishDayCount;

    /**
     * 成立时间
     */
    private String establishDay;

}
