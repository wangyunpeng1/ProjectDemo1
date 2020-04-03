package com.wyp.dao;

import com.wyp.dao.domain.req.LoginReqDo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * @author： wyp
 * @date 2020/2/1
 * @description: 登录
 */
@Mapper
@Component
public interface LoginDao {
    //登录
    Boolean login(LoginReqDo loginReqDo);
}
