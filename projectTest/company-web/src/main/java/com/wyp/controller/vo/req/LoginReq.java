package com.wyp.controller.vo.req;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/1/14
 * @description: 登录
 */
@Data
public class LoginReq {
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;

}
