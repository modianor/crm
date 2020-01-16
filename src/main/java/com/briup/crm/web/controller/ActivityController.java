package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstActivity;
import com.briup.crm.service.ActivityService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/activity")
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    //根据custId查找activity(分页)
    @ApiOperation(value = "根据客户ID和当前页查询所有活动信息", notes = "查找分页信息", httpMethod = "GET")
    @RequestMapping(value = "findActivitiesByCustId/{custId}/{curPage}", method = RequestMethod.GET)
    public String findActivitiesByCustId(@ApiParam(value = "客户ID",example = "1") @PathVariable long custId, @ApiParam(value = "当前页",example = "1") @PathVariable int curPage, HttpSession session) {
        PageInfo<CstActivity> pageInfo = activityService.findActivitiesByCustId(custId, 5, curPage);
        session.setAttribute("activityInfo", pageInfo);
        session.setAttribute("custId", custId);
        return "customer/activities";
    }

    //添加或修改activity
    @ApiOperation(value = "添加或者更新活动", notes = "1个参数", httpMethod = "GET")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
    public String saveOrUpdate(@ApiParam(value = "活动实体类") CstActivity activity, HttpSession session) {
        Long custId = (long) session.getAttribute("custId");
        activity.setAtvCustId(custId);
        activityService.saveOrUpdate(activity);
        return "forward:/activity/findActivitiesByCustId/" + custId + "/1";
    }

    //根据atvId查找activity
    @ApiOperation(value = "根据活动ID查找活动", notes = "1个参数", httpMethod = "GET")
    @RequestMapping(value = "/findActivityById/{atvId}", method = RequestMethod.GET)
    @ResponseBody
    public CstActivity findActivityById(@ApiParam(value = "活动ID",example = "1") @PathVariable long atvId) {
        CstActivity activity = activityService.findActivityById(atvId);
        return activity;
    }

    @ApiOperation(value = "根据活动ID删除活动", notes = "1个参数", httpMethod = "GET")
    @RequestMapping(value = "/deleteActivityById/{atvId}", method = RequestMethod.GET)
    public String deleteActivityById(@ApiParam(value = "活动ID",example = "1") @PathVariable long atvId, HttpSession session) {
        activityService.deleteActivityById(atvId);
        Long custId = (long) session.getAttribute("custId");
        return "forward:/activity/findActivitiesByCustId/" + custId + "/1";

    }
}
