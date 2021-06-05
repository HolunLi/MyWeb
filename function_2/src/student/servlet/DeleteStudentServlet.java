package student.servlet;

import student.service.impl.StudentServiceImpl;
import student.service.IStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        IStudentService iStudentService = new StudentServiceImpl();
        //注意这里一定要设置响应类型,否则页面会乱码
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        if (iStudentService.deleteStudentById(id))
            //resp.getWriter().println("删除成功!!!"); //response对象调用getWriter方法就可以获取out对象
            resp.sendRedirect("QueryStudentByPageServlet");
        else
            resp.getWriter().println("删除失败!!!");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
