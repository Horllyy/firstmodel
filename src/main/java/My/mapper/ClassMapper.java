package My.mapper;

import My.entities.Class;
import My.entities.Teacher;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.ExampleMapper;

import java.util.Collection;

public interface ClassMapper extends BaseMapper<Class>, ExampleMapper<Class> {

    @Select("select * from classInfo where classId=#{classId}")
    public Class getClassByClassId(String classId);

    @Select("select * from classInfo")
    public Collection<Class> getAllClass();

    @Select("select * from teacherInfo")
    public Collection<Teacher> getAllTeacher();

    @Update("update classInfo SET teacherName=#{teacherName} WHERE classId=#{classId}")
    public void appointTeacherToClass(Class aclass);

    @Select("select * from teacherInfo where teacherId=#{teacherId}")
    public Teacher getTeacherByTeacherId(String teacherId);

    @Select("select * from teacherInfo where teacherName=#{teacherName}")
    public Teacher getTeacherByTeacherName(String teacherName);

    @Update("update classInfo set classId=#{classId},className=#{className},teacherName=#{teacherName} where classId=#{classId}")
    public void updateClassInfo(Class aclass);

    @Delete("delete from classinfo where classId=#{classId}")
    public void deleteClassByClassId(Integer classId);

    @Insert("insert into classinfo(className,teacherName) values(#{className},#{teacherName})")
    public void addClass(Class aclass);
}
