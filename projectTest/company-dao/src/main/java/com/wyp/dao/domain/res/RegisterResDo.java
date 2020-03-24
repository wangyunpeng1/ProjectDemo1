package com.wyp.dao.domain.res;

import lombok.Data;

import java.util.Date;

/**
 * @author: wyp
 * @date: 2020/2/7
 * @description: 注册信息
 */
@Data
public class RegisterResDo {
    /**
     * 公司id
     */
    private Long companyId;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 公司名
     */
    private String companyName;
    /**
     * 员工名
     */
    private String staffName;
    /**
     * 公司性质(见枚举 CompanyNatureEnum)
     */
    private Integer companyNature;
    /**
     * 成员(见枚举 MemberEnum)
     */
    private Integer member;
    /**
     * 注册时间
     */
    private Date createTime;
}
