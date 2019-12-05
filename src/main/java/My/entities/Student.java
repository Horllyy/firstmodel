package My.entities;

import java.util.Date;

public class Student {

	private Integer id;
    private String stuId;
    private String stuName;
    private Integer gender;
    private String deptName;
    private Date birth;
    private String phoneNumber;
    private Integer QQ;
    private String email;
    //1 male, 0 female
    private Department department;


    public Integer getId() {
    return id;
}

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getQQ() {
        return QQ;
    }

    public void setQQ(Integer QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Student(Integer id, String stuId, String stuName, Integer gender, String deptName,
                   Date birth, String phoneNumber, Integer QQ, String email, Department department) {
        this.id = id;
        this.stuId = stuId;
        this.stuName = stuName;
        this.gender = gender;
        this.deptName = deptName;
        this.birth = birth;
        this.phoneNumber = phoneNumber;
        this.QQ = QQ;
        this.email = email;
        this.department = department;
    }

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", gender=" + gender +
                ", deptName='" + deptName + '\'' +
                ", birth=" + birth +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", QQ=" + QQ +
                ", email='" + email + '\'' +
                ", department=" + department +
                '}';
    }
}
