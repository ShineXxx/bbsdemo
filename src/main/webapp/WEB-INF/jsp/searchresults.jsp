<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/18
  Time: 15:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索结果</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<table class="table table-hover">
    <thead>
    <c:choose>
        <c:when test="${userspageinfo!=null}"><th>作者</th><th>性别</th></c:when>
        <c:otherwise><th>标题</th><th>id</th></c:otherwise>
    </c:choose>
    </thead>
    <tbody>
    <c:if test="${pageinfo!=null}">
        <c:forEach items="${pageinfo.list}" var="tz">
            <tr>
                <td><a href="/tzinfo?tzid=${tz.tzid}">${tz.title}</a></td>
                <td>${tz.tzid}</td>
            </tr>
        </c:forEach>
    </c:if>
    <c:if test="${userspageinfo!=null}">
        <c:forEach items="${userspageinfo.list}" var="user">
            <tr>
                <td><a href="/userinfo?userid=${user.userid}">${user.name}</a></td>
                <td>
                    <c:choose>
                        <c:when test="${user.gender==0}">男</c:when>
                        <c:otherwise>女</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
</body>
</html>
