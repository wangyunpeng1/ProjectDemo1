package com.wyp.dao;

import com.wyp.dao.domain.req.StaffReqDo;
import com.wyp.dao.domain.res.StaffResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author： wyp
 * @date 2020/2/1
 * @description: 公司
 */
@Mapper
@Component
public interface CompanyDao {
    //分页查询
    List<StaffResDo> query(@Param("companyId") Long companyId,@Param("currIndex") int currIndex,@Param("pageSize") int pageSize);
    //修改权限
    int updatePower(@Param("staffId") Long staffId,@Param("updatePower") Integer updatePower);
    //删除权限
    int deletePower(@Param("staffId") Long staffId,@Param("deletePower") Integer deletePower);
    //插入权限
    int insertPower(@Param("staffId") Long staffId,@Param("insertPower") Integer insertPower);
    //增加员工
    int insertStaff(StaffReqDo staffReqDo);
    //通过账号获取员工信息
    StaffResDo staffInfo(@Param("account") String account);
    //判断员工是否存在
    Boolean isStaffIdExist(@Param("staffId") Long staffId);
    //删除员工
    int deleteStaff(@Param("staffId") Long staffId);
    //获取员工总数
    int count(@Param("companyId") Long companyId);
    //获取公司id集合
    List<Long> getCompanyId();
    //获取最近一个月的员工增加数
    int monthCount(@Param("companyId") Long companyId, @Param("startDate") Date startDate);

}
