<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录页面</title>
    <script type="text/javascript" src="js/jquery-3.6.0.js"></script>
    <script type="text/javascript">

        function ajaxFunction() {
            //获取需要发送的数据
            var name = document.getElementById("loginForm").uname.value;
            var pwd = document.getElementById("loginForm").upwd.value;

            /*
            //第一步:创建XMLHttpRequest对象
            var xhr = new XMLHttpRequest();

            //设置请求地址
            var url = "LoginCheckServlet?name=" + name + "&pwd=" + pwd; //如果请求方式是get,将需要发送的数据附在请求的地址后面

            //与服务器建立连接
            xhr.open("GET", url, true);

            //向服务器发出请求
            xhr.send();

            //指定事件处理函数(又称回调函数,HTTP请求的状态的每发生一次改变，XMLHttpRequest对象都会调用回调函数进行处理。因此在该函数中可以编写处理响应的语句。)
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText.match("false")) { //match函数用于判断一个字符串是否包含另一个字符串
                        document.getElementById("display").innerHTML = "<p style='color: red'>用户名或密码有误!</p>";
                    }
                    else {
                        document.getElementById("loginForm").submit();
                    }
                }
            }
            */

            /*
            //第一步:创建XMLHttpRequest对象
            var xhr = new XMLHttpRequest();

            //与服务器建立连接
            xhr.open("POST", "LoginCheckServlet", true);

            /!*如果请求方式是POST需要设置HTTP头*!/
            //如果发送的数据中,不包含文件数据
            xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
            //如果发送的数据中,包含文件数据
            //xhr.setRequestHeader("Content-type", "multipart/form-data");

            //向服务器发出异步请求
            xhr.send("name=" + name + "&pwd=" + pwd); //如果请求方式是POST,需要在send方法中放置需要发送的数据

            //指定事件处理函数(又称回调函数,HTTP请求的状态的每发生一次改变，XMLHttpRequest对象都会调用回调函数进行处理。因此在该函数中可以编写处理响应的语句。)
            xhr.onreadystatechange = function () {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    if (xhr.responseText.match("false")) { //match函数用于判断一个字符串是否包含另一个字符串
                        document.getElementById("display").innerHTML = "<p style='color: red'>用户名或密码有误!</p>";
                    }
                    else {
                        document.getElementById("loginForm").submit();
                    }
                }
            }
            */

            //使用JQuery实现Ajax(推荐)
            $.post(
                //设置请求的地址
                "LoginCheckServlet",

                //获取需要发送的服务器的数据
                {
                    name:$("#uname").val(),
                    pwd:$("#upwd").val()
                },

                //设置请求成功的回调函数,data就是服务器处理完请求后,返回的数据(响应数据)
                function (data) {
                    if (data.match("true"))
                        document.getElementById("loginForm").submit();
                    else
                        $("#display").html("<p style='color: red'>用户名或密码有误!</p>");
                }
            )
        }
    </script>
</head>
<body>
    <form id="loginForm" action="welcome.jsp">
        <table>
            <tr>
                <td>用户:<br><input type="text" name="uname" id="uname" /></td>
            </tr>
            <tr>
                <td>密码:<br> <input type="password" name="upwd" id="upwd" /></td>
            </tr>
            <tr>
                <td>
                    <input type="button" onclick="ajaxFunction()" value="登录"><br>
                    <span id="display"></span>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
