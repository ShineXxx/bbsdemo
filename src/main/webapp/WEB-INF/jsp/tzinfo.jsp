<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/16
  Time: 22:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>帖子信息</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="/dist/css/bootstrap-switch.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#update").click(function () {
                $("#myModal").modal({backdrop: "static"});
            });
        });

        function addex(extra) {
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/addextz",//url
                data: {extra: extra, tzid:${tz.tzid}},
                success: function (result) {
                    alert(result + "success");
                },
                error: function () {
                    alert("error");
                }
            });
        }

        function deltz() {
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/deltz",//url
                data: {tzid:${tz.tzid}},
                success: function (result) {
                    alert(result + "success");
                },
                error: function () {
                    alert("error");
                }
            });
        }

        function discuss() {
            var text = $("#context").val();
            var t = new Date();
            var comsize = parseInt($("#comsize").html()) + 1;
            var time = t.getDate() + " " + t.getHours() + ":"
                + t.getMinutes() + ":" + t.getSeconds();
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/discuss",//url
                data: {context: $("#context").val(), tzid:${tz.tzid}},
                success: function (result) {
                    alert(result+"success");
                    $("<p></p>").append(time).append($("#name").html()).appendTo("#comments");
                    $("<p></p>").append(text).appendTo("#comments");
                    $("#comsize").html(comsize);
                    $("#context").val("");
                },
                error: function () {
                    alert("error");
                }
            });
        }

        function updatetz(tzid) {
            var formData = new FormData();
            formData.append('tzid', tzid);
            formData.append('file', $("#imgs")[0].files[0]);
            formData.append('title', $("#modaltitle").val());
            formData.append('text', $("#modaltext").val());
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/updatetz",//url
                data: formData,
                processData: false,
                contentType: false,
                async: false,
                success: function (result) {
                    alert(result);
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
<div id="name" hidden="hidden">${userinfo.name}</div>
<c:if test="${userinfo.userid==tz.user.userid||userinfo.status==2}">
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title"></h3>
            </div>
            <div class="panel-body">
                <button type="button" class="btn btn-link" id="update">修改</button>
                <button type="button" class="btn btn-link" onclick="deltz()">删除</button>
            </div>
        </div>
        <div class="page-header">
            <h1>标题
                <small>title</small>
            </h1>
            <p class="bg-primary">${tz.title}</p>
        </div>
    </div>
</c:if>
<div class="container">
    <p class="text-success">作者:<a
            href="/userinfo?userid=${tz.user.userid}">${tz.user.name}</a>+发布时间:${tz.time}
    </p>
    评论数量:<p class="text-danger" id="comsize">${tz.commentList.size()}</p>
    <c:if test="${userinfo.status==2}">
        <button type="button" class="btn btn-success" onclick="addex(1)">设为精品贴</button>
        <button type="button" class="btn btn-warning" onclick="addex(0)">从精品贴移除</button>
    </c:if>
</div>
<div class="container">
    <div class="page-header">
        <h1>内容
            <small>context</small>
        </h1>
    </div>
    <p class="text-muted">${tz.text}</p>
</div>
<div class="container" id="comments">
    <div class="page-header">
        <h1>评论
            <small>comment</small>
        </h1>
    </div>
    <c:forEach items="${tz.commentList}" var="comment">
        <p class="text-danger">${comment.time}+${comment.user.name}</p>
        <p class="text-muted">${comment.text}</p>
    </c:forEach>
</div>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-body panel panel-info">
            发表评论
        </div>
        <div class="panel-footer">
            <textarea class="form-control" id="context" rows="4"></textarea>
        </div>
    </div>
    <button type="button" class="btn btn-success" onclick="discuss()">发表</button>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改帖子</h4>
            </div>
            <div class="modal-body">
                <div>
                    <div class="page-header">
                        <h1>标题
                            <small>title</small>
                        </h1>
                    </div>
                    <div class="col-xs-12">
                        <textarea class="form-control" id="modaltitle" rows="3">${tz.title}</textarea>
                    </div>
                </div>
                <%--<div class="container">--%>
                <%--<p class="text-success">作者:<a--%>
                <%--href="/userinfo?userid=${tz.user.userid}">${tz.user.name}</a>+发布时间:${tz.time}--%>
                <%--</p>--%>
                <%--</div>--%>
                <div>
                    <div class="page-header">
                        <h1>内容
                            <small>context</small>
                        </h1>
                    </div>
                    <div class="col-xs-12">
                        <textarea class="form-control" id="modaltext" rows="10">${tz.text}</textarea>
                        <label for="imgs">File input</label>
                        <input type="file" id="imgs">
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="updatetz('${tz.tzid}')">确认修改</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
