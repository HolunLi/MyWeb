<%@ page import="student.entity.Student" %><%--
  Created by IntelliJ IDEA.
  User: Holun
  Date: 2021/4/18
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息展示</title>
</head>
<body>
<%
    Student student = (Student) request.getAttribute("aStudentInfo");
%>
<form action="ModifyStudentServlet">
    <table>
        <tr>
            <td>学号：<input type="text" name="id" value="<%=student.getId()%>" ></td> <!--学号不可修改-->
        </tr>
        <tr>
            <td>姓名：<input type="text" name="name" value="<%=student.getName()%>"></td>
        </tr><tr>
        <td>年龄：<input type="text" name="age" value="<%=student.getAge()%>"></td>
    </tr>
        <tr>
            <td>性别：<input type="text" name="sex" value="<%=student.getSex()%>"></td>
        </tr>
        <tr>
            <td>
                <input type="reset" value="重置">
                <input type="submit" value="修改">
                <a href="QueryStudentByPageServlet">返回</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
