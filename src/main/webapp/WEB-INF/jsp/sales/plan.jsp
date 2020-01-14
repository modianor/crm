<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/1/6 0006
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>plan</title>
</head>
<body>
<!-- 客户开发计划 -->
<div id="plan">
    <div>
        <form class="form-inline">
            <!-- <div class="form-group">
              <input type="text" class="form-control" id="exampleInputEmail3" placeholder="请输入客户名称">
            </div> -->
            <!--  <div class="form-group">
               <input type="text" class="form-control" id="exampleInputPassword3" placeholder="请输入客户经理">
             </div> -->
            <div class="form-group">
                <select name="city" id="" class="form-control">
                    <option value="">请选择所属地区</option>
                    <option value="华中">华中</option>
                    <option value="华北">华北</option>
                    <option value="华南">华南</option>
                    <option value="华东">华东</option>
                </select>
            </div>
            <!-- <div class="form-group">
              <select name="lever" id="" class="form-control">
                  <option value="">请选择客户等级</option>
                  <option value="0">普通客户</option>
                  <option value="1">大客户</option>
                  <option value="2">重点开发客户</option>
                  <option value="3">合作伙伴</option>
                  <option value="4">战略合作伙伴</option>
              </select>
            </div> -->
            <input class="btn btn-default search" type="button" value="查询">
            <input class="btn btn-default" type="reset" value="重置">
        </form>
    </div>
    <div>
        <table class="table table-hover">
            <thead>

            <tr style="background:#E8E8E8;">
                <th>序号</th>
                <th>客户名称</th>
                <th>概要</th>
                <th>联系人</th>
                <th>联系电话</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${chanceInfo.list}" var="chance">
                <tr>
                    <td>${chance.chcId}</td>
                    <td>${chance.chcCustName}</td>
                    <td>${chance.chcDesc}</td>
                    <td>${chance.chcLinkman}</td>
                    <td>${chance.chcTel}</td>
                    <td>
                        <c:choose>
                            <c:when test="${chance.chcStatus == 1}">
                                待开发
                            </c:when>

                            <c:when test="${chance.chcStatus == 2}">
                                开发中
                            </c:when>

                            <c:when test="${chance.chcStatus == 3}">
                                开发成功
                            </c:when>

                        </c:choose>
                        
                    </td>

                    <c:choose>
                        <c:when test="${chance.chcStatus == 2}">
                            <td>
                                <i value="${chance.chcId}" class="layui-icon layui-icon-fonts-code"></i>
                            </td>
                        </c:when>
                        <c:when test="${chance.chcStatus == 3}">
                            <td>
                                <i value="${chance.chcId}" class="layui-icon layui-icon-face-smile"></i>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <td>
                                <i value="${chance.chcId}" class="layui-icon layui-icon-loading"></i>
                            </td>
                        </c:otherwise>


                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="fenye">
        <table class="footTable">
            <tr>
                <td colspan="5">共${chanceInfo.total } 条记录
                    每页${chanceInfo.size } 条 第${chanceInfo.pageNum } 页 <a
                            href="javascript:void(0)" onclick="pageSelect(1)">首页</a> <c:choose>
                        <c:when test="${chanceInfo.hasPreviousPage }">
                            <a href="javascript:void(0)"
                               onclick="pageSelect(${chanceInfo.prePage})">上一页</a>
                        </c:when>
                        <c:otherwise>
                            <font color="#ABA8AB">上一页</font>
                        </c:otherwise>
                    </c:choose> <c:choose>
                        <c:when test="${chanceInfo.hasNextPage }">
                            <a href="javascript:void(0)"
                               onclick="pageSelect(${chanceInfo.nextPage})">下一页</a>
                        </c:when>
                        <c:otherwise>
                            <font color="#ABA8AB">下一页</font>
                        </c:otherwise>
                    </c:choose> <a href="javascript:void(0)"
                                   onclick="pageSelect(${chanceInfo.lastPage })">尾页</a>
                </td>
            </tr>

        </table>
    </div>
</div>
<style>
    #plan {
        width: 1140px;
        overflow: hidden;
    }

    .pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
        background-color: #ccc;
        border-color: #ccc;
    }

    .pagination > li > a, .pagination > li > span {
        color: black;
    }

    .fenye {
        margin-left: 400px;
    }

    .form-inline {
        margin-top: 15px;
        margin-left: 10px;
        margin-bottom: 20px;
    }

    .search {
        margin-left: 50px;
    }

    .table th, td {
        text-align: center;
    }
</style>
<script type="text/javascript">

    function pageSelect(curPage) {
        var url = "chance/findSaleChanceByzDueTo/" + curPage;
        $('.layui-body').load(url);
    }

    $(function () {
        var chanceId = null;
        // 查询
        $('.search').click(function () {
            var region = $("select[name=city] option:selected").val();

            var url = "chance/findSaleChanceByUserNameAndRegion/1/";
            var data = {
                region: region
            }
            $('.layui-body').load(url, data);

        })
        $('.layui-icon-loading').click(function () {
            chanceId = $(this).attr("value");
            var url = "plan/findSaleChanceAndPlansByChanceIdAndAdd/" + chanceId ;
            $('.layui-body').load(url);
        })
        $('.layui-icon-fonts-code').click(function () {
            chanceId = $(this).attr("value");
            var url = "plan/findSaleChanceAndPlansByChanceIdAndEdit/" + chanceId;
            $('.layui-body').load(url);
        })
        $('.layui-icon-face-smile').click(function () {
            chanceId = $(this).attr("value");
            var url = "plan/findSaleChanceAndPlansByChanceId/" + chanceId;
            $('.layui-body').load(url);
        })
    })
</script>
</body>
</html>
