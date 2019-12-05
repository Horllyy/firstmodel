package My.mapper;

import My.entities.Department;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

/**
 * 这是一个操作数据库的mapper
 */

//为了日后方便 改成批量扫描 在Application.java里最前面@MapperScan(basePackages = "My.mapper")
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Select("select * from department where departmentName=#{departmentName}")
    public Department getDeptByName(String departmentName);

    @Select("select departmentName from department")
    public Collection<Department> getDepartments();

    @Delete("delete from department where id=#{id}")
    public int deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) values(#{departmentName})")
    public int insertDept(Department department);

    @Update("update department set departmentName=#{departmentName} where id=#{id}")
    public int updateDept(Department department);
}
