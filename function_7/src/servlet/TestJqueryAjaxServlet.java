package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "testJqueryAjaxServlet", urlPatterns = "/TestJqueryAjaxServlet")
public class TestJqueryAjaxServlet extends HttpServlet {
    private static final long serialVersionUID = -274346991625243746L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Student> map = new HashMap<>();
        Student student1 = new Student("17171", "李豪伦", 21, "计算机");
        Student student2 = new Student("17172", "独一味", 20, "城建");
        Student student3 = new Student("17173", "小张", 22, "计算机");
        Student student4 = new Student("17174", "老江", 26, "外国语");
        Student student5 = new Student("17175", "麻子", 22, "人文");
        map.put(student1.getXh(), student1);
        map.put(student2.getXh(), student2);
        map.put(student3.getXh(), student3);
        map.put(student4.getXh(), student4);
        map.put(student5.getXh(), student5);

        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");
        String xh = req.getParameter("xh");
        //根据学号模拟在数据库中查找是否有该学生
        Student stu = map.get(xh);
        if (stu == null)
            stu = new Student();
        /*因为拼接一个JSON文本复杂且容易出错,所以一般使用第三方插件(jackson)将一个java对象转换JSON文本*/
        //创建jackson插件的ObjectMapper对象
        ObjectMapper mapper = new ObjectMapper();
        //将一个java对象转换JSON文本(JSON字符串)
        mapper.writeValue(resp.getWriter(), stu);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
