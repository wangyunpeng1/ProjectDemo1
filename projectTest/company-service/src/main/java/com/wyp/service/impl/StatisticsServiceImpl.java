package com.wyp.service.impl;

import com.google.common.base.Preconditions;
import com.wyp.dao.StatisticsDao;
import com.wyp.dao.domain.res.StatisticsResDo;
import com.wyp.service.StatisticsService;
import com.wyp.service.dto.res.StatisticsResDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wyp
 * @date 2020/4/6
 * @description
 */
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    private StatisticsDao statisticsDao;

    public StatisticsResDto getStatistics(Long companyId) {
        Preconditions.checkArgument(companyId != null, "companyId不能等于null");
        StatisticsResDo statisticsResDo = statisticsDao.getStatisticsInfo(companyId);
        if (statisticsResDo == null){
            return null;
        }
        StatisticsResDto statisticsResDto = ConvertBeanUtil.convertToBean(statisticsResDo, StatisticsResDto.class);
        return statisticsResDto;
    }
}
