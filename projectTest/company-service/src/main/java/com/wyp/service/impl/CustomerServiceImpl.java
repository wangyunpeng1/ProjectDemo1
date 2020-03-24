package com.wyp.service.impl;

import com.google.common.base.Preconditions;
import com.wyp.dao.CustomerDao;
import com.wyp.dao.domain.req.CustomerInfoReqDo;
import com.wyp.dao.domain.req.CustomerQueryReqDo;
import com.wyp.dao.domain.res.CustomerInfoResDo;
import com.wyp.service.CustomerService;
import com.wyp.service.dto.req.CustomerInfoReqDto;
import com.wyp.service.dto.req.CustomerQueryReqDto;
import com.wyp.service.dto.res.CustomerInfoResDto;
import com.wyp.utils.CommonUtil;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
/**
 * @author: wyp
 * @date: 2020/2/5
 * @description: 客户
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<CustomerInfoResDto> queryCustomer(CustomerQueryReqDto customerQueryReqDto, HttpServletRequest request) {
        CustomerQueryReqDo customerQueryReqDo = ConvertBeanUtil.convertToBean(customerQueryReqDto, CustomerQueryReqDo.class);
        //分页
        int page = Integer.parseInt(request.getParameter("page"));
        int limit = Integer.parseInt(request.getParameter("limit"));
        int currIndex = (page-1)*limit;
        customerQueryReqDo.setCurrIndex(currIndex);
        customerQueryReqDo.setPageSize(limit);
        List<CustomerInfoResDo> customerInfoResDos = customerDao.queryCustomer(customerQueryReqDo);
        if (CollectionUtils.isEmpty(customerInfoResDos)){
            return null;
        }
        //参数转换
        List<CustomerInfoResDto> customerInfoResDtos = ConvertBeanUtil.convertToList(customerInfoResDos,CustomerInfoResDto.class);
        return customerInfoResDtos;
    }

    public Boolean insertCustomer(CustomerInfoReqDto customerInfoReqDto) {
        if (customerInfoReqDto == null){
            return false;
        }
        //参数转换
        CustomerInfoReqDo customerInfoReqDo = ConvertBeanUtil.convertToBean(customerInfoReqDto,CustomerInfoReqDo.class);
        //添加客户id
        Long id = CommonUtil.randomNumber();
        while (customerDao.isCustomerIdExit(id)){
            id = CommonUtil.randomNumber();
        }
        customerInfoReqDo.setCustomerId(id);

        customerDao.insertCustomer(customerInfoReqDo);
        return Boolean.TRUE;
    }

    public Boolean updateCustomer(CustomerInfoReqDto customerInfoReqDto) {
        if (customerInfoReqDto == null){
            return false;
        }
        //参数转换
        CustomerInfoReqDo customerInfoReqDo = ConvertBeanUtil.convertToBean(customerInfoReqDto,CustomerInfoReqDo.class);
        customerDao.updateCustomer(customerInfoReqDo);
        return Boolean.TRUE;
    }

    public Boolean deleteCustomer(Long customerId) {
        Preconditions.checkArgument(customerId != null,"顾客id不能为空");
        customerDao.deleteCustomer(customerId);
        return Boolean.TRUE;
    }

    public int count(CustomerQueryReqDto customerQueryReqDto) {
        CustomerQueryReqDo customerQueryReqDo = ConvertBeanUtil.convertToBean(customerQueryReqDto, CustomerQueryReqDo.class);
        return customerDao.count(customerQueryReqDo);
    }
}
