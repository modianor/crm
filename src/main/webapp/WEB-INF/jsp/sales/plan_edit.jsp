<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="plan_edit">
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
		      <td>${chance.chcSource }</td>
		      <td>${chance.chcRate }</td>
		      <td>${chance.chcDueTo }</td>
		      <td>${chance.chcAddr }</td>
		      <td>${chance.chcCreateBy }</td>
		      <td>${chance.chcDesc }</td>
		    </tr> 
         </tbody>
    </table>
</div>
 <div class="foot">
 	<form class="form-inline">
			<div class="form-group">
				<label>开发计划:</label>
			</div>
			 <input class="btn btn-default" type="button" value="新增" id="add" style="margin-left:900px;">
		</form>
</div>
<div class="kaifa" style="margin-bottom: 30px;margin-top: -30px;height:200px;overflow:auto;">
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>序号</th>
		      <th>计划内容</th>
		       <th>计划结果</th>
		         <th>操作</th>
		    </tr> 
		  </thead>
		  <tbody>
		  <c:forEach items="${chanceExtend.plans }" var="plan">
		    <tr>
		      <td>${plan.plaId }</td>
		      <td>${plan.plaTodo }</td>
		      <td>${plan.plaResult }</td>
		       <td>
		      	<i value="${plan.plaId }" class="layui-icon layui-icon-edit editUser"></i>  
		      	<i value="${plan.plaId }" class="layui-icon layui-icon-delete deleteUser"></i>   
		      </td>
		    </tr> 	
		    </c:forEach>	
		   
		    
         </tbody>
    </table>
</div>
	<div>
		<form>
			<div class="form-group" style="margin-left:500px;">
			<input class="btn btn-default tijiao" type="button" value="开发成功">
			<input class="btn btn-default back" type="button" value="返&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;回">
			</div>
		</form>
	</div>

	<!-- 删除模态框 -->
<div class="modal" id="deleteModal">
  <div class="modal-dialog"  style="width:350px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">是否确定删除</h4>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default " data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary">确定</button>
      </div>
    </div>
  </div>
</div>
<!-- 新增模态框 -->
<div class="modal"  id="toAdd">
  <div class="modal-dialog" style="height:400px;overflow:auto;">
    <div class="modal-content">
       <form>
					<div class="modal-body">
						<div class="form-group">
							<label>计划内容:</label>
							<input type="textarea" name="plaTodo" style="height:60px" class="form-control">
						</div>
						<div class="form-group">
							<label>计划结果:</label>
							<select name="plaResult" id="" class="form-control">
								<option value="">请选择计划结果</option>
						        <option value="进行中">进行中</option>
						        <option value="成功">成功</option>
						        <option value="失败">失败</option>
							</select>
						</div>
						
					</div>
				</form>
				<div class="modal-footer">
				    <button class="btn" style="background:#ccc">取消</button>
					<button class="btn" style="background:#ccc">保存</button>
				</div>
    </div>
  </div>
</div>
</div>
<style>
#plan_edit{
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
	var chcId;
	var plaId;
	$(function(){
		$('.back').on('click',function(){
			$('.layui-body').load('plan/findPlansByUserId/1');  
		})	
		// 开发成功
		$('.tijiao').on('click',function(){
			url = "plan/chanceSuccess";
			
			$(".layui-body").load(url);
		})
		// 显示删除模态框
		$('.deleteUser').click(function(){
			plaId = $(this).attr("value");
			$('#deleteModal').show();
		})
		// 关闭删除模态框
		$('.close').click(function(){
			$('#deleteModal').hide();
		})
		// 关闭删除模态框
		$('.btn-default').click(function(){
			$('#deleteModal').hide();
		})
		// 确定删除
		$('.btn-primary').click(function(){
			$('#deleteModal').hide();
			url = "plan/deletePlan/" + plaId;
			$(".layui-body").load(url);
			
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			plaId = null;
			$("input[name=plaTodo]").val(null);
			$("select[name=plaResult]").val(null);
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
				$('#toAdd').hide();
				var plaTodo = $("input[name=plaTodo]").val();
				var plaResult = $("select[name=plaResult] option:selected").val();
				var data={
						plaId: plaId,
						plaTodo: plaTodo,
						plaResult: plaResult
				};
				console.log(data);
				var url="plan/addOrUpdate";
			 $('.layui-body').load(url,data);   
				
		})	
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			plaId = $(this).attr("value");
			var url = "plan/findPlanById/" + plaId;
			$.get(url,function(data){
				$("input[name=plaTodo]").val(data.plaTodo);
				$("select[name=plaResult]").val(data.plaResult);
			})
			$('#toAdd').show();
		})
	})
</script>