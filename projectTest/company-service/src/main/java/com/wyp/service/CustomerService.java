package com.wyp.service;

import com.wyp.service.dto.req.CustomerInfoReqDto;
import com.wyp.service.dto.req.CustomerQueryReqDto;
import com.wyp.service.dto.res.CustomerInfoResDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author: wyp
 * @date: 2020/2/4
 * @description: 客户
 */
public interface CustomerService {

    List<CustomerInfoResDto> queryCustomer(CustomerQueryReqDto customerQueryReqDto, HttpServletRequest request);

    Boolean insertCustomer(CustomerInfoReqDto customerInfoReqDto);

    Boolean updateCustomer(CustomerInfoReqDto customerInfoReqDto);

    Boolean deleteCustomer(Long customerId);

    int count(CustomerQueryReqDto customerQueryReqDto);
}
