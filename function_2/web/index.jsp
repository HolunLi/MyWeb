<%@ page import="student.entity.Student" %>
<%@ page import="student.entity.NowPage" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息列表</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>操作</th>
        </tr>
        <%
            NowPage nowPage = (NowPage) request.getAttribute("AllStudents");
            for (Student student : nowPage.getStudents()) {
        %>
            <tr>
                <td><a href="QueryStudentServlet?id=<%=student.getId()%>"><%=student.getId()%></a></td>
                <td><%=student.getName()%></td>
                <td><a href="DeleteStudentServlet?id=<%=student.getId()%>">删除</a></td>
            </tr>
        <%
            }
        %>
    </table>
    <%
        if (nowPage.getCurrentPage() == 0) {
    %>
    <a href="QueryStudentByPageServlet?currentPage=0">首页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getCurrentPage()+1%>">下一页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getTotalPages()-1%>">尾页</a>
    <%
        }
        else if (nowPage.getCurrentPage() == nowPage.getTotalPages()-1) {
    %>
    <a href="QueryStudentByPageServlet?currentPage=0">首页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getCurrentPage()-1%>">上一页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getTotalPages()-1%>">尾页</a>
    <%
        }
        else {
    %>
    <a href="addStudent.jsp">添加学生</a><br>
    <a href="QueryStudentByPageServlet?currentPage=0">首页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getCurrentPage()-1%>">上一页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getCurrentPage()+1%>">下一页</a> &nbsp;
    <a href="QueryStudentByPageServlet?currentPage=<%=nowPage.getTotalPages()-1%>">尾页</a>
    <%
        }
    %>
</body>
</html>
