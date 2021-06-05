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

@WebServlet("/AddStudentServlet")
public class AddStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IStudentService iStudentService = new StudentServiceImpl();
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        int age = Integer.parseInt(req.getParameter("age"));
        String sex = req.getParameter("sex");
        Student student = new Student(id, name, age, sex);
       if (iStudentService.addStudent(student))
           req.setAttribute("tip", "success");
       else
           req.setAttribute("tip", "fail");
        req.getRequestDispatcher("addStudent.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
