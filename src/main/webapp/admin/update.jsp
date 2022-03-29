<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String basePath = request.getScheme() + "://" +
			request.getServerName() + ":" + request.getServerPort() +
			request.getContextPath() + "/";
%>
<html>

	<head>
		<base href="<%=basePath%>">
		<meta charset="UTF-8">
		<title></title>
		<link rel="stylesheet" href="css/bootstrap.css" />
		<link rel="stylesheet" href="css/addBook.css" />
		<script type="text/javascript" src="js/jquery-3.3.1.js"></script>
		<script type="text/javascript" src="js/bootstrap.js"></script>
		<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	</head>

	<body>
		<div id="addAll">
			<div id="nav">
				<p>商品管理>更新商品</p>
			</div>
			<script type="text/javascript">
				function fileChange(){
					//$.ajaxUpload() 文件上传
					$.ajaxFileUpload({
						url:"prod/ajaxImg.action",
						//当前是否需要安全协议
						secureuri:false,
						//这个的名字，上传的<input type="file">的id值，截取这个控件对象
						fileElementId:'pimage',
						//返回数据类型
						dataType:"json",
						success:function (data){
							//先清空数据
							$("#imgDiv").empty();
							//后台打回json数据，返回src的路径
							//回显图片，提升用户体验
							//创建img标签对象
							let imgObj=$("<img>");
							//添加属性
							imgObj.attr("src","image_big/"+data.imgUrl);
							imgObj.attr("width","100px");
							imgObj.attr("height","100px");
							//将图片追加到图片的imgDiv里面
							$("#imgDiv").append(imgObj);
						}
					})
				}
			</script>
<script type="text/javascript">
	function myclose(ispage) {
		window.location="admin/product?flag=split&ispage="+ispage;
		//window.close();
	}
</script>
			<div id="table">
				<form action="prod/update.action" enctype="multipart/form-data" method="post" id="myform">
					<!--hidden:隐藏域提交-->
					<input type="hidden" value="${prod.pId}" name="pId">
					<input type="hidden" value="${prod.pImage}" name="pImage">
					<table>
						<tr>
							<td class="one">商品名称</td>
							<td><input type="text" name="pName" class="two" value="${prod.pName}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pnameerr"></span></td>
						</tr>
						<tr>
							<td class="one">商品介绍</td>
							<td><input type="text" name="pContent" class="two" value="${prod.pContent}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="pcontenterr"></span></td>
						</tr>
						<tr>
							<td class="one">定价</td>
							<td><input type="number" name="pPrice" class="two" value="${prod.pPrice}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="priceerr"></span></td>
						</tr>
						
						<tr>
							<td class="one">图片介绍</td>
							<td> <br><div id="imgDiv" style="display:block; width: 40px; height: 50px;"><img src="image_big/${prod.pImage}" width="100px" height="100px" ></div><br><br><br><br>
								<input type="file" id="pimage" name="pimage" onchange="fileChange()">
								<span id="imgName"></span><br>
							</td>
						</tr>
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>
						
						<tr>
							<td class="one">总数量</td>
							<td><input type="number" name="pNumber" class="two" value="${prod.pNumber}"></td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span id="numerr"></span></td>
						</tr>
						
						
						<tr>
							<td class="one">类别</td>
							<td>
								<select name="typeId">
									<c:forEach items="${typeList}" var="type">
										<option value="${type.typeId}"
												<c:if test="${type.typeId==prod.typeId}">
													selected="selected"
												</c:if>
										>${type.typeName}</option>

									</c:forEach>
								</select>
							</td>
						</tr>
						<!--错误提示-->
						<tr class="three">
							<td class="four"></td>
							<td><span></span></td>
						</tr>

						<tr>
							<td>
								<input type="submit" value="提交" class="btn btn-success">
							</td>
							<td>
								<input type="reset" value="取消" class="btn btn-default" onclick="myclose(1)">
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

	</body>

</html>