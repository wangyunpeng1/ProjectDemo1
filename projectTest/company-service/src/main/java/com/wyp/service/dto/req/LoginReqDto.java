package com.wyp.service.dto.req;

import lombok.Data;

/**
 * @author: wyp
 * @date: 2020/1/22
 * @description: 登录
 */
@Data
public class LoginReqDto {
    /**
     * 登录账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
