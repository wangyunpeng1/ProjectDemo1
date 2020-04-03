package com.wyp.dao;

import com.wyp.dao.domain.req.CustomerInfoReqDo;
import com.wyp.dao.domain.req.CustomerQueryReqDo;
import com.wyp.dao.domain.res.CustomerInfoResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author： wyp
 * @date 2020/2/5
 * @description: 客户
 */
@Mapper
@Component
public interface CustomerDao {
    //分页查询客户信息
    List<CustomerInfoResDo> queryCustomer(CustomerQueryReqDo customerQueryReqDo);
    //插入客户
    int insertCustomer(CustomerInfoReqDo customerInfoReqDo);
    //修改客户
    int updateCustomer(CustomerInfoReqDo customerInfoReqDo);
    //删除客户
    int deleteCustomer(@Param("customerId") Long customerId);
    //判断客户id是否存在
    Boolean isCustomerIdExit(@Param("customerId") Long customerId);
    //获取客户总数
    int count(CustomerQueryReqDo customerQueryReqDo);
    //获取最近7天客户新增数量
    int countSeven(CustomerQueryReqDo customerQueryReqDo);
}
