<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<!-- 服务反馈 -->
<div id="feedback">
  <div>
  <form class="form-inline">

  <div class="form-group">
	<select name="svrType1" id="" class="form-control">
		 <option value="">请选择服务类型</option>
        <option value="咨询">咨询</option>
        <option value="投诉">投诉</option>
        <option value="建议">建议</option>
	</select>
  </div>
  <div class="form-group">
	<select name="svrStatus1" id="" class="form-control">
		 <option value="">请选择服务状态</option>
        <option value="处理中">处理中</option>
        <option value="已处理">已处理</option>
        <option value="已反馈">已反馈</option>
	</select>
  </div>
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
		      <th>服务类型</th>
		      <th>处理人</th>
		      <th>状态</th>		
			  <th>操作</th>
		    </tr> 
		  </thead>

		  <tbody>
		  	<c:forEach items="${services.list }" var="service">
		  		<tr>	    
		  		   <td>${service.svrId }</td>
		      	   <td>${service.svrCustName }</td>
		     	   <td>${service.svrTitle }</td>
		           <td>${service.svrType }</td>
		          <td>${service.svrDispose }</td>
		           <td class="zhuangtai">${service.svrStatus }</td>
		           <c:choose >
		           	  <c:when test="${service.svrStatus == '已反馈'}">
		           	     <td>
		      			 <i value="${service.svrId }" class="layui-icon layui-icon-face-smile" title="已反馈"></i>   
		     			 </td>
		           	  </c:when>
		           	  <c:otherwise>
		           	    <td>
		         		<i value="${service.svrId }" class="layui-icon layui-icon-circle-dot" title="点击进行服务反馈"></i>   
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
				<td colspan="5">共${services.total } 条记录 每页${services.size } 条 第${services.pageNum } 页 <a
					href="javascript:void(0)" onclick="pageSelect(1)">首页</a>
						<c:choose>	
							<c:when test="${services.hasPreviousPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${services.prePage})">上一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">上一页</font>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${services.hasNextPage }">
							<a href="javascript:void(0)" onclick="pageSelect(${services.nextPage})">下一页</a>
							</c:when>
							<c:otherwise>
							<font color="#ABA8AB">下一页</font>
							</c:otherwise>
						</c:choose>
					 <a href="javascript:void(0)" onclick="pageSelect(${services.lastPage })">尾页</a>
				</td>
			</tr>

		</table>
</div>
<!-- 提示模态框 -->
<div class="modal" id="tishi">
  <div class="modal-dialog"  style="width:350px;">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title">服务正在处理中,无法反馈</h4>
      </div>
      <div class="modal-footer">
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
							<label>服务反馈结果:</label>
							<input type="text" name="svrResult" class="form-control">
						</div>
						
						<div class="form-group">
							<label>客户满意度:</label>
							<select name="svrSatisfy" id="" class="form-control">
							<option value="一星">请选择客户满意度</option>
					        <option value="一星">一星</option>
					        <option value="二星">二星</option>
					        <option value="三星">三星</option>
					        <option value="四星">四星</option>
					        <option value="五星">五星</option>
					        </select>
						</div>
						<div class="form-group">
							<label>状态:</label>
							<input type="text" name="svrStatus" class="form-control" value="已反馈" disabled>
							<!-- <select name="city" id="" class="form-control">
							<option value="">请选择状态</option>
					        <option value="0">处理中</option>
					        <option value="1">已处理</option>
					        <option value="2">未处理</option>
					        </select> -->
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
#feedback{
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
	.search{
		margin-left: 50px;
	}
	#add{
		margin-left: 560px;
	}
	.table th,td{
		text-align: center;
	}
	#tishi{
		margin-top: 100px;
	}
	#toAdd{
		margin-top: 50px;
	}
</style>
<script type="text/javascript">
	var svrId;
	function pageSelect(curPage) {
 		$('.layui-body').load('service/findAllService/'+curPage);
	}
	$(function(){
		// 查询
		$('.search').click(function(){
			var svrStatus = $("select[name=svrStatus1] option:selected").val();
			var svrType = $("select[name=svrType1] option:selected").val();
			var data = {
					svrType: svrType,
					svrStatus: svrStatus
			};
			console.log(data);
			url = "service/findServiceByTypeAndStatus/1";
			$('.layui-body').load(url, data);
			
		})
		// 点击新增显示模态框
		$('.layui-icon-circle-dot').click(function(){
			var status = $(this).closest('tr').find('.zhuangtai').text();
			// var status = $('.zhuangtai').html();
			console.log(status)
			if(status=='已处理') {
				svrId = $(this).attr("value");
				$('#toAdd').show();
			} else if(status=='处理中'){
				$('#tishi').show();
			}
			
		})
		// 服务详细信息
		$('.layui-icon-face-smile').click(function(){
			svrId = $(this).attr("value");
			$('.layui-body').load('service/findDetailById2/' + svrId);
		})
		// 关闭提示模态框
		$('.close').click(function(){
			$('#tishi').hide();
		})
		$('.btn-primary').click(function(){
			$('#tishi').hide();
		})
		// 新增模态框关闭
		$('button:contains(取消)').on('click',function(){
			$('button[type=reset]').trigger('click');
				$('#toAdd').hide();
		})
		// 新增模态框保存
		$('button:contains(保存)').on('click',function(){
			var url = "service/updateServiceWithFeedback";
			var svrStatus = $("input[name=svrStatus]").val();
			var svrResult = $("input[name=svrResult]").val();
			var svrSatisfy = $("select[name=svrSatisfy] option:selected").val();
			var data = {
					svrId: svrId,
					svrStatus: svrStatus,
					svrResult: svrResult,
					svrSatisfy: svrSatisfy
			};
			console.log(data);
				$('#toAdd').hide();
			
			$('.layui-body').load(url, data);
		})	
	})
</script>