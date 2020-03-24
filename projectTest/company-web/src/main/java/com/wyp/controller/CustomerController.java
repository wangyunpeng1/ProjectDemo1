package com.wyp.controller;

import com.google.common.base.Preconditions;
import com.wyp.common.Result;
import com.wyp.common.domain.LoginInfo;
import com.wyp.common.enums.MemberEnum;
import com.wyp.common.enums.PowerEnum;
import com.wyp.controller.vo.req.CustomerInfoReq;
import com.wyp.controller.vo.req.CustomerQueryReq;
import com.wyp.controller.vo.res.CustomerInfoRes;
import com.wyp.service.CompanyService;
import com.wyp.service.CustomerService;
import com.wyp.service.dto.req.CustomerInfoReqDto;
import com.wyp.service.dto.req.CustomerQueryReqDto;
import com.wyp.service.dto.res.CustomerInfoResDto;
import com.wyp.service.dto.res.StaffResDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author: wyp
 * @date: 2020/2/4
 * @description: 客户
 */
@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private CompanyService companyService;

    @RequestMapping()
    public String pageCustomer(){
        return "customer";
    }
    /**
     * 查询客户信息
     * @param httpSession
     * @return
     */
    @ResponseBody
    @GetMapping("query")
    public Result<List<CustomerInfoReq>> queryCustomer(CustomerQueryReq customerQueryReq, HttpServletRequest request, HttpSession httpSession){
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        customerQueryReq.setCompanyId(loginInfo.getCompanyId());
        CustomerQueryReqDto customerQueryReqDto = ConvertBeanUtil.convertToBean(customerQueryReq, CustomerQueryReqDto.class);
        //获取客户信息
        List<CustomerInfoResDto> customerInfoResDtos = customerService.queryCustomer(customerQueryReqDto, request);
        if (CollectionUtils.isEmpty(customerInfoResDtos)){
            return new Result(0,null,"暂时没有客户");
        }
        //参数转换
        List<CustomerInfoRes> customerInfoRes = ConvertBeanUtil.convertToList(customerInfoResDtos,CustomerInfoRes.class);
        //总数
        int count = customerService.count(customerQueryReqDto);
        return new Result(0,customerInfoRes,count,"查询成功");
    }

    /**
     * 添加客户
     * @param customerInfoReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("insert")
    public Result<Boolean> insertCustomer(@RequestBody CustomerInfoReq customerInfoReq, HttpSession httpSession){
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断是否有添加权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())){
            //通过账号获取员工权限信息
            StaffResDto staffResDto = companyService.staffInfo(loginInfo.getAccount());
            if (PowerEnum.NOT_EXIST_POWER.getCode().equals(staffResDto.getInsertPower())){
                return new Result(false,"无添加权限，添加失败");
            }
        }
        if (customerInfoReq == null){
            return new Result(false,"增加客户不能为空");
        }
        //参数转换
        CustomerInfoReqDto customerInfoReqDto = ConvertBeanUtil.convertToBean(customerInfoReq,CustomerInfoReqDto.class);
        customerInfoReq.setCompanyId(loginInfo.getCompanyId());

        if (customerService.insertCustomer(customerInfoReqDto)){
            return new Result(true,"添加成功");
        }
        return new Result(false,"添加失败");

    }

    /**
     * 修改客户
     * @param customerInfoReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("update")
    public Result<Boolean> updateCustomer(@RequestBody CustomerInfoReq customerInfoReq, HttpSession httpSession){
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断是否有修改权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())){
            //通过账号获取员工权限信息
            StaffResDto staffResDto = companyService.staffInfo(loginInfo.getAccount());
            if (PowerEnum.NOT_EXIST_POWER.getCode().equals(staffResDto.getUpdatePower())){
                return new Result(false,"无修改权限，修改失败");
            }
        }
        if (customerInfoReq == null){
            return new Result(false,"修改客户不能为空");
        }
        //参数转换
        CustomerInfoReqDto customerInfoReqDto = ConvertBeanUtil.convertToBean(customerInfoReq,CustomerInfoReqDto.class);
        if (customerService.updateCustomer(customerInfoReqDto)){
            return new Result(true,"修改成功");
        }
        return new Result(false,"填加失败");
    }

    /**
     * 删除客户
     * @param customerId
     * @param httpSession
     * @return
     */
    @ResponseBody
    @GetMapping("delete/{customerId}")
    public Result<Boolean> deleteCustomer(@PathVariable Long customerId, HttpSession httpSession){
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断是否有删除权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())){
            //通过账号获取员工权限信息
            StaffResDto staffResDto = companyService.staffInfo(loginInfo.getAccount());
            if (PowerEnum.NOT_EXIST_POWER.getCode().equals(staffResDto.getDeletePower())){
                return new Result(false,"无删除权限，删除失败");
            }
        }
        Preconditions.checkArgument(customerId != null,"客户id不能为空");
        if (customerService.deleteCustomer(customerId)){
            return new Result(true,"删除成功");
        }
        return new Result(false,"删除失败");
    }
}
