<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/15
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>主页</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="page-header col-md-6 col-md-offset-3">
    <h4>总记录数:${pageInfo.total}
        <small>当前页数:${pageInfo.pageNum} </small>
    </h4>
</div>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <table class="table table-striped">
                <thead>
                <th>标题</th>
                <th>作者</th>
                <th>回复</th>
                <th>发表时间</th>
                </thead>
                <tbody>
                <c:forEach items="${pageInfo.list}" var="tz">
                    <tr>
                        <td><a href="/tzinfo?tzid=${tz.tzid}">${tz.title}</a></td>
                        <td><a href="/userinfo?userid=${tz.user.userid}">${tz.user.name}</a></td>
                        <td>${tz.numofC}</td>
                        <td>${tz.time}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="col-md-4">
            <div class="panel-body" style="height: fit-content;width: fit-content;padding-left: 100px;">
                <a href="/jumppost"
                        <c:if test="${userinfo.status==-1||userinfo.status==1}">
                            class="btn btn-primary btn-lg disabled" role="button"</c:if>>
                    <h3>发布帖子
                        <c:if test="${userinfo.status!=2}">
                            <span class="label label-default">
                        <c:if test="${userinfo.status==-1}">
                            游客不可发帖
                        </c:if>
                         <c:if test="${userinfo.status==1}">
                             您已被禁止发帖
                         </c:if>
                        <c:if test="${userinfo.status==0}">
                            New
                        </c:if>
                            </span>
                        </c:if>
                    </h3>
                </a>
            </div>

            <table class="table table-hover">
                <thead>
                <th>标题</th>
                <th>回复</th>
                </thead>
                <tbody>
                <c:forEach items="${expageInfo.list}" var="tz">
                    <tr>
                        <td><a href="/tzinfo?tzid=${tz.tzid}">${tz.title}</a></td>
                        <td>${tz.numofC}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="container">
    <nav aria-label="Page navigation">
        <ul class="pagination">
            <li><a href="/pagenum?pn=${pageInfo.navigateFirstPage}">FirstPage</a></li>
            <li>
                <a href="/pagenum?pn=${pageInfo.prePage}" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach items="${pageInfo.navigatepageNums}" var="num">
                <li <c:if test="${pageInfo.pageNum==num}"> class="active"</c:if>>
                    <a href="/pagenum?pn=${num}">${num}</a>
                </li>
            </c:forEach>
            <li>
                <a href="/pagenum?pn=${pageInfo.nextPage}" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
            <li><a href="/pagenum?pn=${pageInfo.navigateLastPage}">LastPage</a></li>
        </ul>
    </nav>
</div>
</body>
</html>
