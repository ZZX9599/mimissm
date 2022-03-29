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
		<title>登录</title>
		
	</head>

	<body>
		<div id="login">
			<div id="top">
				<img src="images/cloud.jpg" /><span>用户登录</span>
			</div>
			<div id="bottom">
				<form  action="admin/login.action" method="post">
					<table border="0px" id="table">
						<tr>
							<td class="td1">用户名：</td>
							<td><input type="text" placeholder="username" class="td2" name="name"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="nameerr"></span></td>
						</tr>
						<tr>
							<td class="td1">密码：</td>
							<td><input type="password" placeholder="password" class="td2" name="pwd"></td>
						</tr>
						<tr>
							<td></td>
							<td><span id="pwderr" style="color: red"></span></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="submit" value="登录" class="td3">
								<a href="admin/regist.jsp"><input type="button" value="注册" class="td3	"></a>
							</td>
						</tr>
					</table>
				</form>
				<span style="color:red;">${errmsg}</span>
			</div>

		</div>
	</body>

</html>