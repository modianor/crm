<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登录</title>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Client Login Form,Login Forms,Sign up Forms,Registration Forms,News latter Forms,Elements" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!--webfonts-->
<link
	href='http://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>
<!--//webfonts-->
<script>
	var __links = document.querySelectorAll('a');
	function __linkClick(e) {
		parent.window.postMessage(this.href, '*');
	};
	for (var i = 0, l = __links.length; i < l; i++) {
		if (__links[i].getAttribute('data-t') == '_blank') {
			__links[i].addEventListener('click', __linkClick, false);
		}
	}
</script>
<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
<!-- <script>$(document).ready(function(c) {
	$('.alert-close').on('click', function(c){
		$('.message').fadeOut('slow', function(c){
	  		$('.message').remove();
		});
	});	  
});
</script> -->
<script type="text/javascript">
	var message = "${msg}";
	if(message) {
		alert(message);
	}
</script>
</head>
<body>
	<div class="shadow-forms">
		<div class="message warning">
			<div class="login-head">
				<!-- <div class="alert-close"> </div>  -->
				<h2>登录</h2>
			</div>
			<div class="sub-head"></div>
			<form id="form1" action="user/login" method="post">
				<input name="username" type="text" class="text" value="输入姓名"
					onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Username';}" onkeydown="keydown(event)">
				<input name="password" type="password" value="Password" onfocus="this.value = '';"
					onblur="if (this.value == '') {this.value = 'Password';}" onkeydown="keydown(event)">
				<div class="p-container">
					<!-- <label class="checkbox"><input type="checkbox" name="checkbox" checked><i></i>记住我？</label> -->
					<!-- <h6><a href="#">忘记密码 ?</a> </h6> -->
					<div class="clear"></div>
				</div>
				<div class="submit">
					<input type="button" onclick="myFunction()" value="登录">
				</div>

			</form>

			<script>
				$(document).ready(function() {
					$('.popup-with-zoom-anim').magnificPopup({
						type : 'inline',
						fixedContentPos : false,
						fixedBgPos : true,
						overflowY : 'auto',
						closeBtnInside : true,
						preloader : false,
						midClick : true,
						removalDelay : 300,
						mainClass : 'my-mfp-zoom-in'
					});

				});
			</script>
		</div>

		<div class="clear"></div>
	</div>

	<!--- footer -->
	<div class="footer">
		<p>Copyright 2011-2018 杰普软件科技有限公司 ICP 05024518 | 营业执照</p>

	</div>
</body>
<script type="text/javascript">
	function keydown(event){
		if(event.keyCode == 13){
			myFunction();
		}
	}
	function myFunction(){
		$('#form1').submit();
	}
</script>
</html>