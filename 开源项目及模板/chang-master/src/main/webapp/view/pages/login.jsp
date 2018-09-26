<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html lang="en" class="no-js">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>login</title>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="view/resources/css/normalize.css" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/css/normalize.css" />
    <link rel="stylesheet" type="text/css" href="view/resources/css/demo.css" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/css/demo.css" />
    <!--必要样式-->
    <link rel="stylesheet" type="text/css" href="view/resources/css/component.css" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/css/component.css" />
    <!--[if IE]>
    <script src="view/resources/js/html5.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/html5.js"></script>
    <![endif]-->
</head>
<body>
<div class="container demo-1">
    <div class="content">
        <div id="large-header" class="large-header">
            <canvas id="demo-canvas"></canvas>
            <div class="logo_box">
                <h3>账号登录</h3>
                <form id="form" action="/login" name="f" method="post">
                    <div class="input_outer">
                        <span class="u_user"></span>
                        <input name="logname" class="text" style="color: #FFFFFF !important" type="text" placeholder="请输入账户">
                    </div>
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="input_outer">
                        <span class="us_uer"></span>
                        <input name="logpass" class="text" style="color: #FFFFFF !important; position:absolute; z-index:100;"value="" type="password" placeholder="请输入密码">
                    </div>
                    <div class="mb2"><a class="act-but submit" onclick="submits()" href="javascript:;" style="color: #FFFFFF">登录</a></div>
                </form>
            </div>
        </div>
    </div>
</div><!-- /container -->
<script src="view/resources/js/TweenLite.min.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/TweenLite.min.js"></script>
<script src="view/resources/js/EasePack.min.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/EasePack.min.js"></script>
<script src="view/resources/js/rAF.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/rAF.js"></script>
<script src="view/resources/js/demo-1.js" tppabs="http://www.17sucai.com/preview/668095/2017-07-19/perfect/js/demo-1.js"></script>
<script>
    function submits() {
        document.getElementById("form").submit();
    }
</script>
</body>
</html>
