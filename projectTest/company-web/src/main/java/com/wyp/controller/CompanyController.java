package com.wyp.controller;

import com.wyp.common.Result;
import com.wyp.common.domain.LoginInfo;
import com.wyp.common.enums.MemberEnum;
import com.wyp.controller.vo.req.StaffInfoReq;
import com.wyp.controller.vo.res.StaffInfoRes;
import com.wyp.controller.vo.res.StatisticsRes;
import com.wyp.service.CompanyService;
import com.wyp.service.dto.res.StaffResDto;
import com.wyp.utils.ConvertBeanUtil;
import com.wyp.utils.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: wyp
 * @date: 2020/2/2
 * @description: 公司
 */
@Controller
@RequestMapping("company")
public class CompanyController {

    private String DATE_FORMAT = "yyy-MM-dd HH:mm:ss";

    @Autowired
    private CompanyService companyService;

    @RequestMapping()
    public String pageCompany() {
        return "company";
    }

    /**
     * 获取公司员工
     *
     * @param httpSession
     * @return
     */
    @ResponseBody
    @GetMapping("query")
    public Result<List<StaffInfoRes>> queryStaffInfo(HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //查询公司员工
        List<StaffResDto> staffResDtos = companyService.query(loginInfo.getCompanyId(), request);
        if (CollectionUtils.isEmpty(staffResDtos)) {
            return new Result(0, false, "公司目前没有员工");
        }
        //参数转换
        List<StaffInfoRes> staffInfoRes = ConvertBeanUtil.convertToList(staffResDtos, StaffInfoRes.class, new Converter<StaffResDto, StaffInfoRes>() {
            public void convert(StaffResDto from, StaffInfoRes to) {
                SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT);
                to.setCreateTime(f.format(from.getCreateTime()));
            }
        });
        //获取总数
        int count = companyService.count(loginInfo.getCompanyId());
        return new Result(0, staffInfoRes, count, "员工查询成功");
    }

    /**
     * 更改修改权限
     *
     * @param staffInfoReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("updatePower")
    public Result<Boolean> updatePower(@RequestBody StaffInfoReq staffInfoReq, HttpSession httpSession) {
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())) {
            return new Result(false, "权限不足，更改修改权限失败");
        }
        if (staffInfoReq == null) {
            return new Result(false, "员工为空，更改修改权限失败");
        }
        if (companyService.updatePower(staffInfoReq.getStaffId(), staffInfoReq.getUpdatePower())) {
            return new Result(true, "更改修改权限成功");
        }
        return new Result(false, "更改修改权限失败");
    }

    /**
     * 修改删除权限
     *
     * @param staffInfoReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("deletePower")
    public Result<Boolean> deletePower(@RequestBody StaffInfoReq staffInfoReq, HttpSession httpSession) {
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())) {
            return new Result(false, "权限不足，更改删除权限失败");
        }
        if (staffInfoReq == null) {
            return new Result(false, "员工为空，更改删除权限失败");
        }
        if (companyService.deletePower(staffInfoReq.getStaffId(), staffInfoReq.getDeletePower())) {
            return new Result(true, "更改删除权限成功");
        }
        return new Result(false, "更改删除权限失败");
    }

    /**
     * 修改添加权限
     *
     * @param staffInfoReq
     * @param httpSession
     * @return
     */
    @ResponseBody
    @PostMapping("insertPower")
    public Result<Boolean> insertPower(@RequestBody StaffInfoReq staffInfoReq, HttpSession httpSession) {
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        //判断权限
        if (MemberEnum.STAFF.getCode().equals(loginInfo.getMember())) {
            return new Result(false, "权限不足，更改删除权限失败");
        }
        if (staffInfoReq == null) {
            return new Result(false, "员工为空，更改添加权限失败");
        }
        if (companyService.insertPower(staffInfoReq.getStaffId(), staffInfoReq.getInsertPower())) {
            return new Result(true, "更改添加权限成功");
        }
        return new Result(false, "更改添加权限失败");
    }

    /**
     * 删除员工
     *
     * @param staffInfoReq
     * @return
     */
    @ResponseBody
    @PostMapping("deleteStaff")
    public Result<Boolean> deleteStaff(@RequestBody StaffInfoReq staffInfoReq) {
        if (staffInfoReq == null) {
            return new Result(false, "员工为空，更改添加权限失败");
        }
        companyService.deleteStaff(staffInfoReq.getAccount(), staffInfoReq.getStaffId());
        return new Result(true, "删除成功");
    }

    @ResponseBody
    @GetMapping("Statistics")
    public Result<StatisticsRes> statistics(HttpSession httpSession) {
        //获取登录者信息
        LoginInfo loginInfo = (LoginInfo) httpSession.getAttribute("loginInfo");
        return null;
    }
}
