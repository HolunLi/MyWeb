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

@WebServlet("/QueryStudentServlet")
public class QueryStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IStudentService iStudentService = new StudentServiceImpl();
        req.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = iStudentService.queryAStudentById(id);
        req.setAttribute("aStudentInfo", student);
        req.getRequestDispatcher("studentInfo.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
