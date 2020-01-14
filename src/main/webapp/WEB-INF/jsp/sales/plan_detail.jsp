<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<div id="plan_detail">
  <div class="foot">
 	<form>
			<div class="form-group">
				<label>商机详情:</label>
			</div>
		</form>
</div>

<div style="margin-bottom: 50px;margin-top: -30px;">
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		       <th>商机来源</th>
		      <th>成功几率</th>
		      <th>商机指派</th>		 
			  <th>所属地区</th>
			  <th>创建人</th>
			  <th>商机描述</th>
		    </tr> 
		  </thead>

		  <tbody>
		  
		    <tr>
		     <td>${chanceExtend.chcSource }</td>
		      <td>${chanceExtend.chcRate }</td>
		      <td>${chanceExtend.chcDueTo }</td>
		      <td>${chanceExtend.chcAddr }</td>
		      <td>${chanceExtend.chcCreateBy }</td>
		      <td>${chanceExtend.chcDesc }</td>
		    </tr> 
         </tbody>
    </table>
</div>
 <div class="foot">
 	<form>
			<div class="form-group">
				<label>开发计划:</label>
			</div>
		</form>
</div>
<div class="kaifa" style="margin-bottom: 50px;margin-top: -30px;height:200px;overflow:auto;">
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>序号</th>
		      <th>计划内容</th>
		       <th>计划结果</th>
		       
		    </tr> 
		  </thead>
		  <tbody>
		  <c:forEach items="${chanceExtend.plans }" var="plan">
		    <tr>
		      <td>${plan.plaId }</td>
		      <td>${plan.plaTodo }</td>
		      <td>${plan.plaResult }</td>
		    </tr> 	
		    </c:forEach>	   
         </tbody>
    </table>
</div>
	<div class="foot">
		<form>
			<div class="form-group">
			<input class="btn btn-default back" type="button" value="返回" id="add">
			</div>
		</form>
	</div>
</div>
<style>
#plan_detail{
	width: 1140px;
	overflow: hidden;
}
	.kaifa tr td:first-child,th:first-child{
		width: 100px;
	}
	.foot{
		/*margin-top: 150px;*/
		padding: 10px;
	}
	.foot label{
		font-size: 18px;
	}
	.form-inline{
		margin-top: 15px;
		margin-left: 10px;
		margin-bottom: 20px;
	}
	input[type=submit]{
		margin-left: 50px;
	}
	#add{
		margin-left:550px;
		width: 100px;
	}
	.table {
		margin-top: 10px;
	}
	.table th,td{
		text-align: center;
	}
	#deleteModal{
		margin-top: 100px;
	}
	#toAdd{
		margin-top: 50px;
	}
</style>
<script type="text/javascript">
	$(function(){		
		$('.back').on('click',function(){
		 $('.layui-body').load('plan/findPlansByUserId/1');  
		})
	})
</script>