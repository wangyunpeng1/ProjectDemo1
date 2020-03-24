package com.wyp.dao.domain.req;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/2/1
 * @description: 登录
 */
@Data
public class LoginReqDo {
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
