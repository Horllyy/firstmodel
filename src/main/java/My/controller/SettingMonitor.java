package My.controller;

import My.entities.Student;
import My.mapper.StudentMapper;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;

@Controller
public class SettingMonitor {

    @Autowired
    StudentMapper studentMapper;

    @GetMapping("/settingMonitor")
    public String settingMonitor(Model model) {

        System.out.println("到达任命班委模块.");

        Collection<Student> stuMonitors =studentMapper.getStuMonitors();
        Collection<Student> stus =studentMapper.getStuNotMonitors();

        model.addAttribute("stuMonitors", stuMonitors);
        model.addAttribute("stus", stus);

            return "settingMonitor/settingMonitor";

    }

    //撤职班委
    @PostMapping("/dismiss/{stuId}")
    public String dissmissMonitor(@PathVariable("stuId") String stuId){
        System.out.println("到达撤职班委模块");
        studentMapper.dismissMonitor(stuId);
        System.out.println("撤职班委成功");

        return "redirect:/settingMonitor";
    }

    //任命班委
    @PostMapping("/appoint/{stuId}")
    public String appointMonitor(@PathVariable("stuId") String stuId){
        System.out.println("到达任命班委模块");
        studentMapper.appointMonitor(stuId);
        System.out.println("任命班委成功");

        return "redirect:/settingMonitor";
    }
}
