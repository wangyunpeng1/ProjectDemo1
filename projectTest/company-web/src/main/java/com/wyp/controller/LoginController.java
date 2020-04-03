package com.wyp.controller;

import com.wyp.common.Result;
import com.wyp.common.domain.LoginInfo;
import com.wyp.controller.vo.req.LoginReq;
import com.wyp.service.LoginService;
import com.wyp.service.RegisterService;
import com.wyp.service.dto.req.LoginReqDto;
import com.wyp.service.dto.res.RegisterResDto;
import com.wyp.utils.ConvertBeanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author: wyp
 * @date: 2020/1/14
 * @description: 登录页
 */
@Controller
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    /**
     * 登录页面
     * @return
     */
    @RequestMapping
    public String pageLogin(){
        return "login";
    }

    /**
     * 登录
     * @param loginReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("login")
    public Result<Boolean> login(@RequestBody LoginReq loginReq, HttpSession httpSession){
        if (loginReq == null){
            return new Result(false,"登录失败");
        }
        //参数转换
        LoginReqDto loginReqDto = ConvertBeanUtil.convertToBean(loginReq,LoginReqDto.class);
        if (loginService.login(loginReqDto)){
            //登录成功，查询登陆者信息
            RegisterResDto registerRes = registerService.infoByAccount(loginReqDto.getAccount());
            //参数转化
            LoginInfo loginInfo = ConvertBeanUtil.convertToBean(registerRes,LoginInfo.class);
            if (registerRes == null){
                return new Result(false,"系统错误，获取登录信息失败");
            }
            //信息放入session
            httpSession.setAttribute("loginInfo",loginInfo);
            return new Result(true,"登录成功");
        }else {
            return new Result(false,"账号密码错误");
        }
    }
}
