<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
测试!!!
<%
    request.setAttribute("name", "李豪伦");
    request.setAttribute("name", "holun");
    request.removeAttribute("name");
    session.setAttribute("sex", "男");
    session.setAttribute("sex", "女");
    session.removeAttribute("sex");
    session.setMaxInactiveInterval(5); //5秒不操作,销毁session对象
%>
</body>
</html>
