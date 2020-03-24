package com.wyp.service.impl;

import com.wyp.dao.LoginDao;
import com.wyp.dao.domain.req.LoginReqDo;
import com.wyp.service.LoginService;
import com.wyp.service.dto.req.LoginReqDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wyp
 * @date: 2020/2/1
 * @description: 登录/注册
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    public Boolean login(LoginReqDto loginReqDto) {
        if (loginReqDto == null){
            return Boolean.FALSE;
        }
        //参数转换
        LoginReqDo loginReqDo = ConvertBeanUtil.convertToBean(loginReqDto,LoginReqDo.class);
        return loginDao.login(loginReqDo);
    }
}
