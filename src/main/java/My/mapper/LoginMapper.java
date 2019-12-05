package My.mapper;

import My.entities.Login;
import org.apache.ibatis.annotations.Select;

import java.util.Collection;

public interface LoginMapper {

    @Select("select * from login where stuId=#{stuId}")
    public Login getLoginByStuId(String stuId);

    //这个方法纯粹为了补救我的中英文转换
    @Select("select stuName from stuInformation where stuId=#{stuId}")
    public String getLoginName(String stuId);

    @Select("select * from login")
    public Collection<Login> getLogins();

//    public String getLoginNameByStuId(String stuId);
//
//    public String getLoginPasswordByStuId(String stuId);

    @Select("SELECT menuUrl from page where role=(SELECT role from role where stuId=#{stuId})")
    public String getURLByStuId(String stuId);


}
