package com.wyp.service;

import com.wyp.service.dto.req.LoginReqDto;

/**
 * @author: wyp
 * @date: 2020/2/1
 * @description: 登录
 */
public interface LoginService {

    Boolean login(LoginReqDto loginReqDto);
}
