package com.briup.crm.web.controller;

import com.briup.crm.bean.SalChance;
import com.briup.crm.service.SaleChanceService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/chance")
public class SaleChanceController {
    @Autowired
    private SaleChanceService chanceService;

    //查询（分页）
    @ApiOperation(value = "根据当前页查找销售商机", notes = "分页查询商机", httpMethod = "GET")
    @RequestMapping(value = "/findSalesChance/{curPage}", method = RequestMethod.GET)
    public String findSalesChance(@PathVariable int curPage, HttpSession session) {
        PageInfo<SalChance> pageInfo = chanceService.findSalChance(curPage, 5);
        session.setAttribute("chanceInfo", pageInfo);
        return "sales/sales";
    }


    @RequestMapping(value = "/findSaleChanceLike/{curPage}", method = RequestMethod.GET)
    @ApiOperation(value = "根据当前页模糊查找销售商机", notes = "模糊分页查询商机", httpMethod = "GET")
    public String findSaleChanceLike(@PathVariable int curPage, String chcCustName, String chcAddr, HttpSession session) {

        PageInfo<SalChance> pageInfo = chanceService.findSaleChanceLike(curPage, 5, chcCustName, chcAddr);
        session.setAttribute("chanceInfo", pageInfo);
        return "sales/sales";
    }

    //保存
    @ApiOperation(value = "保存或者", notes = "分页查询商机", httpMethod = "GET")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
    public String saveOrUpdate(SalChance chance, HttpSession session) {
        chanceService.saveOrUpdate(chance);
        Long chcId = (long) session.getAttribute("chcId");
        return "forward:/chance/findSalesChance/1";
    }

    //查询
    @ApiOperation(value = "根据商机ID查找商机", notes = "查询商机", httpMethod = "GET")
    @RequestMapping(value = "/findChanceById/{chcId}", method = RequestMethod.GET)
    @ResponseBody
    public SalChance findChanceById(@PathVariable long chcId, HttpSession session) {
        SalChance chance = chanceService.findChanceById(chcId);
        session.setAttribute("chcId", chcId);
        return chance;
    }

    //删除
    @RequestMapping(value = "/deleteChanceById/{chcId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据商机ID删除商机", notes = "删除商机", httpMethod = "GET")
    public String deleteChanceById(@PathVariable long chcId) {
        chanceService.deleteChanceById(chcId);
        return "forward:/chance/findSalesChance/" + chcId + "/1";
    }
}
