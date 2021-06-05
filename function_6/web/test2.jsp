<%@ page import="entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    Student student = new Student("张三");
    session.setAttribute("name", student);
    session.removeAttribute("name");
%>
</body>
</html>
