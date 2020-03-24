package com.wyp.controller;

import com.wyp.common.Result;
import com.wyp.common.domain.LoginInfo;
import com.wyp.common.enums.MemberEnum;
import com.wyp.common.enums.PowerEnum;
import com.wyp.controller.vo.req.RegisterReq;
import com.wyp.service.RegisterService;
import com.wyp.service.dto.req.RegisterReqDto;
import com.wyp.service.dto.req.StaffReqDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 注册
 */
@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 注册页面
     * @return
     */
    @RequestMapping
    public String pageRegister(){
        return "register";
    }
    /**
     * 公司注册
     * @param registerReq
     * @return
     */
    @ResponseBody
    @PostMapping("registerAdmin")
    public Result<Boolean> registerAdmin(@RequestBody RegisterReq registerReq){
        if (registerReq == null){
            return new Result(false,"注册失败");
        }
        //判断账号是否存在
        if (registerService.isAccountExist(registerReq.getAccount())){
            return new Result(false,"账号已存在，注册失败");
        }
        //判断公司名称是否存在
        if (registerService.isCompanyNameExist(registerReq.getCompanyName())){
            return new Result(false,"公司名称已存在，注册失败");
        }
        registerReq.setMember(MemberEnum.ADMIN.getCode());
        registerReq.setStaffName(MemberEnum.ADMIN.getMessage());
        //参数转换
        RegisterReqDto registerReqDto = ConvertBeanUtil.convertToBean(registerReq,RegisterReqDto.class);
        if (registerService.registerCompany(registerReqDto)){
            return new Result(true,"注册成功");
        }else {
            return new Result(false,"注册失败");
        }
    }

    /**
     * 员工添加(只有公司管理员才有权限)
     * @param registerReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("registerStaff")
    public Result<Boolean> registerStaff(@RequestBody RegisterReq registerReq, HttpSession httpSession){
        //获取当前登录账号的信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())){
            return new Result(false,"权限不足,注册失败");
        }
        if (registerReq == null){
            return new Result(false,"注册失败");
        }
        //判断账号是否存在
        if (registerService.isAccountExist(registerReq.getAccount())){
            return new Result(false,"账号已存在，注册失败");
        }
        registerReq.setMember(MemberEnum.STAFF.getCode());
        registerReq.setCompanyId(loginInfo.getCompanyId());
        registerReq.setCompanyNature(loginInfo.getCompanyNature());
        registerReq.setCompanyName(loginInfo.getCompanyName());
        //参数转换
        RegisterReqDto registerReqDto = ConvertBeanUtil.convertToBean(registerReq,RegisterReqDto.class);
        StaffReqDto staffReqDto = new StaffReqDto();
        staffReqDto.setStaffName(registerReq.getStaffName());
        staffReqDto.setAccount(registerReq.getAccount());
        staffReqDto.setCompanyId(loginInfo.getCompanyId());
        staffReqDto.setInsertPower(PowerEnum.NOT_EXIST_POWER.getCode());
        staffReqDto.setDeletePower(PowerEnum.NOT_EXIST_POWER.getCode());
        staffReqDto.setUpdatePower(PowerEnum.NOT_EXIST_POWER.getCode());
        if (registerService.registerStaff(registerReqDto,staffReqDto)){
            return new Result(true,"注册成功");
        }else {
            return new Result(false,"注册失败");
        }
    }
}
