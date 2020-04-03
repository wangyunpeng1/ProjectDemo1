package com.wyp.service.impl;

import com.google.common.base.Preconditions;
import com.wyp.dao.RegisterDao;
import com.wyp.dao.domain.req.RegisterReqDo;
import com.wyp.dao.domain.res.RegisterResDo;
import com.wyp.service.RegisterService;
import com.wyp.service.affair.RegisterCompanyAffair;
import com.wyp.service.affair.RegisterStaffAffair;
import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.service.dto.req.StaffReqDto;
import com.wyp.service.dto.res.RegisterResDto;
import com.wyp.utils.CommonUtil;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 注册
 */
@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private RegisterStaffAffair registerStaffAffair;
    @Autowired
    private RegisterCompanyAffair registerCompanyAffair;

    public Boolean registerCompany(RegisterReqDto registerReqDto) {
        if (registerReqDto == null){
            return Boolean.FALSE;
        }
        registerCompanyAffair.registerCompanyAffair(registerReqDto);
        return Boolean.TRUE;
    }

    public Boolean registerStaff(RegisterReqDto registerReqDto, StaffReqDto staffReqDto) {
        if (registerReqDto == null && staffReqDto == null){
            return Boolean.FALSE;
        }
        //事务一致
        registerStaffAffair.registerStaffAffair(registerReqDto,staffReqDto);
        return Boolean.TRUE;
    }

    public Boolean isAccountExist(String account) {
        return registerDao.isAccountExist(account);
    }

    public Boolean isCompanyNameExist(String companyName) {
        return registerDao.isCompanyNameExist(companyName);
    }

    public RegisterResDto infoByAccount(String account) {
        Preconditions.checkArgument(account != null,"账号不能为空");
        RegisterResDo registerResDo = registerDao.infoByAccount(account);
        if (registerResDo == null){
            return null;
        }
        //参数转换
        RegisterResDto registerResDto = ConvertBeanUtil.convertToBean(registerResDo,RegisterResDto.class);
        return registerResDto;
    }
}