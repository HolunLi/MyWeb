<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> <!--当前JSP页面想要使用jstl的核心标签库,必须使用taglib指令标记引入jstl的核心标签库。-->
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> <!--当前JSP页面想要使用jstl的函数标签库,必须使用taglib指令标记引入jstl的函数标签库。
                                                                       注意:当前JSP页面已经引入了两个标签库,所以标签的前缀一定要不同-->
<html>
<head>
    <title>Title</title>
</head>
<body>
===========使用 c:set 标签,在某个作用域中，给某个变量重新赋值==============<br>
未赋值前:
${requestScope.name}
<c:set var="name" value="李豪伦" scope="request" /> <!-- 等价于request.setAttribute("name","holun") -->
<br>重新赋值后:
${requestScope.name}

<br>===========使用 c:set 标签,在某个作用域中，声明一个新的变量,并为其赋值(初始化)==============<br>
<c:set var="sex" value="男" scope="request" /> <!-- 等价于request.setAttribute("sex","男") -->
性别: ${requestScope.sex}

<br>===========使用 c:set 标签,在某个作用域中，给某个JavaBean对象的属性重新赋值==============<br>
未赋值前:
${requestScope.student.name}
<c:set target="${requestScope.student}" property="name" value="李豪伦" /> <!-- requestScope.student返回一个student对象(这个一个JavaBean对象),
这条jstl语句实际上就是调用student对象的setName方法为该对象的name属性重新设置一个值("李豪伦")。使用该语句的前提是student对象已被创建(不为null),
且该对象对应的类中提供了setName方法,否则执行到该语句时，会发生异常 --->
<br>重新赋值后:
${requestScope.student.name}

<br>===========使用 c:set 标签,在某个作用域中，给map对象(map集合对象)的属性重新赋值==============<br>
未赋值前:
${requestScope.map.us}
<c:set target="${requestScope.map}" property="us" value="法国" /> <!-- requestScope.map返回一个HashMap集合对象,
                                                                  通过这条语句为HashMap集合对象的us属性重新赋值"法国"  --->
<br>重新赋值后:
${requestScope.map.us}

<br>===========使用 c:out 标签输出数据==============<br>
<c:out value="hello" /> <!-- 直接输出value的值,value的值是一个字符串 --><br>
<c:out value="${requestScope.map.us}" default="us为null" />  <!-- map集合中存在us属性,该语句将us属性值输出 --><br>
<c:out value="${requestScope.map.ys}" default="ys为null" />  <!-- map集合中不存在ys属性,即value值为null。该语句将default值(ys为null)输出 -->

<br>===========使用 c:out 标签显示超链接==============<br>
<c:out value='<a href="https://www.bilibili.com/">B站</a>' escapeXml="false" /> <!-- escapeXml为false, 显示一个超链接--> <br>
<c:out value='<a href="https://www.bilibili.com/">B站</a>' escapeXml="true" /> <!-- escapeXml为true, 直接输出value的值-->

<br>===========使用 c:remove 标签删除某个作用域中的某个变量==============<br>
<c:set  var="mode" value="赛车" scope="request" /> <!-- 等价于 request.setAttribute("mode", "赛车"); -->
<c:out value="变量mode的值为: ${requestScope.mode}" />
<c:remove  var="mode" scope="request" /> <!-- 等价于 request.removeAttribute("mode"); --> <br>
<c:out value="${requestScope.mode}"  default="mode变量已被删除" />

<br>======获取bird属性的值(request和session中都包含bird属性)=======<br>
${bird} <!-- 这里没有指明从哪个域对象(作用域)获取bird属性的值,所以会按照作用域的大小,先从pageScope中找有没bird属性,没有再从requestScope中找,
requestScope中没有再从sessionScope中找, sessionScope中没有,最后从applicationScope中找,只要在其中一个作用域中成功获取到bird属性就结束,
不再继续从后面范围较大的作用域中获取,哪怕后面的作用域中也包含一个同名的bird属性。如果最后在applicationScope也没找到,说明就根本不存在bird属性 -->
<!-- 因为在请求域和会话域中都包含bird属性,所以在获取bird属性时,必须指明从哪个作用域中获取 -->
<br>${requestScope.bird}<br>
${sessionScope.bird}

<br>======流程控制标签的使用======<br>
<!-- 单分支选择语句 -->
<c:if test="true"> <!-- 当test属性的值为布尔类型true时,执行该标签中的内容。注意:test属性的值是布尔类型,并不是字符串,所以""中一定不能带空格-->
    你好!!!<br>
</c:if>
<c:if test="${3>2}"> <!-- c:if 标签是单分支if语句,并没有与之匹配的c:else 标签 -->
    hello!!!<br>
</c:if>

<!-- 多分支选择语句 -->
<c:set var="i" value="1" scope="page" />
<c:choose>
    <c:when test="${pageScope.i > 0}"> <!-- 当test属性的值为布尔类型true时,执行该标签中的内容。注意:test属性的值是布尔类型,并不是字符串,所以""中一定不能带空格-->
        <c:out value="${pageScope.i} 是正数" />
    </c:when>
    <c:when test="${pageScope.i < 0}">
        <c:out value="${pageScope.i} 是负数" />
    </c:when>
    <c:otherwise>
        啥也不是!!!
    </c:otherwise>
</c:choose>

<br>======迭代标签的使用======<br>
<%
    Set<String> set = new HashSet<>();
    set.add("stu_1");
    set.add("stu_2");
    set.add("stu_3");
    set.add("stu_4");
    request.setAttribute("set", set);

    String[] args = new String[] {"A", "B", "C", "D"};
    pageContext.setAttribute("args", args);
%>

<!-- 使用增强for循环遍历HashSet集合 -->
<c:forEach var="stu" items="${set}"> <!-- 等价于: for (String stu : set){...} -->
    ${stu} <br>
</c:forEach>

<!-- 使用普通for循环遍历数组中的元素 -->
<c:forEach var="i" begin="0" end="${fn:length(pageScope.args)-1}" step="1" varStatus="status"> <!--等价于: for (int i=0; i<=args.length; i++){...}  -->
    ${pageScope.args[i]} 是数组中的第${status.index+1}个元素 <!--改成: i+1 也行 --> <br>
</c:forEach>

<br>======URL标签的使用======<br>
<c:url value="test.jsp" var="link1" /> <!-- c:url 标签还有一个scope属性可以指定当前构造的URL处于哪个作用域中,默认处于页面域-->
<a href="${link1}">走</a>
<br>
<c:url value="test.jsp" var="link2" scope="page">
    <c:param name="sex" value="男" /> <!-- 用 c:param 向指定URL地址传递参数 -->
</c:url>
<a href="${link2}">走</a>
</body>
</html>