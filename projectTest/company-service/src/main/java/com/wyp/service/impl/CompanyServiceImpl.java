package com.wyp.service.impl;

import com.google.common.base.Preconditions;
import com.wyp.common.enums.PowerEnum;
import com.wyp.dao.CompanyDao;
import com.wyp.dao.domain.res.StaffResDo;
import com.wyp.service.CompanyService;
import com.wyp.service.affair.DeleteStaffAffair;
import com.wyp.service.dto.res.StaffResDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @author: wyp
 * @date: 2020/2/6
 * @description: 公司
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    private CompanyDao companyDao;
    @Autowired
    private DeleteStaffAffair deleteStaffAffair;

    public List<StaffResDto> query(Long companyId, HttpServletRequest request) {
        //分页
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int currIndex = (page-1)*limit;
        List<StaffResDo> staffResDos = companyDao.query(companyId,currIndex,limit);
        if (CollectionUtils.isEmpty(staffResDos)){
            return null;
        }
        //参数转换
        List<StaffResDto> staffResDtos = ConvertBeanUtil.convertToList(staffResDos, StaffResDto.class);
        return staffResDtos;
    }

    public Boolean updatePower(Long staffId, Integer updatePower) {
        Preconditions.checkArgument(staffId != null,"员工id不能为空");
        Preconditions.checkArgument(updatePower != null,"修改权限不能为空");
        //判断权限然后进行修改
        if (PowerEnum.EXIST_POWER.getCode().equals(updatePower)){
            updatePower = PowerEnum.NOT_EXIST_POWER.getCode();
        }else {
            updatePower = PowerEnum.EXIST_POWER.getCode();
        }
        companyDao.updatePower(staffId,updatePower);
        return Boolean.TRUE;
    }

    public Boolean insertPower(Long staffId, Integer insertPower) {
        Preconditions.checkArgument(staffId != null,"员工id不能为空");
        Preconditions.checkArgument(insertPower != null,"插入权限不能为空");
        //判断权限然后进行修改
        if (PowerEnum.EXIST_POWER.getCode().equals(insertPower)){
            insertPower = PowerEnum.NOT_EXIST_POWER.getCode();
        }else {
            insertPower = PowerEnum.EXIST_POWER.getCode();
        }
        companyDao.insertPower(staffId,insertPower);
        return Boolean.TRUE;
    }

    public Boolean deletePower(Long staffId, Integer deletePower) {
        Preconditions.checkArgument(staffId != null,"员工id不能为空");
        Preconditions.checkArgument(deletePower != null,"删除权限不能为空");
        //判断权限然后进行修改
        if (PowerEnum.EXIST_POWER.getCode().equals(deletePower)){
            deletePower = PowerEnum.NOT_EXIST_POWER.getCode();
        }else {
            deletePower = PowerEnum.EXIST_POWER.getCode();
        }
        companyDao.deletePower(staffId,deletePower);
        return Boolean.TRUE;
    }

    public StaffResDto staffInfo(String account) {
        Preconditions.checkArgument(account != null,"账号不能为空");
        StaffResDo staffResDo = companyDao.staffInfo(account);
        if (staffResDo == null){
            return null;
        }
        //参数转换
        StaffResDto staffResDto = ConvertBeanUtil.convertToBean(staffResDo,StaffResDto.class);
        return staffResDto;
    }

    public Boolean deleteStaff(String account, Long staffId) {
        Preconditions.checkArgument(account != null,"账号不能为空");
        Preconditions.checkArgument(staffId != null,"staffId不能为空");
        deleteStaffAffair.deleteStaffAffair(account,staffId);
        return Boolean.TRUE;
    }

    public int count(Long companyId) {
        return companyDao.count(companyId);
    }

}
