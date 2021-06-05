<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>根据学号查询学生信息</title>
    <!-- 在当前JSP页面中引入jquery-3.6.0.js文件后,才能使用JQuery语法 -->
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>

    <!-- 使用jquery实现Ajax -->
    <script type="text/javascript">
        function testJqueryAjax() {
            //方法一: 使用JQuery中的ajax方法
            /*
            $.ajax(
                {
                    //指定请求的地址
                    url:"TestJqueryAjaxServlet",

                    //设置请求的方式,默认为get
                    type:"post",

                    //预估服务器返回的数据是什么类型(可以不设置,即此项可省略。如果不设置自动根据MIME类型返回信息)
                    dataType:"text",

                    //使用JQuery获取需要发送到服务器的数据
                    data:{
                        xh:$("#xh").val()
                    },

                    //设置请求成功后的回调函数,回调函数中的参数data为服务器返回的数据(响应数据)
                    success:function (data) {
                        //使用javascript中的eval()方法,将JSON文本转换成javascript对象
                        var stu = eval("(" + data + ")");
                        $("#name").val(stu.name);
                        $("#age").val(stu.age);
                        $("#yx").val(stu.yx);
                    },

                    //设置请求失败时调用的函数(可以不设置,即此项可省略)
                    error:function () {
                        alert("处理异常");
                    }
                }
            );
            */

            //方法二: 使用JQuery中的get方法(如果使用该方法,则方法中的参数必须严格按照顺序设置)
            /*
            $.get(
                //第一个参数是: 请求的地址
                "TestJqueryAjaxServlet",

                //第二个参数是: 发送到服务器的数据
                {
                    xh:$("#xh").val()
                },

                //第三个参数是: 请求成功后的回调函数。注意:回调函数中的参数data为服务器返回的数据(响应数据)
                function (data) {
                    //使用javascript中的eval()方法,将JSON文本转换成javascript对象
                    var stu = eval("(" + data + ")");
                    $("#name").val(stu.name);
                    $("#age").val(stu.age);
                    $("#yx").val(stu.yx);
                },

                //第四个参数是: 预估服务器的返回值类型(该参数可省略,如果省略自动根据MIME类型返回信息)
                "text"
            );
            */

            //方法三: 使用JQuery中的post方法(如果使用该方法,则方法中的参数必须严格按照顺序设置,否则会出错)
            $.post(
                //第一个参数是: 请求的地址
                "TestJqueryAjaxServlet",

                //第二个参数是: 发送到服务器的数据
                {
                    xh:$("#xh").val()
                },

                //第三个参数是: 请求成功后的回调函数。注意:回调函数中的参数data为服务器返回的数据(响应数据)
                function (data) {
                    //使用javascript中的eval()方法,将JSON文本转换成javascript对象
                    var stu = eval("(" + data + ")");
                    $("#name").val(stu.name);
                    $("#age").val(stu.age);
                    $("#yx").val(stu.yx);
                },

                //第四个参数是: 预估服务器的返回值类型(该参数可省略,如果省略自动根据MIME类型返回信息)
                "text"
            );
        }
    </script>
</head>
<body>
<table>
    <tr>
        <td>学号:&nbsp;<input type="test" id="xh" onblur="testJqueryAjax()"></td> <!--失去焦点时,调用testJqueryAjax()方法-->
    </tr>
    <tr>
        <td>姓名:&nbsp;<input type="test" id="name" readonly></td>
    </tr>
    <tr>
        <td>年级:&nbsp;<input type="test" id="age" readonly></td>
    </tr>
    <tr>
        <td>院系:&nbsp;<input type="test" id="yx" readonly></td>
    </tr>
</table>
</body>
</html>
