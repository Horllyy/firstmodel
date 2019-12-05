package My.mapper;


import My.entities.Student;

import java.util.Collection;

public interface StudentMapper {

    public Student getStuById(Integer id);

    public Student getStuByStuId(String stuId);

    public Collection<Student> getStuMonitors();

    public Collection<Student> getStuNotMonitors();

    public void insertStu(Student student);

    //或许还存在Employee的单复数问题
    public Collection<Student> getAll();

    public void deleteStu(Integer id);

    public void dismissMonitor(String stuId);

    public void appointMonitor(String stuId);

    public void updateStu(Student student);

    public String getStuIdByName(String name);
}
