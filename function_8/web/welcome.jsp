<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>欢迎页面</title>
</head>
<body>
您输入的验证码是：${param.verificationCode}<br>
servlet生产的验证码是：${sessionScope.verificationCode}<br><br>
您输入的账号是：${param.number}<br>
您输入的密码是：${param.pwd}
<hr/>
<p style="color: limegreen">恭喜您通过验证，登录成功!</p>
<a href="LogoutServlet">退出登录</a>
</body>
</html>
