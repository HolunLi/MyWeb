package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "loginCheckServlet", urlPatterns = "/LoginCheckServlet")
public class LoginCheckServlet extends HttpServlet {
    private static final long serialVersionUID = -8373053469322955407L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("uname");
        String pwd = req.getParameter("upwd");
        if ("李豪伦".equals(name) && "123".equals(pwd)) {
            req.getSession().setAttribute("uname", name);
            req.getSession().setAttribute("upwd", pwd);
            resp.sendRedirect("welcome.jsp");
        }
        else {
            req.getSession().setAttribute("error", "用户名或密码错误!!!");
            req.getSession().setMaxInactiveInterval(6);
            resp.sendRedirect("login.jsp");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
