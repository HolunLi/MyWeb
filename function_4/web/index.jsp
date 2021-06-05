<%@ page import="entity.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  ===============在JSP页面中使用java代码获取student对象中的内容=================
  <%
    String name = "haha";
    Student student = (Student) request.getAttribute("student");
    out.print("<br>" + student.getId() + "<br>");
    out.print(student.getName() + "<br>");
    out.print(student.getAge() + "<br>");
    out.print(student.getSex() + "<br>");
    out.print(student.getAddress().getHomeAddress() + "<br>");
    out.print(student.getAddress().getTempAddress() + "<br>");
  %>

  ===============在JSP页面中使用EL代替java代码获取student对象中的内容==================<br>
  ${requestScope.student.id}<br>  <!-- 可以改成:requestScope["student"]["id"] -->
  ${requestScope["student"]["id"]}<br>  <!-- 中括号[] 中必须要有双引号或者单引号 -->
  ${requestScope.student.name}<br> <!-- (1)requestScope.student 实际上被转化成 request.getAttribute("student")语句
                                        (2)student.name 被转换成 student.getName()语句,所以实体类Student中必须要有一个公开的方法getName()用于获取属性name-->
  ${requestScope.student.age}<br>  <!-- 也可以省略前面的域对象(requestScope),直接改成:student.age (不推荐)-->
  ${requestScope.student.sex}<br>
  ${requestScope.student.address.homeAddress}<br>
  ${requestScope.student.address.tempAddress}<br>

  ===============在EL中使用empty运算符判断一个值是否为null======================<br>
  ${empty requestScope.student.address.tempAddress}<br>
  ${empty requestScope["xs"]}<br> <!-- 属性xs在request在中不存在 -->

  ==============在EL中通过requestScope(请求域)获取数组中的元素==================<br>
  ${requestScope.country[0]}<br>
  ${requestScope.country[1]}<br>

  ==============在EL中通过requestScope获取map属性(map集合)===================<br>
  ${requestScope.map.zh}
  ${requestScope['map']['us']}

  </body>
</html>
