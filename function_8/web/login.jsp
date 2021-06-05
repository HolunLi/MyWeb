<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <link type="text/css" rel="stylesheet" href="css/mycss_1.css">
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript" src="js/functions.js"></script>
</head>
<body>
<form id="loginForm" action="welcome.jsp" method="post">
    <table border="0">
        <tr><th align="left">请您登录</th></tr>
        <tr>
            <td>账号：<input type="text" name="number" class="t1" ></td>
        </tr>
        <tr>
            <td>密码：<input type="password" name="pwd" class="t1" ></td>
        </tr>
        <tr>
            <td>
                <img id="verificationCodeImg" src="ImageCodeServlet" onclick="changeImg()">&nbsp;&nbsp;
                <!-- <a href="javascript:changeImg()">看不清，换一张</a>&nbsp; //使用链接刷新验证码 -->
                <input type="text" name="verificationCode" id="verificationCode" placeholder="请输入验证码"
                       style="height: 20px;width: 90px;border-radius: 5px;">
            </td>
        </tr>
        <tr>
            <td align="center">
                <br><input type="button" value="登录" onclick="ajaxFunction()" class="t2">
                <span id="display"></span>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
