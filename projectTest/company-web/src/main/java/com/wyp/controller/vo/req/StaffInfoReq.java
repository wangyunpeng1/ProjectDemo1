package com.wyp.controller.vo.req;

import lombok.Data;

import java.util.Date;

/**
 * @author: wyp
 * @date: 2020/2/4
 * @description: 员工信息/权限
 */
@Data
public class StaffInfoReq {
    /**
     * 员工id
     */
    private Long staffId;
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 员工姓名
     */
    private String staffName;
    /**
     * 账号
     */
    private String account;
    /**
     * 增加权限(0 无，1 有)
     */
    private Integer insertPower;
    /**
     * 删除权限(0 无，1 有)
     */
    private Integer deletePower;
    /**
     * 修改权限(0 无，1 有)
     */
    private Integer updatePower;
    /**
     * 创建时间
     */
    private Date createTime;
}
