package My.mapper;

import My.entities.Role;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

public interface RoleMapper {

    @Select("select * from role where stuId=#{stuId}")
    public Role getRoleByStuId(String stuId);

    @Select("select * from role")
    public Collection<Role> getLogins();

}
