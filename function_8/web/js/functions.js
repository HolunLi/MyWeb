
function changeImg() {
    $("#verificationCodeImg").attr("src", "ImageCodeServlet?" + Math.random());
}

function ajaxFunction() {
    $.post(
        "LoginCheckServlet",

        $("#loginForm").serializeArray(),

        function (data) {
            if (data.match("1"))
                document.getElementById("loginForm").submit();
            else if (data.match("2"))
                $("#display").html("<p style='color: red'>验证码错误!</p>");
            else
                $("#display").html("<p style='color: red'>账号或密码有误!</p>");
        }
    )
}