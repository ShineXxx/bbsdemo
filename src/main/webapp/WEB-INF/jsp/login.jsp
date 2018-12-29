<%--
  Created by IntelliJ IDEA.
  User: VULCAN
  Date: 2018/12/15
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Login</title>
    <link href="/dist/css/signin.css" rel="stylesheet">
    <link href="/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="/dist/jquery-3.3.1.min.js"></script>
    <script src="/dist/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#adduser").click(function () {
                $("#myModal").modal({backdrop: "static"});
            });
        });
        function formadd() {
            var formData=new FormData();
            formData.append('file',$("#img")[0].files[0]);
            formData.append('name',$("#name").val());
            formData.append('password',$("#password").val());
            formData.append('gender',$('input[type=radio][name=gender]:checked').val());
            $.ajax({
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/signup",//url
                data: formData,
                processData : false,
                contentType : false,
                async:false,
                success: function (result) {
                    alert(result+"success");
                },
                error: function () {
                    alert("error");
                }
            });
        }
    </script>
</head>
<body class="text-center">
<div class="container">
    <form class="form-signin" action="/confirm" method="post">
        <img class="mb-4" src="/imges/bootstrap-social-logo.png" alt="" width="72" height="72">
        <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
        <h4 style="color: #c9302c">${msg}</h4>
        <label for="inputUser" class="sr-only">Username</label>
        <input name="name" type="text" id="inputUser" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input name="password" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <p class="mt-5 mb-3 text-muted">&copy; 2017-2018</p>
    </form>
    <a href="/Anonymouslogin" class="btn btn-default" role="button">匿名登陆</a>
    <button class="btn btn-default" id="adduser">注册</button>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">用户注册</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="form1">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">Name</label>
                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="name" placeholder="Name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="password" class="col-sm-2 control-label">Password</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="password" placeholder="Password">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="img">上传头像</label>
                        <div class="col-sm-offset-4">
                            <input type="file" id="img">
                        </div>
                        <p class="help-block">Let's Play Get Help.</p>
                    </div>
                    <div class="form-group">
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="0"> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="gender" value="1"> 女
                        </label>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="formadd()">Sign Up</button>
            </div>
        </div>
    </div>
</div>
</body>
</html>
