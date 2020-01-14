<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="serviceDetails2">
<div>服务详情:</div>
	<div class="serviceDetails2_content" style="">		
		<div class="serviceDetails2_content_top">
			<div class="serviceDetails2_content_top_left">
				<ul>
				    <li>客户名：${service.svrCustName }</li>
					<li>服务类型：${service.svrType }</li>
					<li>概要：${service.svrTitle}</li>
				</ul>
			</div>
		    <div class="serviceDetails2_content_top_right">
		    	<ul>
		    		<li>处理人：${service.svrDispose}</li>
		    		<li>服务状态：${service.svrStatus}</li>
					<li>客户满意度：${service.svrSatisfy}</li>
						
					<!-- <li></li> -->
				</ul>
		    </div>
		</div>
		<div class="serviceDetails2_content_bottom">
			<span>服务请求：${service.svrRequest}</span><br>
			<span>服务处理过程：${service.svrHandle}</span><br>
			<span>服务处理结果：${service.svrResult}</span>
		</div>
	</div>
	<input class="btn btn-default back" type="button" value="返回" id="add">
</div>
<style type="text/css">
.btn{
		float: left;
		margin-top: 430px;
		margin-left:1050px;
}
#serviceDetails2 div:first-child{
	font-size: 25px;
}
	#serviceDetails2{
	  width: 1140px;
	  overflow: hidden;
	  padding-left: 10px;
	  padding-top: 10px;
	  /*background-color: pink;*/
	  /*position: absolute;*/
	}
	#serviceDetails2>.serviceDetails2_content{
		width: 1000px;
		height: 400px;
		position: absolute;
		top: 65px;
		left: 70px;
		box-shadow: 10px 10px 10px #ccc;
	}
	.serviceDetails2_content_bottom{
		width: 1000px;
		height: 300px;
		/*line-height: 90px;*/
		font-size: 20px;
		padding-left: 50px;
		background-color: #F0F0F0;
	}
	.serviceDetails2_content_top{
		width: 1000px;
		height: 100px;
	}
	.serviceDetails2_content_top_left{
		background-color: #F0F0F0;
	}
	.serviceDetails2_content_top_left li{
		list-style: none;
		width: 400px;
		height: 50px;
		margin-left: 50px;
		margin-top: 10px;
		/*background-color: red;*/
		font-size: 20px;
		line-height: 50px;
	}
	.serviceDetails2_content_top_right li{
		list-style: none;
		width: 400px;
		height: 50px;
		margin-left: 50px;
		margin-top: 10px;
		/*background-color: #ccc;*/
		font-size: 20px;
		line-height: 50px;

	}
	.serviceDetails2_content_top_right{
		background-color: #F0F0F0;
	}
	.serviceDetails2_content_top_left,.serviceDetails2_content_top_right{
		float: left;
		width: 500px;
		height: 200px;
	}
	.serviceDetails2_content_bottom span{
		margin-bottom: 20px;
		display: inline-block;
	}
</style>
<script type="text/javascript">
	$(function(){		
		$('.back').on('click',function(){
		 $('.layui-body').load('service/findServiceByUserName/1');  
		})
	})
</script>