<%@page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String basePath = request.getScheme() + "://" +
            request.getServerName() + ":" + request.getServerPort() +
            request.getContextPath() + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">

    <script type="text/javascript">
        if ("${msg}" != "") {
            alert("${msg}");
        }
    </script>

    <c:remove var="msg"></c:remove>

    <link rel="stylesheet" href="css/bootstrap.css"/>
    <link rel="stylesheet" href="css/bright.css"/>
    <link rel="stylesheet" href="css/addBook.css"/>
    <script type="text/javascript" src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript" src="js/bootstrap.js"></script>
    <title></title>
</head>
<script type="text/javascript">
    function allClick() {
        //取得全选按钮的状态
        let flag=$("#all").prop("checked");
        //将状态赋值给单元框
        //$("input[id='ck']")得到的是数组
        $("input[id='ck']").each(function (){
            this.checked=flag;
        })
    }

    function ckClick() {
        //得到分页的复选框的个数
        let orgin=$("input[id='ck']").length;
        //得到被选中的复选框个数
        let cheakedLength=$("input[id='ck']:checked").length;
        //进行对比，来确定全选框的状态
        if(orgin==cheakedLength){
            //$("#all").checked=true;
            $("#all").prop("checked",true);
        }else {
            $("#all").prop("checked",false);
        }
    }
</script>
<body>
<div id="brall">
    <div id="nav">
        <p>商品管理>商品列表</p>
    </div>
    <div id="condition" style="text-align: center">
        <form id="myform">
            商品名称：<input name="pname" id="pname">&nbsp;&nbsp;&nbsp;
            商品类型：<select name="typeid" id="typeid">
            <option value="-1">请选择</option>
            <c:forEach items="${typeList}" var="pt">
                <option value="${pt.typeId}">${pt.typeName}</option>
            </c:forEach>
        </select>&nbsp;&nbsp;&nbsp;
            价格：<input name="lprice" id="lprice">-<input name="hprice" id="hprice">
            <input type="button" value="查询" onclick="condition()">
        </form>
    </div>
    <br>
    <div id="table">

        <c:choose>
            <c:when test="${info.list.size()!=0}">
                <div id="top">
                    <input type="checkbox" id="all" onclick="allClick()" style="margin-left: 50px">&nbsp;&nbsp;全选
                    <a href="admin/addproduct.jsp">

                        <input type="button" class="btn btn-warning" id="btn1"
                               value="新增商品">
                    </a>
                    <input type="button" class="btn btn-warning" id="btn1"
                           value="批量删除" onclick="deleteBatch()">
                </div>
                <!--显示分页后的商品-->
                <div id="middle">
                    <table class="table table-bordered table-striped">
                        <tr>
                            <th></th>
                            <th>商品名</th>
                            <th>商品介绍</th>
                            <th>定价（元）</th>
                            <th>商品图片</th>
                            <th>商品数量</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${info.list}" var="p">
                            <tr>
                                <td valign="center" align="center"><input type="checkbox" name="ck" id="ck" value="${p.pId}" onclick="ckClick()"></td>
                                <td>${p.pName}</td>
                                <td>${p.pContent}</td>
                                <td>${p.pPrice}</td>
                                <td><img width="55px" height="45px"
                                         src="image_big/${p.pImage}"></td>
                                <td>${p.pNumber}</td>
                                    <%--<td><a href="admin/product?flag=delete&pid=${p.pId}" onclick="return confirm('确定删除吗？')">删除</a>--%>
                                    <%--&nbsp;&nbsp;&nbsp;<a href="admin/product?flag=one&pid=${p.pId}">修改</a></td>--%>
                                <td>
                                    <button type="button" class="btn btn-info "
                                            onclick="one(${p.pId},${info.pageNum})">编辑
                                    </button>
                                    <button type="button" class="btn btn-warning" id="mydel"
                                            onclick="del(${p.pId},${info.pageNum})">删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <!--分页栏-->
                    <div id="bottom">
                        <div>
                            <nav aria-label="..." style="text-align:center;">
                                <ul class="pagination">
                                    <li>
                                            <%--<a href="prod/split.action?page=${info.prePage}" aria-label="Previous">--%>
                                        <a href="javascript:ajaxsplit(${info.prePage})" aria-label="Previous">

                                            <span aria-hidden="true">«</span></a>
                                    </li>
                                    <c:forEach begin="1" end="${info.pages}" var="i">
                                        <c:if test="${info.pageNum==i}">
                                            <li>
                                                    <%--<a href="prod/split.action?page=${i}" style="background-color: grey">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})"
                                                   style="background-color: grey">${i}</a>
                                            </li>
                                        </c:if>
                                        <c:if test="${info.pageNum!=i}">
                                            <li>
                                                <%--<a href="prod/split.action?page=${i}">${i}</a>--%>
                                                <a href="javascript:ajaxsplit(${i})">${i}</a>
                                            </li>
                                        </c:if>
                                    </c:forEach>
                                    <li>
                                        <%--  <a href="prod/split.action?page=1" aria-label="Next">--%>
                                        <a href="javascript:ajaxsplit(${info.nextPage})" aria-label="Next">
                                            <span aria-hidden="true">»</span></a>
                                    </li>
                                    <li style=" margin-left:150px;color: #0e90d2;height: 35px; line-height: 35px;">总共&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pages}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        <c:if test="${info.pageNum!=0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">${info.pageNum}</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                        <c:if test="${info.pageNum==0}">
                                            当前&nbsp;&nbsp;&nbsp;<font
                                            style="color:orange;">1</font>&nbsp;&nbsp;&nbsp;页&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </c:if>
                                    </li>
                                </ul>
                            </nav>
                        </div>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <div>
                    <h2 style="width:1200px; text-align: center;color: orangered;margin-top: 100px">暂时没有符合条件的商品！</h2>
                </div>
            </c:otherwise>
        </c:choose>
    </div>
</div>
</body>

<script type="text/javascript">
    function mysubmit() {
        $("#myform").submit();
    }

    //批量删除
    function deleteBatch() {
        //获取被选中的复选框
        let cks=$("input[id='ck']:checked");
        if(cks.length!=0){
            let str="";
            let pid="";
            if(confirm("你确定要删除"+cks.length+"条商品吗？")){
                $.each(cks,function (){
                    pid=$(this).val();
                    if(pid!=null&&pid!=1){
                        str+=pid+",";
                    }
                });
                //发送ajax请求
                $.ajax({
                    url:"prod/deleteBatch.action",
                    data:({
                        "pids":str
                    }),
                    type:"post",
                    dataType: "text",
                    success:function (date){
                        alert(date);
                        $("#table").load("admin/product.jsp #table");
                    }
                })
            }
        }else {
            alert("请选择需要批量删除的商品！");
        }
    }

    //单个删除
    function del(pid,page) {

        if(pid!=1){
            //js的弹框方法：confirm("String")
            if(confirm("确认删除吗")) {
                //商品名
                let pName=$("#pname").val();
                //种类
                let typeId=$("#typeid").val();
                //最低价
                let lPrice=$("#lprice").val();
                //最高价
                let hPrice=$("#hprice").val();

                //向服务器提交请求完成删除
                $.ajax({
                    url:"prod/delete.action",
                    data:({
                        "pId":pid,
                        "page":page,
                        "pName":pName,
                        "typeId":typeId,
                        "lPrice":lPrice,
                        "hPrice":hPrice
                    }),
                    type: "post",
                    dataType:"text",
                    success:function (data){
                        alert(data);
                        $("#table").load("admin/product.jsp #table");
                    }
                })
            }
        }else {
            alert("此商品不可被删除")
        }
    }

    //取得当前商品的信息
    function one(pid,page) {
        //商品名
        let pName=$("#pname").val();
        //种类
        let typeId=$("#typeid").val();
        //最低价
        let lPrice=$("#lprice").val();
        //最高价
        let hPrice=$("#hprice").val();

        let str="?pid="+pid+"&page="+page+"&pName="+pName+"&typeId="+typeId+"&lPrice="+lPrice+"&hPrice="+hPrice
        if(pid!=1){
            window.location.href = "prod/getOne.action"+str;
        }else {
            alert("该商品不可被编辑");
        }
    }
</script>

<!--分页的AJAX实现-->
<script type="text/javascript">
    function ajaxsplit(page) {
        //商品名
        let pName=$("#pname").val();
        //种类
        let typeId=$("#typeid").val();
        //最低价
        let lPrice=$("#lprice").val();
        //最高价
        let hPrice=$("#hprice").val();
        //异步ajax分页请求
        $.ajax({
        url:"prod/splitCondition.action",
            data:({
                "page":page,
                "pName":pName,
                "typeId":typeId,
                "lPrice":lPrice,
                "hPrice":hPrice,
            }),
            type:"post",
            success:function () {
                //重新加载分页显示的组件table
                //location.href---->http://localhost:8080/admin/login.action
                //$("#id").load()  重新加载id覆盖的页面
                /* 这里不要写localhost端口这些 */
                $("#table").load("admin/product.jsp #table");
            }
        })
    };

    function condition(){
        //取出vo所需要的条件
        //商品名
        let pName=$("#pname").val();
        //种类
        let typeId=$("#typeid").val();
        //最低价
        let lPrice=$("#lprice").val();
        //最高价
        let hPrice=$("#hprice").val();
        $.ajax({
            url:"prod/condition.action",
            type:"post",
            data:({
                "pName":pName,
                "typeId":typeId,
                "lPrice":lPrice,
                "hPrice":hPrice,
            }),
            success:function (){
                //刷新数据
                $("#table").load("admin/product.jsp #table");
            }
        })
    }
</script>

</html>