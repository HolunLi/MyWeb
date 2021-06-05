import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

/*
想要将文件进行上传,需要先导入两个jar包(commons-fileupload 和 commons-io)
 */
@WebServlet(name = "uploadServlet", urlPatterns = "/UploadServlet")
public class UploadServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码
        req.setCharacterEncoding("UTF-8");
        //设置响应编码
        resp.setCharacterEncoding("UTF-8");
        //设置响应类型
        resp.setContentType("text/html; charset=UTF-8");

        //静态isMultipartContent方法用于判断前台表单中是否包含multipart属性
        Boolean isMultipart = ServletFileUpload.isMultipartContent(req); //前台表单中输入的信息都被封装在request对象中
        try {
            if (isMultipart) {
                //DiskFileItemFactory类是创建FileItem对象的工厂,它主要用于临时内存和临时目录
                FileItemFactory fileItem = new DiskFileItemFactory();
                /*
                //如果要设置上传文件存放的临时目录,必须直接使用DiskFileItemFactory类new对象
                DiskFileItemFactory fileItem = new DiskFileItemFactory();
                */
                //ServletFileUpload类用于处理上传的文件数据,将表单中的每个输入项封装成一个FileItem对象
                ServletFileUpload upload = new ServletFileUpload(fileItem);

                //设置上传的单个文件的大小(单位为字节)。注意:必须在调用parseRequest方法解析form表单中所有请求字段之前,设置上传文件的大小
                upload.setFileSizeMax(1024*1024); //上传的文件最大只能为1M
                /*
                //除了可以设置上传单个文件的大小外,还可以设置上传文件存放的临时目录(先将上传的文件存放在临时目录,最后在存放到最终的目录)
                fileItem.setSizeThreshold(1024); //设置临时的缓冲文件大小
                fileItem.setRepository(new File("D:/temp")); //设置临时目录*/

                //通过parseRequest方法解析form表单中所有请求字段,并将其保存在list集合中。注:form表单中的字段分为普通字段和文件字段
                List<FileItem> list = upload.parseRequest(req);
                for (FileItem item : list) {
                    //判断当前是普通表单字段(uname,upwd),还是文件字段(upic)
                    if (item.isFormField()) { //如果是普通表单字段,简单处理一下
                        String itemName = item.getFieldName(); //getFieldName用于获取普通表单字段的名字
                        String name = null, pwd = null;
                        //判断当前普通表单字段是uname还是upwd
                        if ("uname".equals(itemName)){
                            name = item.getString("UTF-8"); //getString方法获取当前字段的值(属性值),在获取字段值时还可以设置编码格式,防止乱码(可设置也可不设置)
                        }
                        else if ("upwd".equals(itemName)){
                            pwd = item.getString("UTF-8");
                        }
                    }
                    else { //不是普通表单字段则一定是文件字段,进行上传
                        String itemName = item.getName(); //getName方法用于获取上传的文件的名字(包含文件的扩展名),注意别与getFieldName方法弄混
                        //限制上传文件的类型(只允许上传扩展名为docx,png的文件)
                        if (!(itemName.endsWith("docx") || itemName.endsWith("png"))) {
                            resp.getWriter().println(itemName + " 上传失败!");
                            return;
                        }

                        //指定用户上传的文件存放在哪个目录中。注:这个目录不要包含在tomcat服务器安装目录中
                        File file = new File("D:/MyWeb/function_3/web/upload", itemName);
                        item.write(file); //将用户上传的文件,通过输出流写入到指定的目录中存储
                        resp.getWriter().println(itemName + " 上传成功!");
                    }
                }
            }
        } catch (FileUploadBase.FileSizeLimitExceededException e) {//FileSizeLimitExceededException是FileUploadException的子异常
            resp.getWriter().println("上传的文件超过1M,上传失败!");
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
