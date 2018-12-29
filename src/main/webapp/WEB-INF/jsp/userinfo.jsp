<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/16
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="/dist/jquery-3.3.1.min.js"></script>
<script src="/dist/js/bootstrap.min.js"></script>
<html>
<head>
    <title>用户主页</title>
    <script type="text/javascript">
        function del(name) {
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/restatus",//url
                data: {name: name },
                success: function (result) {
                    alert(result + "success");
                },
                error: function () {
                    alert("error");
                }
            });
        }

        function modify(name,status) {
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/restatus",//url
                data: {name: name,type:1,status:status },
                success: function (result) {
                    alert(result + "success");
                },
                error: function () {
                    alert("error");
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="container">
    <h3><a href="#">发布的帖子 <span class="badge">${count}</span></a></h3>
    <c:if test="${userinfo.status==2}">
        <button type="button" class="btn btn-warning" onclick="modify('${user.name}',1)">封杀</button>
        <button type="button" class="btn btn-info" onclick="modify('${user.name}',0)">解除封杀</button>
        <%--<h3><a href="#">回复的帖子<span class="badge">42</span></a></h3>--%>
        <button type="button" class="btn btn-danger" onclick="del('${user.name}')">删除</button>
    </c:if>
</div>
<div class="container">${user.name}<img src="${user.photo}" width="20px" height="20px" class="img-circle"></div>
<div class="container">
    <table class="table">
        <thead>
        <th>帖子名</th><th>发帖时间</th>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="tz">
            <tr><td><a href="/tzinfo?tzid=${tz.tzid}">${tz.title}</a></td><td>${tz.time}</td></tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li><a href="/userinfo?userid=${user.userid}&pn=${pageInfo.navigateFirstPage}">FirstPage</a></li>
            <li>
                <a href="/userinfo?userid=${user.userid}&pn=${pageInfo.prePage}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                <li <c:if test="${pageInfo.pageNum==num}"> class="active"</c:if>>
                    <a href="/userinfo?userid=${user.userid}&pn=${num}">${num}</a>
                </li>
            </c:forEach>
            <li>
                <a href="/userinfo?userid=${user.userid}&pn=${pageInfo.nextPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a href="/userinfo?userid=${user.userid}&pn=${pageInfo.navigateLastPage}">LastPage</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
