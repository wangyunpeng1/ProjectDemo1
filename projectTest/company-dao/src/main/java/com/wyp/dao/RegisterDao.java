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

    int registerCompany(RegisterReqDo registerReqDo);

    Boolean isAccountExist(@Param("account") String account);

    Boolean isCompanyNameExist(@Param("companyName") String companyName);

    RegisterResDo infoByAccount(@Param("account") String account);

    Boolean isCompanyIdExist(@Param("companyId") Long companyId);

    int deleteAccount(@Param("account") String account);
}
