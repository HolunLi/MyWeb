<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <link rel="stylesheet" href="css/css1.css" type="text/css">
</head>
<body>
<div class="oneFixed">
    <form action="LoginServlet" method="post">
        <table border="0" width="300">
            <tr>
                <td>
                    用户名:<br><input type="text" name="uname" value="" />
                </td>
            </tr>
            <tr>
                <td>
                    密码:<br> <input type="password" name="upsw" value="" />
                </td>
            </tr>
            <tr>
                <td align="left">
                    <input type="reset" name="reset" value="重置" />
                    <input type="submit" name="submit" value="登录" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>