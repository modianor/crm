package com.briup.crm.web.controller;

import javax.servlet.http.HttpSession;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.briup.crm.bean.CstService;
import com.briup.crm.bean.SysUser;
import com.briup.crm.service.ServeService;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/service")
public class ServiceController {

    @Autowired
    ServeService serveService;

    //根据经理id查找service
    @ApiOperation(value = "根据当前页 用户名查找服务", notes = "查找服务", httpMethod = "GET")
    @RequestMapping(value = "/findServiceByUserName/{curPage}", method = RequestMethod.GET)
    public String findServiceByUserName(@PathVariable int curPage, HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("user");
        String userName = user.getUsrName();
        PageInfo<CstService> info = serveService.findServiceByUserName(curPage, 5, userName);
        session.setAttribute("serviceInfo", info);
        return "service/service";
    }

    //模糊查询
    @ApiOperation(value = "根据当前页 服务信息进行模糊查找服务", notes = "分页模糊查询服务", httpMethod = "GET")
    @RequestMapping(value = "/findServiceByCustNameAndType/{curPage}", method = RequestMethod.GET)
    public String findServiceByCustNameAndType(@PathVariable int curPage, CstService service, HttpSession session) {
        SysUser user = (SysUser) session.getAttribute("user");
        String userName = user.getUsrName();
        service.setSvrDispose(userName);
        PageInfo<CstService> info = serveService.findServiceByCustNameAndType(curPage, 5, service);
        session.setAttribute("serviceInfo", info);
        return "service/service";
    }

    //新增和更新
    @ApiOperation(value = "保存或更新服务", notes = "保存或更新服务", httpMethod = "GET")
    @RequestMapping(value = "/saveOrUpdate", method = RequestMethod.GET)
    public String saveOrUpdate(CstService service, HttpSession session) {

        serveService.saveOrUpdate(service);

        return "forward:/service/findServiceByUserName/1";
    }

    //根据serId查找service
    @ApiOperation(value = "根据服务ID查找服务", notes = "查找服务", httpMethod = "GET")
    @RequestMapping(value = "/findServiceById/{svrId}", method = RequestMethod.GET)
    @ResponseBody
    public CstService findServiceById(@PathVariable Long svrId) {
        CstService service = serveService.findServiceById(svrId);
        return service;
    }

    //更新状态为已处理
    @ApiOperation(value = "根据服务ID更新服务状态", notes = "更新服务状态", httpMethod = "GET")
    @RequestMapping(value = "/updateStatusById/{svrId}", method = RequestMethod.GET)
    public String updateStatusById(@PathVariable Long svrId) {
        serveService.updateStatusById(svrId);
        return "forward:/service/findServiceByUserName/1";
    }

    //根据svrId查找service
    @ApiOperation(value = "根据服务ID查找服务", notes = "查找服务并跳转到服务细节页面", httpMethod = "GET")
    @RequestMapping(value = "/findServiceDetailById/{svrId}", method = RequestMethod.GET)
    public String findServiceById(@PathVariable Long svrId, HttpSession session) {
        CstService service = serveService.findServiceById(svrId);
        session.setAttribute("service", service);
        return "service/serviceDetail2";
    }

    //查询所有service
    @ApiOperation(value = "根据当前页查找服务", notes = "分页查询服务", httpMethod = "GET")
    @RequestMapping(value = "/findAllService/{curPage}", method = RequestMethod.GET)
    public String findAllService(@PathVariable int curPage, HttpSession session) {
        PageInfo<CstService> info = serveService.findAllService(curPage, 5);
        session.setAttribute("services", info);
        return "service/feedback";
    }

    //提交服务反馈结果
    @ApiOperation(value = "更新服务反馈", notes = "更新服务反馈", httpMethod = "GET")
    @RequestMapping(value = "/updateServiceWithFeedback", method = RequestMethod.GET)
    public String updateServiceWithFeedback(CstService service) {
        serveService.updateServiceWithFeedback(service);
        return "forward:/service/findAllService/1";
    }

    //根据type和status查询服务
    @ApiOperation(value = "根据当前页和服务类型查找服务", notes = "分页查找服务并跳转到服务反馈页面", httpMethod = "GET")
    @RequestMapping(value = "/findServiceByTypeAndStatus/{curPage}", method = RequestMethod.GET)
    public String findServiceByTypeAndStatus(@PathVariable int curPage, HttpSession session, CstService service) {
        PageInfo<CstService> info = serveService.findServiceByTypeAndStatus(curPage, 5, service);
        session.setAttribute("services", info);
        return "service/feedback";
    }

    //根据已反馈id查找服务信息
    @RequestMapping(value = "/findDetailById2/{svrId}", method = RequestMethod.GET)
    @ApiOperation(value = "根据服务ID查找服务细节", notes = "查找服务细节并跳转到服务细节页面", httpMethod = "GET")
    public String findDetailById2(@PathVariable long svrId, HttpSession session) {
        CstService service = serveService.findServiceById(svrId);
        session.setAttribute("service", service);
        return "service/serviceDetail";
    }
}
