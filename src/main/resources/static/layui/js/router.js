$(function(){
    $('.layui-nav-child').on({
        click:function(){
            if($(this).text()=="商机开发计划"){
                $('.layui-body').load('plan/findPlansByUserId/1');

            }
            if($(this).text()=="销售商机管理"){
                $('.layui-body').load('chance/findSalesChance/1');

            }
            if($(this).text()=="客户信息查询"){
                $('.layui-body').load('customer/findAllCustomerByPage/1');

            }
            // if($(this).text()=="客户信息添加"){
            //   $('.layui-body').load('./pages/customer_add.html');

            // }
            if($(this).text()=="服务反馈"){
                $('.layui-body').load('service/findAllService/1');

            }
            if($(this).text()=="服务处理"){
                $('.layui-body').load('service/findServiceByUserName/1');

            }
            if($(this).text()=="客户贡献分析"){
                $('.layui-body').load('reportForms/toContribution');

            }
            if($(this).text()=="客户构成分析"){
                $('.layui-body').load('reportForms/toConstitute');

            }
            if($(this).text()=="角色管理"){
                $('.layui-body').load('manager/toRole/1');

            }
            if($(this).text()=="用户管理"){
                $('.layui-body').load('manager/toUser/1');

            }

        }
    },'dd');
});



