<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 角色 -->
<div id="role">
  <div>
  <form class="form-inline">
  <!-- <div class="form-group">
	<select name="city" id="" class="form-control">
		 <option value="">请选择用户角色类型</option>
        <option value="0">管理员</option>
        <option value="1">高管</option>
        <option value="1">部门主管</option>
        <option value="2">客户经理</option>
	</select>
  </div>
  <input class="btn btn-default" type="submit" value="查询">
  <input class="btn btn-default" type="button" value="重置"> -->
  <input class="btn btn-default" type="button" value="新增" id="add">

</form>
</div>

<div>
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>序号</th>
		      <th>角色名</th>
		      <th>描述</th>
		      <th>状态</th>	 
			  <th>操作</th>
		    </tr> 
		  </thead>

		  <tbody>
		  	<c:forEach items="${roleInfo.list }" var="role">
		  	
		    <tr>
		      <td>${role.roleId }</td>
		      <td>${role.roleName }</td>
		      <td>${role.roleDesc }</td>
		      <td>${role.roleFlag }</td>
		      <td>
		      	<i value="${role.roleId }" class="layui-icon layui-icon-edit editUser"></i>  
		      	<i  value="${role.roleId }" class="layui-icon layui-icon-delete deleteUser"></i>   
		      </td>
		    </tr> 
		  	</c:forEach>
		   
         </tbody>
    </table>
</div>
<div class="fenye">
	<table class="footTable">
			<tr>
				<td colspan="5">共${roleInfo.total } 条记录 每页${roleInfo.size } 条 第${roleInfo.pageNum } 页 <a
					href="javascript:void(0)" onclick="pageSelect(1)">首页</a>
						<c:choose>	
							<c:when test="${roleInfo.hasPreviousPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${roleInfo.prePage})">上一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">上一页</font>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${roleInfo.hasNextPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${roleInfo.nextPage})">下一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">下一页</font>
							</c:otherwise>
						</c:choose>
					 <a href="javascript:void(0)" onclick="pageSelect(${roleInfo.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
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
							<label>角色名:</label>
							<input type="text" name="roleName" class="form-control">
						</div>
						<div class="form-group">
							<label>描述:</label>
							<input type="text" name="roleDesc" class="form-control">
						</div>
						<div class="form-group">
							<label>状态:</label>
							<select name="roleFlag" id="" class="form-control">
						        <option value="1">正常</option>
						        <option value="0">注销</option>						   
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
#role{
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
.fenye{
	margin-left: 400px;
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
		margin-left: 1020px;
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
	//分页
	function pageSelect(curPage) {
		 $('.layui-body').load('manager/toRole/'+curPage);
	}
	$(function(){
		var roleId;
		
		// 显示删除模态框
		$('.deleteUser').click(function(){
			roleId = $(this).attr("value");
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
			url = "manager/deleteRoleById/" + roleId;
			$('.layui-body').load(url);
			$('#deleteModal').hide();
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			roleId = null;
			$("input[name=roleName]").val(null);
			$("input[name=roleDesc]").val(null);
		    $("select[name=roleFlag]").val(null);
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
			var url = "manager/saveOrUpdate";
			var roleName = $("input[name=roleName]").val();
			var roleDesc = $("input[name=roleDesc]").val();
			var roleFlag = $("select[name=roleFlag] option:selected").val();
			var data = {
					roleId: roleId,					
					roleName: roleName,
					roleDesc: roleDesc,
					roleFlag: roleFlag,
			}
			$(".layui-body").load(url,data);
				$('#toAdd').hide();
		})	
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			roleId = $(this).attr("value");
			url = "manager/findRoleById/" + roleId;
			$.get(url, function(data){
				$("input[name=roleName]").val(data.roleName);
				$("input[name=roleDesc]").val(data.roleDesc);
			    $("select[name=roleFlag]").val(data.roleFlag);
			})
			$('#toAdd').show();
		})
	})
</script>