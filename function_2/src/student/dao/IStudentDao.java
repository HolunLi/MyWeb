package student.dao;

import student.entity.Student;
import java.util.List;

public interface IStudentDao {

    //判断学生是否存在
    boolean isExist(int id);

    //通过学号(id)查找学生
    Student queryAStudentById(int id);

    //查找所有的学生
    List<Student> queryAllStudent();

    //查询student表的总数据数
    int getTotalCount();

    //查询当前页的数据集合,即当前页包含几条数据
    List<Student> QueryStudentsByPage(int currentPage, int pageSize);

    //添加一个学生
    boolean addStudent(Student student);

    //通过学号(id)删除一个学生
    boolean deleteStudentById(int id);

    //修改一个学生的信息
    boolean modifyStudentById(Student student);
}
