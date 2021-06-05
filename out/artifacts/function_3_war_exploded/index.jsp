<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>注册</title>
  </head>
  <body>
  <form action="UploadServlet" method="post" enctype="multipart/form-data">
    <table border="0">
      <tr>
        <td align="right">用户名:</td>
        <td align="left"><input type="text" name="uname"></td>
      </tr>
      <tr>
        <td align="right">密码:</td>
        <td align="left"><input type="password" name="upwd"></td>
      </tr>
      <tr>
        <td align="right">上传照片:</td>
        <td align="left"><input type="file" name="upic"></td>
      </tr>
      <tr>
        <td colspan="2"><input type="submit" value="注册" style="width: 250px; height: 20px"></td>
      </tr>
    </table>
  </form>
  <a href="DownloadServlet?filename=分页.docx">分页.docx</a>
  </body>
</html>
