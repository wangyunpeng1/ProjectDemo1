package com.wyp.service.dto.res;

import lombok.Data;

/**
 * @author wyp
 * @date 2020/4/6
 * @description 数据统计
 */
@Data
public class StatisticsResDto {
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
