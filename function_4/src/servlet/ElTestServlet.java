package servlet;

import entity.Address;
import entity.Student;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "elTestServlet", urlPatterns = "/ElTestServlet")
public class ElTestServlet extends HttpServlet {
    private static final long serialVersionUID = 3170493076472570168L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Address address = new Address("江西", "上海");
        Student student = new Student(1,"伦哥", 22, "男" , address);
        String[] country = new String[] {"中国", "美国"};
        Map<String, String> map = new HashMap<>();
        map.put("zh", "中国");
        map.put("us", "美国");

        req.setAttribute("name", "holun");
        req.setAttribute("bird", "鸽子");
        req.getSession().setAttribute("bird", "麻雀"); //request对象调用getSession()方法获取session对象
        req.setAttribute("student", student);
        req.setAttribute("country", country);
        req.setAttribute("map", map);
        req.getRequestDispatcher("jstl.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
