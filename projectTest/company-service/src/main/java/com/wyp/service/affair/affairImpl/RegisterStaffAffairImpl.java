package com.wyp.service.affair.affairImpl;

import com.wyp.dao.CompanyDao;
import com.wyp.dao.RegisterDao;
import com.wyp.dao.domain.req.RegisterReqDo;
import com.wyp.dao.domain.req.StaffReqDo;
import com.wyp.dao.domain.res.RegisterResDo;
import com.wyp.dao.domain.res.StaffResDo;
import com.wyp.service.affair.RegisterStaffAffair;
import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.service.dto.req.StaffReqDto;
import com.wyp.utils.CommonUtil;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wyp
 * @date: 2020/2/7
 * @description: 员工注册事务
 */
@Service
public class RegisterStaffAffairImpl implements RegisterStaffAffair {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public void registerStaffAffair(RegisterReqDto registerReqDto, StaffReqDto staffReqDto) {

        RegisterReqDo registerReqDo = ConvertBeanUtil.convertToBean(registerReqDto,RegisterReqDo.class);
        registerDao.registerCompany(registerReqDo);
        StaffReqDo staffReqDo = ConvertBeanUtil.convertToBean(staffReqDto,StaffReqDo.class);
        //员工id
        Long id = CommonUtil.randomNumber();
        while (companyDao.isStaffIdExist(id)){
            id = CommonUtil.randomNumber();
        }
        staffReqDo.setStaffId(id);
        companyDao.insertStaff(staffReqDo);

    }
}
