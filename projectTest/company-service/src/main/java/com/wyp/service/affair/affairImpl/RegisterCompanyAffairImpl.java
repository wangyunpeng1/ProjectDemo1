package com.wyp.service.affair.affairImpl;

import com.wyp.dao.RegisterDao;
import com.wyp.dao.StatisticsDao;
import com.wyp.dao.domain.req.RegisterReqDo;
import com.wyp.service.affair.RegisterCompanyAffair;
import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.utils.CommonUtil;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wyp
 * @date 2020/4/3
 * @description 注册公司事务
 */
@Service
public class RegisterCompanyAffairImpl implements RegisterCompanyAffair {
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private StatisticsDao statisticsDao;

    @Transactional
    public void registerCompanyAffair(RegisterReqDto registerReqDto) {
        //参数转换
        RegisterReqDo registerReqDo = ConvertBeanUtil.convertToBean(registerReqDto,RegisterReqDo.class);
        //赋值公司id
        Long id = CommonUtil.randomNumber();
        while (registerDao.isCompanyIdExist(id)){
            id = CommonUtil.randomNumber();
        }
        registerReqDo.setCompanyId(id);
        registerDao.registerCompany(registerReqDo);
        statisticsDao.insertStatistics(registerReqDo.getCompanyId());
    }
}
