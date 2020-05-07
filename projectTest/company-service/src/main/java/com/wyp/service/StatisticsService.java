package com.wyp.service;

import com.wyp.service.dto.res.StatisticsResDto;

/**
 * @author wyp
 * @date 2020/4/6
 * @description 统计信息
 */
public interface StatisticsService {
    StatisticsResDto getStatistics(Long planId);
}
