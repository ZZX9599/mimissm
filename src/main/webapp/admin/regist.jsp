<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" +
			request.getServerName() + ":" + request.getServerPort() +
			request.getContextPath() + "/";
%>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/login.css" />
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<title>用户注册</title>
		<script type="text/javascript">
			$(function (){
				$("#reg").click(function (){
					reg();
				})

				//监听键盘
				$(document).keydown(function(event){
					if(event.keyCode==13){
						reg();
					}
				});
			})

			function reg(){
				//先拿到账号密码
				let myName=$.trim($("#myName").val());
				let myPwd=$.trim($("#myPwd").val());
				$.ajax({
					url:"admin/reg.action",
					data:({
						"adminName":myName,
						"adminPwd":myPwd
					}),
					type:"post",
					dataType:"json",
					success:function (data){
						//打回的json数据
						//{"success":true}
						//{"success":false,"errMsg":errMsg}
						if(data.success){
							alert("用户注册成功！");
							//跳转到登录页
							window.location.href="admin/login.jsp";
						}else {
							//清空消息
							$("#myName").val("");
							$("#errMsg").text(data.errMsg);
						}
					}
				})
			}
		</script>
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="images/cloud.jpg"/><span>用户注册</span>
			</div>
			<div id="bottom">
				<form action="main.jsp" method="get">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td><input type="text" placeholder="Username" class="td2" name="myName" id="myName"></td>
						</tr>
						<tr>
							<td></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" placeholder="Password" class="td2" name="myPwd" id="myPwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="errMsg" style="color: red"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" value="注册" id="reg" class="td3">
								<input type="reset" value="取消" class="td3	">
							</td>
						</tr>
					</table>
				</form>
			</div>

		</div>
	</body>

</html>