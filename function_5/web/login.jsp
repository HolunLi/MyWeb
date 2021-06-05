<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>登录页面</title>
    <style type="text/css">
        .oneFixed {
            border: 0px dashed #0099CC;
            background-color: white;
            margin-left: 650px;
            margin-top: 200px;
            height: 200px;
            width: 300px;
        }
    </style>
</head>
<body>
<div class="oneFixed">
    <form action="LoginCheckServlet" method="post">
        <table border="0" width="300">
            <tr>
                <td>
                    用户名:<br><input type="text" name="uname"  />
                </td>
            </tr>
            <tr>
                <td>
                    密码:<br> <input type="password" name="upwd"  />
                </td>
            </tr>
            <tr>
                <td align="left">
                    <input type="reset" name="reset" value="重置" />
                    <input type="submit" name="submit" value="登录" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${sessionScope.error != null}">
                        <p style="color: red">${sessionScope.error}</p>
                    </c:if>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
