package com.wyp.dao;

import com.wyp.dao.domain.req.RegisterReqDo;
import com.wyp.dao.domain.res.RegisterResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 注册
 */
@Mapper
@Component
public interface RegisterDao {
    //注册公司
    int registerCompany(RegisterReqDo registerReqDo);
    //判断账号是否存在
    Boolean isAccountExist(@Param("account") String account);
    //判断公司名字是否存在
    Boolean isCompanyNameExist(@Param("companyName") String companyName);
    //通过账号获取公司信息
    RegisterResDo infoByAccount(@Param("account") String account);
    //判断公司id是否存在
    Boolean isCompanyIdExist(@Param("companyId") Long companyId);
    //删除账号
    int deleteAccount(@Param("account") String account);
}
