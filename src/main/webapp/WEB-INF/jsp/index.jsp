<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>客户关系管理</title>
<script src="http://cdn.highcharts.com.cn/highcharts/highcharts.js"></script>

  <link rel="stylesheet" href="layui/css/layui.css">
  <!-- <link rel="stylesheet" href="css/bootstrap-3.3.7/css/bootstrap.min.css"> -->
  <link rel="stylesheet" href="css/bootstrapSwitch/css/bootstrap3/bootstrap-switch.min.css">
   <script src="layui/js/jquery-3.3.1.min.js"></script>
   <link rel="stylesheet" href="css/bootstrap-3.3.7/css/bootstrap.min.css">
  <!-- <script src="css/bootstrapSwitch/css/bootstrap-switch.min.js"></script> -->
   <script src="css/bootstrap-3.3.7/js/bootstrap.min.js"></script>
  <script src="css/bootstrapSwitch/js/bootstrap-switch.min.js"></script>
  <script src="layui/js/router.js"></script>
  <style src="css/index.css"></style>
  <style type="text/css">
    #inner p:first-child{
    margin-left: 300px;
    margin-top: 110px;
    font-size: 80px;
    color: white;
    text-shadow: black 0 0 10px;
  }
  #inner p:last-child{
    color: white;
    text-shadow: black 0 0 10px;
    margin-left: 550px;
     margin-top: 50px;
    font-size: 50px;
  }
  #inner{
    width: 1140px;
  overflow: hidden;
  }
.layui-footer{
  text-align: center;
} 
.layui-icon-chart-screen {
    position: relative;
    top: 15px;
    left: 5px;
    font-size: 30px;
    font-weight: bolder;
    color: #cde6c7;
}
.layui-body{
  padding: 10px;
  background-color: #F0F0F0;
  overflow: auto;
}
.layui-body>div{
  position: absolute;
  background-color: white;
  width: 100%;
  min-height: 550px; 
}
/* #main>div{
  width:100%;
} */

  </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
  <div class="layui-header">
     <i class="layui-icon layui-icon-chart-screen"></i>
     <div class="layui-logo" style="font-size:18px;font-weight:bolder;">crm管理系统</div>

    <!-- 头部区域（可配合layui已有的水平导航） -->
   <!--  <ul class="layui-nav layui-layout-left">
      <li class="layui-nav-item"><a href="">控制台</a></li>
      <li class="layui-nav-item"><a href="">商品管理</a></li>
      <li class="layui-nav-item"><a href="">用户</a></li>
      <li class="layui-nav-item">
        <a href="javascript:;">其它系统</a>
        <dl class="layui-nav-child">
          <dd><a href="">邮件管理</a></dd>
          <dd><a href="">消息管理</a></dd>
          <dd><a href="">授权管理</a></dd>
        </dl>
      </li>
    </ul> -->
    <ul class="layui-nav layui-layout-right">
      <li class="layui-nav-item">
        <a href="javascript:;">
          <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
            ${user.usrName }
        </a>
      
        <dl class="layui-nav-child">
          <dd><a href="">基本资料</a></dd>
          <dd><a href="">安全设置</a></dd>
        </dl>
      </li>
      <li class="layui-nav-item"><a href="user/logout">退出</a></li>
    </ul>
  </div>
  
  <div class="layui-side layui-bg-black">
    <div class="layui-side-scroll">
      <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
      <ul class="layui-nav layui-nav-tree"  lay-filter="test">
      <c:if test="${user.usrRoleId==4 || user.usrRoleId==3 }">
          <li class="layui-nav-item layui-nav-itemed">
            <a href="javascript:;">营销管理</a>
            <dl class="layui-nav-child">
            <c:if test="${user.usrRoleId==3 }">
                <dd><a href="javascript:;">销售商机管理</a></dd>
              </c:if>
               <c:if test="${user.usrRoleId==4 }">
                <dd><a href="javascript:;">商机开发计划</a></dd>
              </c:if> 
            </dl>
          </li>
        </c:if>
        <c:if test="${user.usrRoleId==4 }">
          <li class="layui-nav-item">
            <a href="javascript:;">客户管理</a>
            <dl class="layui-nav-child">
              <dd><a href="javascript:;">客户信息查询</a></dd>
            </dl>
          </li>
        </c:if>
        <c:if test="${user.usrRoleId==4 || user.usrRoleId==3 }">
        <li class="layui-nav-item">
          <a href="javascript:;">服务管理</a>
          <dl class="layui-nav-child">
           <c:if test="${user.usrRoleId==3}">
             <dd><a href="javascript:;">服务反馈</a></dd>
           </c:if>
            <c:if test="${user.usrRoleId==4 }">
            <dd><a href="javascript:;">服务处理</a></dd>
            </c:if>
          </dl>
        </li>
        </c:if>
        <c:if test="${user.usrRoleId==2 }">
       <li class="layui-nav-item">
          <a href="javascript:;">统计报表</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">客户贡献分析</a></dd>
            <dd><a href="javascript:;">客户构成分析</a></dd>
          </dl>
        </li>
        </c:if>
         <c:if test="${user.usrRoleId==1 }">
        <li class="layui-nav-item">
          <a href="javascript:;">系统设置</a>
          <dl class="layui-nav-child">
            <dd><a href="javascript:;">角色管理</a></dd>
            <dd><a href="javascript:;">用户管理</a></dd>
          </dl>
        </li>
        </c:if>
      </ul>
    </div>
  </div>
  
  <div class="layui-body" id="main">
    <!-- 内容主体区域 -->
     <div id="inner">
        <p>欢迎使用</p>
        <p>crm后台系统</p>
      </div>
  </div>
  
  <div class="layui-footer">
    <!-- 底部固定区域 -->
  Copyright 2011-2018 杰普软件科技有限公司 ICP 05024518 | 营业执照 
  </div>
</div>
<script src="layui/layui.js"></script>
<script>
//JavaScript代码区域
layui.use('element', function(){
  var element = layui.element;
  
});
</script>
</body>
</html>