package com.wyp.service.job;

import com.wyp.dao.CompanyDao;
import com.wyp.dao.CustomerDao;
import com.wyp.dao.StatisticsDao;
import com.wyp.dao.domain.req.CustomerQueryReqDo;
import com.wyp.dao.domain.req.StatisticsReqDo;
import com.wyp.dao.domain.res.StatisticsResDo;
import javafx.scene.chart.PieChart;
import org.apache.commons.lang3.time.DateUtils;
import org.assertj.core.util.DateUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author wyp
 * @date 2020/4/4
 * @datecription 数据统计job
 */
public class StatisticsJob implements Job {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private StatisticsDao statisticsDao;

    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        //获取公司id集合
        List<Long> ids = companyDao.getCompanyId();
        for (Long companyId : ids){
            StatisticsReqDo statisticsReqDo = new StatisticsReqDo();
            statisticsReqDo.setCompanyId(companyId);
            //获取员工总数
            statisticsReqDo.setStaffCount(companyDao.count(companyId));
            CustomerQueryReqDo customerQueryReqDo = new CustomerQueryReqDo();
            customerQueryReqDo.setCompanyId(companyId);
            //获取客户总数
            statisticsReqDo.setCustomerCount(customerDao.count(customerQueryReqDo));
            //获取客户男生总数
            customerQueryReqDo.setSex("男");
            statisticsReqDo.setCustomerCount(customerDao.count(customerQueryReqDo));
            //获取客户女生总数
            customerQueryReqDo.setSex("女");
            statisticsReqDo.setCustomerCount(customerDao.count(customerQueryReqDo));
            customerQueryReqDo.setSex(null);
            //获取最近7天的客户增加数
            Date date = new Date();
            Date startCustomerDate = DateUtils.addDays(date, -7);
            customerQueryReqDo.setDay(startCustomerDate);
            statisticsReqDo.setWeekConsumerCount(customerDao.countSeven(customerQueryReqDo));
            //获取最近1个月的员工增加数
            Date startStaffDate = DateUtils.addMonths(date, -1);
            statisticsReqDo.setMonthStaffCount(companyDao.monthCount(companyId, startStaffDate));

            statisticsDao.updateStatistics(statisticsReqDo);
        }
    }
}
