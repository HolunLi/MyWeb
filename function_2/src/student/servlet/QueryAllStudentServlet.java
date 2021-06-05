package student.servlet;

import student.service.impl.StudentServiceImpl;
import student.service.IStudentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/QueryAllStudentServlet")
public class QueryAllStudentServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IStudentService iStudentService = new StudentServiceImpl();
        List list = iStudentService.queryAllStudent();
        req.setAttribute("AllStudent", list);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
