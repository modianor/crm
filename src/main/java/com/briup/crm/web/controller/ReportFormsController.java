package com.briup.crm.web.controller;

import com.briup.crm.bean.Contribution;
import com.briup.crm.service.ConstituteService;
import com.briup.crm.service.ContributionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("reportForms")
public class ReportFormsController {
    @Autowired
    private ContributionService contributionService;
    @Autowired
    private ConstituteService conService;

    //跳转到贡献分析
    @ApiOperation(value = "跳转到贡献页面", notes = "跳转到贡献页面", httpMethod = "GET")
    @RequestMapping(value = "/toContribution", method = RequestMethod.GET)
    public String toContribution() {
        return "reportForms/contribution";
    }

    //用户贡献
    @ApiOperation(value = "查询用户贡献", notes = "查询用户贡献", httpMethod = "GET")
    @RequestMapping(value = "/getContribution", method = RequestMethod.GET)
    @ResponseBody
    public List<Contribution> getContribution() {
        List<Contribution> list = contributionService.findContribution();
        System.out.println(list);
        return list;
    }

    //根据region查询贡献
    @ApiOperation(value = "根据地区查找用户贡献", notes = "查询用户贡献", httpMethod = "GET")
    @RequestMapping(value = "/getContributionByRegion", method = RequestMethod.GET)
    @ResponseBody
    public List<Contribution> getContributionByRegion(String region) {
        Contribution con = contributionService.findContributionByRegion(region);
        List<Contribution> c = new ArrayList<>();
        c.add(con);
        return c;
    }

    //跳转用户构成分析页面
    @ApiOperation(value = "跳转到用户构成分析页面", notes = "跳转到用户构成分析页面", httpMethod = "GET")
    @RequestMapping(value = "/toConstitute", method = RequestMethod.GET)
    public String toConstitute() {
        return "reportForms/constitute";
    }

    //用户构成
    @ApiOperation(value = "根据条件查找用户构成", notes = "根据条件查找用户构成", httpMethod = "GET")
    @RequestMapping(value = "/getConstitute", method = RequestMethod.GET)
    @ResponseBody
    public List<Contribution> getConstitute(int condition) {
        List<Contribution> list = conService.findCustMarkup(condition);
        return list;
    }
}
