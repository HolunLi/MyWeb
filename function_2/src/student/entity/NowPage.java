package student.entity;

import java.util.List;

public class NowPage {
    //总数据量
    private int totalCount;
    //页面大小
    private int pageSize;
    //总页面数
    private int totalPages;
    //当前页
    private int currentPage;
    //当前页面的数据集合(当前页面显示的数据量)
    private List<Student> students;

    public NowPage() {
    }

    public NowPage(int totalCount, int pageSize, int currentPage, List<Student> students) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.students = students;
        totalPages = totalCount%pageSize == 0? totalCount/pageSize : (totalCount/pageSize + 1);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
