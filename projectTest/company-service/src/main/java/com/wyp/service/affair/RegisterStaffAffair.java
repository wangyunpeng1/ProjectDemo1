package com.wyp.service.affair;

import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.service.dto.req.StaffReqDto;

/**
 * @author: wyp
 * @date: 2020/2/7
 * @description: 员工注册事务
 */
public interface RegisterStaffAffair {

    void registerStaffAffair(RegisterReqDto registerReqDto, StaffReqDto staffReqDto);
}
