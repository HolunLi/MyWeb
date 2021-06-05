package servlet;

import entity.User;
import service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "loginCheckServlet", urlPatterns = "/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
    private static final long serialVersionUID = 57671172076849391L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        UserService userService = new UserService();
        String number = req.getParameter("number");
        String pwd = req.getParameter("pwd");
        Boolean flag1 = userService.isExist(new User(number, pwd));
        Boolean flag2 = req.getParameter("verificationCode").equalsIgnoreCase((String) session.getAttribute("verificationCode"));
        if (flag1 && flag2) {
            session.setAttribute("number", number);
            resp.getWriter().println("1");
        }
        else if (flag1)
            resp.getWriter().println("2");
        else
            resp.getWriter().println("3");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
