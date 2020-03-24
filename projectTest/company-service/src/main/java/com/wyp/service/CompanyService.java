package com.wyp.service;

import com.wyp.service.dto.res.StaffResDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wyp
 * @date: 2020/2/5
 * @description: 公司
 */
public interface CompanyService {

    List<StaffResDto> query(Long companyId, HttpServletRequest request);

    Boolean updatePower(Long staffId,Integer updatePower);

    Boolean insertPower(Long staffId,Integer insertPower);

    Boolean deletePower(Long staffId,Integer deletePower);

    StaffResDto staffInfo(String account);

    Boolean deleteStaff(String account,Long staffId);

    int count(Long companyId);

}
