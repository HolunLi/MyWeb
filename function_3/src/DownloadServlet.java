import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

/*
扩展知识点:
mime类型是多用途互联网邮件扩展类型(英文全称Multipurpose Internet Mail Extensions)。
用来设置某种扩展名的文件用一种应用程序来打开的方式类型，当该扩展名文件被访问的时候，浏览器会自动使用指定应用程序来打开。
多用于指定一些客户端自定义的文件名，以及一些媒体文件打开方式。
文件下载就是单纯的IO操作,就是通过输入/输出流进行读写文件操作
 */

@WebServlet(name = "downloadServlet", urlPatterns = "/DownloadServlet")
public class DownloadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取下载的文件名
        String filename = req.getParameter("filename");
        //获取下载的文件的类型(mime类型)
        String mimeType = getServletContext().getMimeType("download/" + filename);
        /*下载文件,一定要设置两个消息头(响应头)*/
        //这个响应头告诉客户端浏览器,下载的文件是什么类型的文件。
        resp.setHeader("content-Type",mimeType); //还可以直接改成: resp.setHeader("content-Type","application/octet-stream"); 告诉客户端浏览器下载的文件是一个二进制文件,即任意类型的文件都可以下载(通用)
        //这个响应头告诉浏览器,这个文件是一个附件,即是需要下载的。attachment表示附件，也就是待下载的一个文件。fileName=待下载的文件的名字。
        resp.setHeader("content-Disposition", "attachment;filename=" + URLEncoder.encode(filename,"UTF-8"));
        //如果附件(待下载的文件)的名字是中文,客户端在实际进行下载时会出现文件名乱码,所以需要对中文附件名进行编码处理防止出现乱码。方法: URLEncoder.encode("待下载的文件名(附件名)","编码方式")

        //获取一个指向下载的文件的输入流
        InputStream in = getServletContext().getResourceAsStream("download/" + filename); //InputStream in = new FileInputStream("D:/MyWeb/function_3/web/download/" + filename); 还可以直接new一个指向文件的文件字节输入流
        //getServletContext()方法是DownloadServlet类继承而来的方法,调用该方法返回一个ServletContext对象
        //ServletContext对象调用getResourceAsStream("文件的路径")方法返回一个指向文件的字节输入流

        //通过输出流将读取的数据(分页.docx),输出到客户端
        OutputStream out = resp.getOutputStream(); //response对象调用该方法可以获取一个字节输出流,该输出流输出的目的地是客户端
        int len = -1;
        byte[] bytes = new byte[1024];
        while ((len=in.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }
        out.flush();
        out.close();
        in.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
