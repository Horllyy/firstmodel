package My.controller;

import My.entities.Login;
import My.entities.Role;
import My.mapper.LoginMapper;
import My.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginMapper loginMapper;

    @Autowired
    RoleMapper roleMapper;

//    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @PostMapping(value = "/user/login")
//    @RequestParam的作用是 若不提交此参数 就报错
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String,Object> map, HttpSession session){

//        添加if判断role要去哪个main页面
//先根据stuId把对应的login信息拉出来 看看是否存在 不存在 gg；存在 核对密码loginPassword 相同 ok 登陆成功 把此login结构体存入session域中
        Login login=loginMapper.getLoginByStuId(username);
        Role role=roleMapper.getRoleByStuId(username);
        String url=loginMapper.getURLByStuId(username);


        if (login!=null&&login.getLoginPassword().equals(password)){
            //登陆成功以后 防止表单重复提交 可重定向到主页
//            session.setAttribute("loginUserName",username);
              login.setLoginName(loginMapper.getLoginName(username));
              session.setAttribute("login",login);//因为日后我还会增加修改密码等操作 所以我感觉把整个结构体直接传过来更保险方便
              session.setAttribute("role",role);//把角色也存进session域中,决定跳转页面
              session.setAttribute("url",url);

//            //目前只考虑登陆成功与否 至于role 稍后添加进来 用于决定跳转至哪个main页面
//            return "redirect:/main";


            //接下来的任务是： 1.多搞几个结构略微不同的跳转界面(功能栏有区别 学生有的功能没有) 根据role分别安排跳转
            //                 2.在各个模块可大展拳脚了。。。随你怎么玩怎么开发 不同分支开始蔓延开来

            System.out.println("role="+role.getRole());
                return "redirect:/"+url;




        }
        else if (login!=null&&!login.getLoginPassword().equals(password)){
            map.put("message","用户名密码错误，请重新输入！");
            return "login";
        }
        else {
            map.put("message","用户名不存在，请重新输入！");
            return "login";
        }


//        if(!StringUtils.isEmpty(username)&&"123456".equals(password)){
//            //登陆成功以后 防止表单重复提交 可重定向到主页
//            session.setAttribute("loginUserName",username);
//            return "redirect:/main";
//        }
//        else {
//            map.put("message","用户名密码错误");
//            return "login";
////            return "forward:/index";
//        }
    }
}
