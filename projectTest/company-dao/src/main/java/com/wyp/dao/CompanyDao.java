package com.wyp.dao;

import com.wyp.dao.domain.req.StaffReqDo;
import com.wyp.dao.domain.res.StaffResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author： wyp
 * @date 2020/2/1
 * @description: 公司
 */
@Mapper
@Component
public interface CompanyDao {

    List<StaffResDo> query(@Param("companyId") Long companyId,@Param("currIndex") int currIndex,@Param("pageSize") int pageSize);

    int updatePower(@Param("staffId") Long staffId,@Param("updatePower") Integer updatePower);

    int deletePower(@Param("staffId") Long staffId,@Param("deletePower") Integer deletePower);

    int insertPower(@Param("staffId") Long staffId,@Param("insertPower") Integer insertPower);

    int insertStaff(StaffReqDo staffReqDo);

    StaffResDo staffInfo(@Param("account") String account);

    Boolean isStaffIdExist(@Param("staffId") Long staffId);

    int deleteStaff(@Param("staffId") Long staffId);

    int count(@Param("companyId") Long companyId);
}
