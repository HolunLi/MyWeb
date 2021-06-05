package student.servlet;

import student.entity.NowPage;
import student.entity.Student;
import student.service.IStudentService;
import student.service.impl.StudentServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/QueryStudentByPageServlet")
public class QueryStudentByPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IStudentService iStudentService = new StudentServiceImpl();
        String cPage = req.getParameter("currentPage");
        if (cPage == null)
            cPage = "0";

        int currentPage = Integer.parseInt(cPage);
        int totalCount = iStudentService.getTotalCount();
        int pageSize = 6;
        List<Student> students = iStudentService.QueryStudentsByPage(currentPage, pageSize);
        NowPage nowPage = new NowPage(totalCount, pageSize, currentPage, students);

        req.setAttribute("AllStudents", nowPage);
        req.getRequestDispatcher("index.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
