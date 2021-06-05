<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.print(request.getParameter("uname") + "<br>");
    String[] hobbies =  request.getParameterValues("hobbies");
    for (String s : hobbies) {
        out.print(s + "&nbsp;&nbsp;");
    }
%>
<br>

==========在EL中使用参数访问对象(param,paramValues)获取用户在表单中输入的数据=============<br>
姓名:<br> ${param.uname}<br> <!-- 等价于 out.print(request.getParameter("uname")); -->
爱好:<br> ${paramValues.hobbies[0]}  <!-- 等价于 String[] hobbies =  request.getParameterValues("hobbies");  out.print(hobbies[0]); -->
${paramValues.hobbies[1]} <!-- 同理 -->
${paramValues.hobbies[2]} <!-- 同理 -->
${paramValues.hobbies[3]} <!-- 同理 -->
</body>
</html>
