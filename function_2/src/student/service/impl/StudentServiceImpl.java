package student.service.impl;

import student.dao.IStudentDao;
import student.dao.impl.StudentDaoImpl;
import student.entity.Student;
import student.service.IStudentService;
import java.util.List;

public class StudentServiceImpl implements IStudentService {
    private IStudentDao iStudentDao = new StudentDaoImpl();

    //通过学号查找某个学生
    public Student queryAStudentById(int id) {
        return iStudentDao.queryAStudentById(id);
    }

    //查询所有的学生
    public List<Student> queryAllStudent() {
        return iStudentDao.queryAllStudent();
    }

    //查询student表的总数据数
    public int getTotalCount() {
        return iStudentDao.getTotalCount();
    }

    //查询当前页的数据集合,即当前页包含几条数据
    public List<Student> QueryStudentsByPage(int currentPage, int pageSize) {
        return iStudentDao.QueryStudentsByPage(currentPage, pageSize);
    }

    //添加一个学生
    public boolean addStudent(Student student) {
        //数据库中不存在该学生时,再将该学生添加到数据库中
        if (iStudentDao.isExist(student.getId()))
            return iStudentDao.addStudent(student);
        return false;
    }

    //通过学号删除一个学生
    public boolean deleteStudentById(int id) {
        //不存在该学生,无需执行删除操作,直接return false
        if (iStudentDao.isExist(id))
            return false;
        return iStudentDao.deleteStudentById(id);
    }

    //修改一个学生的信息
    public boolean modifyStudentById(Student student) {
        if (iStudentDao.isExist(student.getId()))
            return false;
        return iStudentDao.modifyStudentById(student);
    }
}
