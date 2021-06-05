package student.service;

import student.entity.Student;

import java.util.List;

public interface IStudentService {

    //通过学号查找某个学生
    Student queryAStudentById(int id);

    //查询所有的学生
    List<Student> queryAllStudent();

    //查询student表的总数据数
    int getTotalCount();

    //查询当前页的数据集合,即当前页包含几条数据
    List<Student> QueryStudentsByPage(int currentPage, int pageSize);

    //添加一个学生
    boolean addStudent(Student student);

    //通过学号删除一个学生
    boolean deleteStudentById(int id);

    //修改一个学生的信息
    boolean modifyStudentById(Student student);
}
