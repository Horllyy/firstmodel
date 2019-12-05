package My.controller;

import My.entities.Department;
import My.entities.Role;
import My.entities.Student;
import My.mapper.DepartmentMapper;
import My.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

@Controller
//public class EmployeeController {
public class StuInfoController {

//    @Autowired
//    EmployeeDao employeeDao;
//
//    @Autowired
//    DepartmentDao departmentDao;


    @Autowired
    StudentMapper studentMapper;

    @Autowired
    DepartmentMapper departmentMapper;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

//查所有员工
    @GetMapping("/stus")
    public String list(Model model,HttpSession session){
        Collection<Student> students = studentMapper.getAll();

        System.out.println(students);

        for (Student student:students
        ) {
            student.setDepartment(departmentMapper.getDeptById(student.getId()));
//            student.setBirth(new Date());
        }

        model.addAttribute("stus",students);

        Role role= (Role) session.getAttribute("role");
        if (role.getRole().equals("manager")){
            return "stu/listManager";
        }
        else if (role.getRole().equals("bigboss")){
            return "stu/listBigBoss";
        }
        else {
            return "stu/listMonitor";
        }
//        //会和默认路径  private String prefix = "classpath:/templates/"; 拼接
//        return "stu/list";
    }

   //添加学生
    @GetMapping("/stu")
    public String addPage(Model model,HttpSession session){
        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("depts",departments);

        Role role= (Role) session.getAttribute("role");
        if (role.getRole().equals("manager")){
            return "stu/addManager";
        }
        else if (role.getRole().equals("bigboss")){
            return "stu/addBigBoss";
        }
        else {
            return "stu/addMonitor";
        }
//        return "stu/add";
    }

    //springmvc自动将请求参数和入参对象的属性进行一一对应：要求两方属性相同
    @PostMapping("/stu")
    public String addStu(Student student){
        System.out.println(student);
//        employeeDao.save(student);

        student.setDepartment(departmentMapper.getDeptByName(student.getDeptName()));
//        student.setDepartment(departmentMapper.getDeptByName(student.getDepartment().getDepartmentName()));
        System.out.println(student);
		studentMapper.insertStu(student);

        return "redirect:/stus";
    }



    //来到修改界面
    @GetMapping("/stu/{id}")
    public String editPage(@PathVariable("id") Integer id, Model model,HttpSession session){
        Student student = studentMapper.getStuById(id);
        System.out.println(student);

        student.setDepartment(departmentMapper.getDeptByName(student.getDeptName()));
        System.out.println(student);

        model.addAttribute("stu",student);

        Collection<Department> departments = departmentMapper.getDepartments();
        model.addAttribute("depts",departments);

        Role role= (Role) session.getAttribute("role");
        if (role.getRole().equals("manager")){
            return "stu/updateManager";
        }
        else if (role.getRole().equals("bigboss")){
            return "stu/updateBigBoss";
        }
        else {
            return "stu/updateMonitor";
        }
    }

    //修改
    @PutMapping("/stu")
    public String updateStudent(Student student){
        System.out.println("修改的员工数据："+student);
//        employeeDao.save(employee);

        student.setDepartment(departmentMapper.getDeptByName(student.getDeptName()));
        System.out.println("含department参数的修改的员工数据: "+student);

        studentMapper.updateStu(student);

        return "redirect:/stus";
    }

    //删除
    @DeleteMapping("/stu/{id}")
    public String deleteStudent(@PathVariable("id") Integer id,HttpSession session){
        studentMapper.deleteStu(id);

        return "redirect:/stus";
    }

//    //查询所有员工
//    @GetMapping("/emps")
//    public String list(Model model){
//        Collection<Employee> employees = employeeDao.getAll();
//
//        model.addAttribute("emps",employees);
//
//        //会和默认路径  private String prefix = "classpath:/templates/"; 拼接
//        return "emp/list";
//    }
//
//    @GetMapping("/emp")
//    public String addPage(Model model){
//        Collection<Department> departments = departmentDao.getDepartments();
//        model.addAttribute("depts",departments);
//        return "emp/add";
//    }
//
//    //springmvc自动将请求参数和入参对象的属性进行一一对应：要求两方属性相同
//    @PostMapping("/emp")
//    public String addEmp(Employee employee){
//        System.out.println(employee);
//        employeeDao.save(employee);
//        return "redirect:/emps";
//    }
//
//    //来到修改界面
//    @GetMapping("/emp/{id}")
//    public String editPage(@PathVariable("id") Integer id, Model model){
//        Employee employee = employeeDao.get(id);
//        System.out.println(employee);
//        model.addAttribute("emp",employee);
//
//        Collection<Department> departments = departmentDao.getDepartments();
//        model.addAttribute("depts",departments);
//
//        return "emp/update";
//    }
//
//
//    //修改
//    @PutMapping("/emp")
//    public String updateEmplayee(Employee employee){
//        System.out.println("修改的员工数据："+employee);
//        employeeDao.save(employee);
//        return "redirect:/emps";
//    }
//
//    @DeleteMapping("/emp/{id}")
//    public String deleteEmployee(@PathVariable("id") Integer id){
//        employeeDao.delete(id);
//        return "redirect:/emps";
//    }
}
