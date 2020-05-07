package com.wyp.dao;

import com.wyp.dao.domain.req.StatisticsReqDo;
import com.wyp.dao.domain.res.StatisticsResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author： wyp
 * @date 2020/3/29
 * @description: 统计
 */
@Mapper
@Component
public interface StatisticsDao {
    //新增统计信息
    int insertStatistics(@Param("companyId") Long companyId);
    //更改统计信息
    int updateStatistics(StatisticsReqDo statisticsReqDo);
    //根据公司id获取统计信息
    StatisticsResDo getStatisticsInfo(@Param("companyId") Long companyId);

}
