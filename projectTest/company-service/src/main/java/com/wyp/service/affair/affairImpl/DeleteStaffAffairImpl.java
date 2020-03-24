package com.wyp.service.affair.affairImpl;

import com.wyp.dao.CompanyDao;
import com.wyp.dao.RegisterDao;
import com.wyp.service.affair.DeleteStaffAffair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: wyp
 * @date: 2020/2/27
 * @description: 员工删除事务
 */
@Service
public class DeleteStaffAffairImpl implements DeleteStaffAffair {

    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private CompanyDao companyDao;

    @Transactional
    public void deleteStaffAffair(String account, Long staffId) {
        registerDao.deleteAccount(account);
        companyDao.deleteStaff(staffId);
    }
}
