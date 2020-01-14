<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<div id="linkman">
  <div>
  <form class="form-inline">
   <div class="form-group" style="padding-left:10px;">
				<label style="font-size: 18px;">联系人信息:</label>
			</div>
  <input class="btn btn-default" type="button" value="新增" id="add">

</form>
</div>

<div>
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>姓名</th>
		      <th>性别</th>
		      <th>职位</th>
		      <th>办公室电话</th>
		      <th>手机</th>
		      <th>备注</th>		 
			  <th>操作</th>
		    </tr> 
		  </thead>

		  <tbody>
		  	<c:forEach items="${linkmanInfo.list }" var="linkman">
			  	 <tr>
			     <td>${linkman.lkmName }</td>
			      <td>${linkman.lkmSex }</td>
			      <td>${linkman.lkmPostion }</td>
			      <td>${linkman.lkmTel }</td>
			      <td>${linkman.lkmMobile }</td>
			      <td>${linkman.lkmMemo }</td>
			      <td>
			      	<i value="${linkman.lkmId }" class="layui-icon layui-icon-edit editUser" title="编辑"></i>  
			      	<i value="${linkman.lkmId }" class="layui-icon layui-icon-delete deleteUser" title="删除"></i>   
			      </td>
			    </tr> 	  	
		  	</c:forEach>	    
         </tbody>
    </table>
</div>
<div class="fenye">
	<table class="footTable">
			<tr>
				<td colspan="5">共${linkmanInfo.total } 条记录 每页${linkmanInfo.size } 条 第${linkmanInfo.pageNum } 页 <a
					href="javascript:void(0)" onclick="pageSelect(1)">首页</a>
						<c:choose>	
							<c:when test="${linkmanInfo.hasPreviousPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${linkmanInfo.prePage})">上一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">上一页</font>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${linkmanInfo.hasNextPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${linkmanInfo.nextPage})">下一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">下一页</font>
							</c:otherwise>
						</c:choose>
					 <a href="javascript:void(0)" onclick="pageSelect(${linkmanInfo.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
</div>
<div class="back">
	 <button class="btn" style="background:#ccc">返回</button>
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
  <div class="modal-dialog">
    <div class="modal-content">
       <form>
					<div class="modal-body">
						<div class="form-group">
							<label>姓名:</label>
							<input type="text" name="lkmName"  class="form-control">
						</div>
						<div class="form-group">
							<label>性别:</label>
							<select name="lkmSex" id="" class="form-control">
								<option value="男">男</option>
								<option value="女">女</option>
							</select>
						</div>
						<div class="form-group">
							<label>办公室电话:</label>
							<input type="text" name="lkmTel" class="form-control">
						</div>
						<div class="form-group">
							<label>手机:</label>
							<input type="text" name="lkmMobile" class="form-control">
						</div>
						<div class="form-group">
							<label>备注:</label>
							<input type="text" name="lkmMemo" style="height:60px" class="form-control">
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
.pagination > .active > a, .pagination > .active > a:focus, .pagination > .active > a:hover, .pagination > .active > span, .pagination > .active > span:focus, .pagination > .active > span:hover {
    background-color: #ccc;
    border-color: #ccc;
}
.back{
		float: right;
	}
.pagination > li > a, .pagination > li > span {
    color: black;
}
#linkman{
	width: 1140px;
	overflow: hidden;
}
.fenye{
	margin-left: 400px;
}
	.form-inline{
		margin-top: 15px;
		margin-left: 10px;
		margin-bottom: 20px;
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
		margin-left: 1050px;
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
	//页码选择
	function pageSelect(curPage) {
	var url = "linkman/findLinkmanByCustId/" + ${custId} + "/" + curPage; 
	 $('.layui-body').load(url);
	}
	
	$(function(){
		var linkmanId;
		// 返回
		$('button:contains(返回)').on('click',function(){
			// $('.layui-body').load('customer/findAllCustomerByPage/1');
			
			$(".layui-nav-item dd:contains('客户信息查询')").trigger("click");
		})
		// 显示删除模态框
		$('.deleteUser').click(function(){
			linkmanId = $(this).attr("value");
			$('#deleteModal').show();
		})
		// 关闭删除模态框
		$('.close').click(function(){
			$('#deleteModal').hide();
			linkmanId = null;
		})
		// 关闭删除模态框
		$('.btn-default').click(function(){
			linkmanId = null;
			$('#deleteModal').hide();
		})
		// 确定删除
		$('.btn-primary').click(function(){
			var url = "linkman/deleteLinkmanById/" + linkmanId;
			$('#deleteModal').hide();
			$('.layui-body').load(url);
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			linkmanId = null;
			
			$('#toAdd').show();
			
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			linkmanId = null;
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
				$('#toAdd').hide();
				var lkmName = $("input[name=lkmName]").val();
				var lkmSex = $("select[name=lkmSex] option:selected").val();
				var lkmTel = $("input[name=lkmTel]").val();
				var lkmMobile = $("input[name=lkmMobile]").val();
				var lkmMemo = $("input[name=lkmMemo]").val();
				var data = {
					lkmId: linkmanId,
					lkmName: lkmName,
					lkmSex: lkmSex,
					lkmTel: lkmTel,
					lkmMobile: lkmMobile,
					lkmMemo: lkmMemo,
					
				}
				// console.log(data);
				var url = "linkman/saveOrUpdate"
				$(".layui-body").load(url,data);
		})	
		
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			linkmanId = $(this).attr("value");
			var url = "linkman/findLinkmanById/" + linkmanId;
			$.get(url,function(data) {
				 $("input[name=lkmName]").val(data.lkmName);
				 $("select[name=lkmSex]").val(data.lkmSex);
				 $("input[name=lkmTel]").val(data.lkmTel);
				 $("input[name=lkmMobile]").val(data.lkmMobile);
				 $("input[name=lkmMemo]").val(data.lkmMemo);
			})
			$('#toAdd').show();
			
		})
	})
</script>