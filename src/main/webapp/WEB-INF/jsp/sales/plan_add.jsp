<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="plan_add">
  <div class="foot">
 	<form>
			<div class="form-group">
				<label>商机详情:</label>
			</div>
		</form>
</div>

<div style="margin-bottom: 100px;margin-top: -30px;">
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
		<form>
			<div class="form-group">
				<label>开发计划:</label>
				<input type="textarea" name="plan" style="height:100px" class="form-control">
			</div>
			<div class="form-group" style="margin-left:500px;">
			<input class="btn btn-default tijiao" type="button" value="提交">
			<input class="btn btn-default back" type="button" value="返回">
			</div>
		</form>
	</div>
</div>
<style>
#plan_add{
	width: 1140px;
	overflow: hidden;
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
		$('.tijiao').on('click',function(){
			var plan = $("input[name=plan]").val();
			var data={
					plaTodo:plan	
			};
			var url="plan/addPlan"
		 $('.layui-body').load(url,data);  
		})
		
		$('.back').on('click',function(){
			$('.layui-body').load('plan/findPlansByUserId/1');  
		})
	})
</script>