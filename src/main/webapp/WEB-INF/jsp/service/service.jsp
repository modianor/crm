<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 服务处理 -->
<div id="service">
  <div>
  <form class="form-inline">
  <div class="form-group">
    <input type="text" class="form-control" id="svrCustName" placeholder="请输入客户名称">
  </div>

  <div class="form-group">
	<select name=svrType id="" class="form-control">
		 <option value="">请选择服务类型</option>
        <option value="咨询">咨询</option>
        <option value="投诉">投诉</option>
        <option value="建议">建议</option>
	</select>
  </div>
  <input class="btn btn-default search" type="button" value="查询">
  <input class="btn btn-default" type="reset" value="重置">
  <input class="btn btn-default" type="button" value="新增" id="add">

</form>
</div>

<div>
	<table class="table table-hover">
		 <thead>
		    <tr style="background:#E8E8E8;">
		      <th>序号</th>
		      <th>客户名称</th>
		      <th>概要</th>
		      <th>服务类型</th>
		       <th>状态</th>		 
			  <th>操作</th>
		    </tr> 
		  </thead>

		  <tbody>
		 
		  	<c:forEach items="${serviceInfo.list }" var="service">
		  	 	<tr>
		      <td>${service.svrId }</td>
		      <td>${service.svrCustName }</td>
		      <td>${service.svrTitle }</td>
		      <td>${service.svrType }</td>
		      <td class="zhuangtai">${service.svrStatus }</td>
		      <c:choose>
		      	<c:when test="${service.svrStatus==\"已反馈\"}" >
		      	 <td>
		      	<i value="${service.svrId }" class="layui-icon layui-icon-face-smile" title="详情"></i>     
		     	 </td>
		      	</c:when>
		      			      	
		      	<c:when test="${service.svrStatus=='已处理'}" >
		      	 <td>
		      	<i value="${service.svrId }" class="layui-icon layui-icon-edit editUser" title="编辑"></i> 
      	       
		     	 </td>
		      	</c:when>
		      	
		      	<c:when test="${service.svrStatus=='处理中'}" >
		      	 <td>
		      	<i value="${service.svrId }" class="layui-icon layui-icon-edit editUser" title="编辑"></i> 
      	        <i value="${service.svrId }" class="layui-icon layui-icon-ok updata" title="更新"></i> 
		     	 </td>
		      	</c:when>
		      	<c:when test="${service.svrStatus=='未处理'}" >
		      	 <td>
		      	<i value="${service.svrId }" class="layui-icon layui-icon-edit editUser" title="编辑"></i> 
      	        <i value="${service.svrId }" class="layui-icon layui-icon-ok updata" title="更新"></i> 
		     	 </td>
		      	</c:when>
		      	
		      </c:choose>
		  	</tr>
		  	</c:forEach>
		    
		   
         </tbody>
    </table>
</div>
<div class="fenye">
	<table class="footTable">
			<tr>
				<td colspan="5">共${serviceInfo.total } 条记录 每页${serviceInfo.size } 条 第${serviceInfo.pageNum } 页 <a
					href="javascript:void(0)" onclick="pageSelect(1)">首页</a>
						<c:choose>	
							<c:when test="${serviceInfo.hasPreviousPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${serviceInfo.prePage})">上一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">上一页</font>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${serviceInfo.hasNextPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${serviceInfo.nextPage})">下一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">下一页</font>
							</c:otherwise>
						</c:choose>
					 <a href="javascript:void(0)" onclick="pageSelect(${serviceInfo.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
</div>
<!-- 新增模态框 -->
<div class="modal"  id="toAdd">
  <div class="modal-dialog" style="height:400px;overflow:auto;">
    <div class="modal-content">
       <form>
					<div class="modal-body">
						<div class="form-group">
							<label>服务类型:</label>
							<select name="svrType1" id="" class="form-control">
							<option value="">请选择服务类型</option>
					        <option value="咨询">咨询</option>
					        <option value="投诉">投诉</option>
					        <option value="建议">建议</option>
					        </select>
						</div>
						<div class="form-group">
							<label>概要:</label>
							<input type="text" name="svrTitle" class="form-control">
						</div>
						<div class="form-group">
							<label>客户名称:</label>
							<input type="text" name="svrCustName1" class="form-control">
						</div>
						<div class="form-group">
							<label>状态:</label>
							<select name="svrStatus" id="" class="form-control">
							<option value="">请选择状态</option>
					        <option value="处理中">处理中</option>
					        <option value="已处理">已处理</option>
					        <option value="未处理">未处理</option>
					        </select>
						</div>
						<div class="form-group">
							<label>处理人:</label>
							<input type="text" name="svrDispose" class="form-control">
						</div>
						<div class="form-group">
							<label>服务请求:</label>
							<input type="textarea" name="svrRequest" style="height:60px" class="form-control">
						</div>
						<div class="form-group">
							<label>服务处理过程:</label>
							<input type="textarea" name="svrHandle" style="height:60px" class="form-control">
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
#service{
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
	margin-left:400px;
}
	.form-inline{
		margin-top: 15px;
		margin-left: 10px;
		margin-bottom: 20px;
	}
	.search{
		margin-left: 50px;
	}
	#add{
		margin-left: 560px;
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
	var svrId;
	function pageSelect(curPage) {
	 	$('.layui-body').load('service/findServiceByUserName/'+curPage);
	}
	
	$(function(){
		//点击已反馈
		$('.layui-icon-face-smile').click(function(){
			svrId = $(this).attr("value");
			 $('.layui-body').load('service/findServiceDetailById/' + svrId);  
		})
		// 点击新增显示模态框
		$('#add').click(function(){
			svrId = null;
			$('#toAdd').show();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
			var svrType = $("select[name=svrType1] option:selected").val();
			var svrStatus = $("select[name=svrStatus] option:selected").val();
			var svrTitle = $("input[name=svrTitle]").val();
			var svrCustName = $("input[name=svrCustName1]").val();
			var svrRequest = $("input[name=svrRequest]").val();
			var svrDispose = $("input[name=svrDispose]").val();
			var svrHandle = $("input[name=svrHandle]").val();
			var data = {
					svrId: svrId,
					svrType: svrType,
					svrStatus: svrStatus,
					svrTitle: svrTitle,
					svrCustName: svrCustName,
					svrRequest: svrRequest,
					svrDispose: svrDispose,
					svrHandle: svrHandle
			}
			console.log(data);
			var url = "service/saveOrUpdate";
			$(".layui-body").load(url,data);
				$('#toAdd').hide();
				
		})	
		// 点击编辑显示模态框
		$('.editUser').click(function(){
			svrId = $(this).attr("value");
			url = "service/findServiceById/" + svrId;
			$.get(url,function(data){
			console.log(data);
			$("select[name=svrType1]").val(data.svrType);
			$("select[name=svrStatus]").val(data.svrStatus);
			$("input[name=svrTitle]").val(data.svrTitle);
		    $("input[name=svrCustName1]").val(data.svrCustName);
			$("input[name=svrRequest]").val(data.svrRequest);
			$("input[name=svrDispose]").val(data.svrDispose);
			$("input[name=svrHandle]").val(data.svrHandle);
			})
			$('#toAdd').show();
		})
		// 点击更新
		$('.updata').click(function(){
		//	var svrStatus = $(this).closest('tr').find('.zhuangtai').text();
			svrId = $(this).attr("value");
			console.log(svrId);
			url = "service/updateStatusById/" + svrId;
			$(".layui-body").load(url);
				
			
		})
		// 查询
		$('.search').click(function(){
			var svrCustName = $("input[id=svrCustName]").val();
			var svrType = $("select[name=svrType] option:selected").val();
			var data = {
					svrCustName: svrCustName,
					svrType: svrType
			}
			console.log(data);
			$('.layui-body').load('service/findServiceByCustNameAndType/1',data);
		})
		
	})
</script>