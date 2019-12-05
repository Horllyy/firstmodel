package My.controller;

import My.entities.Login;
import My.entities.Role;
import My.entities.Student;
import My.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;

@Controller
public class CheckInfoController {

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/check")
    public String list(Model model, HttpSession session){
        Login login=(Login) session.getAttribute("login");
        Student student = studentMapper.getStuByStuId(login.getStuId());

        System.out.println("查看个人信息："+student);

//        for (Student student:students
//        ) {
//            student.setDepartment(departmentMapper.getDeptById(student.getId()));
////            student.setBirth(new Date());
//        }

        model.addAttribute("stu",student);

        Role role= (Role) session.getAttribute("role");
        if (role.getRole().equals("manager")){
            return "checkPersonalInfo/checkManager";
        }
        else if(role.getRole().equals("monitor")){
            return "checkPersonalInfo/checkMonitor";
        }
        else if(role.getRole().equals("bigboss")){
            return "checkPersonalInfo/checkBigBoss";
        }
        else{
            return "checkPersonalInfo/checkStu";
        }


    }
}
