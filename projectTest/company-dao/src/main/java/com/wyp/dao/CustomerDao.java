package com.wyp.dao;

import com.wyp.dao.domain.req.CustomerInfoReqDo;
import com.wyp.dao.domain.req.CustomerQueryReqDo;
import com.wyp.dao.domain.res.CustomerInfoResDo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author： wyp
 * @date 2020/2/5
 * @description: 客户
 */
@Mapper
@Component
public interface CustomerDao {

    List<CustomerInfoResDo> queryCustomer(CustomerQueryReqDo customerQueryReqDo);

    int insertCustomer(CustomerInfoReqDo customerInfoReqDo);

    int updateCustomer(CustomerInfoReqDo customerInfoReqDo);

    int deleteCustomer(@Param("customerId") Long customerId);

    Boolean isCustomerIdExit(@Param("customerId") Long customerId);

    int count(CustomerQueryReqDo customerQueryReqDo);
}
