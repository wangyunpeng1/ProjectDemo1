package com.wyp.service;

import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.service.dto.req.StaffReqDto;
import com.wyp.service.dto.res.RegisterResDto;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 注册
 */
public interface RegisterService {

    Boolean registerCompany(RegisterReqDto registerReqDto);

    Boolean registerStaff(RegisterReqDto registerReqDto,StaffReqDto staffReqDto);

    Boolean isAccountExist(String account);

    Boolean isCompanyNameExist(String companyName);

    RegisterResDto infoByAccount(String account);
}
