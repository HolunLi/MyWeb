<%--
  Created by IntelliJ IDEA.
  User: Holun
  Date: 2021/4/18
  Time: 19:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加学生</title>
</head>
<body>
<form action="AddStudentServlet">
    <table>
        <caption>添加学生</caption>
        <tr>
            <td>学号：<input type="text" name="id"></td>
        </tr>
        <tr>
            <td>姓名：<input type="text" name="name"></td>
        </tr>
        <tr>
            <td>年龄：<input type="text" name="age"></td>
        </tr>
        <tr>
            <td>性别：<input type="text" name="sex"></td>
        </tr>
        <tr>
            <td>
                <%
                    String tip = (String) request.getAttribute("tip");
                    if ("success".equals(tip))
                        out.println("添加成功!!!");
                    else if ("fail".equals(tip))
                        out.println("添加失败!!!");
                %>
            </td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="重置">
                <input type="submit" value="提交">
                <a href="QueryStudentByPageServlet">返回</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
