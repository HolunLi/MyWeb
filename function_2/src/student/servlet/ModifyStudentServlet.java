package student.servlet;

import student.entity.Student;
import student.service.impl.StudentServiceImpl;
import student.service.IStudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ModifyStudentServlet")
public class ModifyStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IStudentService iStudentService = new StudentServiceImpl();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");
        Student student = new Student(id, name, age, sex);
        //注意这里一定要设置响应类型,否则页面会乱码
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if (iStudentService.modifyStudentById(student))
            resp.sendRedirect("QueryStudentByPageServlet");
        else
            resp.getWriter().println("不存在这个学生,修改失败!!!");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
