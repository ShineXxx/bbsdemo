<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/15
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>发布帖子</title>
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function login() {
            var formData = new FormData();
            formData.append('file', $("#imgs")[0].files[0]);
            formData.append('title', $("#title").val());
            formData.append('text', $("#text").val());
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/post",//url
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
            return false;
        }
    </script>
</head>
<body>
<jsp:include page="nav.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h3>发布
            <small>post</small>
        </h3>
    </div>
    <form id="form" onsubmit="return login()">
        <div class="input-group input-group-lg">

            <span class="input-group-addon" id="sizing-addon1">标题</span>
            <input type="text" class="form-control" placeholder="" aria-describedby="sizing-addon1" id="title">
        </div>
        <div class="page-header">
            <h3>内容<span class="label label-success">context</span></h3>
            <textarea class="form-control" rows="6" id="text"></textarea>
        </div>
        <div class="form-group">
            <label for="imgs">添加图片</label>
            <input type="file" name="file" id="imgs">
        </div>
        <div class="form-group">
            <button type="submit" class="btn btn-info">发布</button>
        </div>
    </form>
</div>
</body>
</html>
