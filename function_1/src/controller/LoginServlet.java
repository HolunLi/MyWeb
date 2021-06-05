package controller;

import student.entity.Login;
import student.dao.LoginDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LoginServlet") //指明LoginServlet所在的目录，‘/’代表根目录,一个web项目将src作为项目的目录
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("uname");
        String pwd = request.getParameter("upsw");

        int result = LoginDao.loginDemo(new Login(name, pwd));
        if (result > 0)
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        else
            request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

