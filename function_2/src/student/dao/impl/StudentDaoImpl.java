package student.dao.impl;


import student.dao.IStudentDao;
import student.entity.Student;
import student.util.DBUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StudentDaoImpl implements IStudentDao {

    //判断学生是否存在
    public boolean isExist(int id) {
        return queryAStudentById(id) == null? true : false; //不存在返回true,存在返回false
    }

    //通过学号(id)查找学生
    public Student queryAStudentById(int id) {
        Student student = null;
        String sql = "select * from student where id=?";
        ResultSet resultSet = DBUtil.query(sql, id);
        try {
            if (resultSet.next()) {
                int i = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String sex = resultSet.getString("sex");
                student = new Student(i, name, age, sex);
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.close(DBUtil.connection, DBUtil.pStatement, DBUtil.resultSet);
        }
    }

    //查找所有的学生
    public List<Student> queryAllStudent() {
        List<Student> list = new ArrayList();
        String sql = "select * from student";
        ResultSet set = DBUtil.query(sql, null);
        try {
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                int age = set.getInt("age");
                String sex = set.getString("sex");
                Student student = new Student(id, name, age, sex);
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
           DBUtil.close(DBUtil.connection, DBUtil.pStatement, DBUtil.resultSet);
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //查询student表中总共包含几条数据
    public int getTotalCount() {
        String sql = "select count(*) from student";
        return DBUtil.getTotalCount(sql);
    }

    //查询当前页的数据集合,即当前页包含几条数据
    public List<Student> QueryStudentsByPage(int currentPage, int pageSize) {
        List<Student> list = new LinkedList<>();
        String sql = "select * from student limit ?,?";
        ResultSet set = DBUtil.query(sql, currentPage*pageSize, pageSize);
        try {
            while (set.next()) {
                Student student = new Student(set.getInt("id"),set.getString("name"),set.getInt("age"), set.getString("sex"));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }finally {
            DBUtil.close(DBUtil.connection, DBUtil.pStatement, DBUtil.resultSet);
            if (set != null) {
                try {
                    set.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //添加一个学生
    public boolean addStudent(Student student) {
        String sql = "insert into student values(?,?,?,?)";
        int count = DBUtil.update(sql, student.getId(), student.getName(), student.getAge(), student.getSex());
        if (count > 0)
            return true;
        return false;
    }

    //通过学号(id)删除一个学生
    public boolean deleteStudentById(int id) {
        String sql = "delete from student where id = ?";
        int count = DBUtil.update(sql, id);
        if (count > 0)
            return true;
        return false;
    }

    //根据学号修改一个学生的信息
    public boolean modifyStudentById(Student student) {
        String sql = "update student set name=?, age=?, sex=? where id=?";
        int count = DBUtil.update(sql, student.getName(), student.getAge(), student.getSex(), student.getId());
        if (count > 0)
            return true;
        return false;
    }
}
