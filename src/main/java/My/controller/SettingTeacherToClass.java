package My.controller;

import My.entities.Class;
import My.entities.Teacher;
import My.mapper.ClassMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.Collection;

@Controller
public class SettingTeacherToClass {

        @Autowired
        ClassMapper classMapper;

        @GetMapping("/settingTeacherToClass")
        public String settingTeacherToClass(Model model) {

            System.out.println("到达任命教师模块.");

            Collection<Class> classes=classMapper.getAllClass();
            Collection<Teacher> teachers =classMapper.getAllTeacher();
            System.out.println(classes);

            for (Class aclass:classes
                 ) {
                aclass.setTeacher(classMapper.getTeacherByTeacherName(aclass.getTeacherName()));
            }

            model.addAttribute("classes", classes);
            model.addAttribute("teachers", teachers);

            return "settingMonitor/settingTeacherToClass";

        }

    //班主任管理
    @PutMapping("/appointClass")
//    public String  appointClass(@RequestParam("classId") String classId, @RequestParam("teacherName") String teacherName){
//    public String  appointClass(@RequestParam("param1") String aclass,@RequestParam("param2") String teacherName){
    public String  appointClass(Class aclass,String submit){
        System.out.println("到达指定班主任模块");
        System.out.println("submit="+submit);
        if (submit.equals("update")) {
            System.out.println("修改前的班级信息：" + aclass);

            aclass.setTeacher(classMapper.getTeacherByTeacherName(aclass.getTeacherName()));
            System.out.println("修改后的班级信息：" + aclass);
            classMapper.updateClassInfo(aclass);
            System.out.println("指定班主任成功");
        }
        else if (submit.equals("delete")){
            System.out.println("即将删除此班级信息。。。。。。");
            classMapper.deleteClassByClassId(aclass.getClassId());
            System.out.println("删除成功");
            return "settingMonitor/deleteClassSucceed";
        }
        return "redirect:/settingTeacherToClass";
    }


    @GetMapping("/addClass")
    public String addClassPage(Model model){
        System.out.println("增加班级模块。。。。");
        Collection<Teacher> teachers =classMapper.getAllTeacher();
        model.addAttribute("teachers",teachers);
        System.out.println("teachers="+teachers);
        return "settingMonitor/addClass";
    }

    @PostMapping("/addClass")
    public String addClass(Class aclass){
        System.out.println("要增加的班级信息是："+aclass);
        classMapper.addClass(aclass);
        System.out.println("班级信息增加成功");
        return "redirect:/settingTeacherToClass";
    }

}
