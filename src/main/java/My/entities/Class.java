package My.entities;

public class Class {

    private Integer classId;
    private String className;
    private String teacherName;
    private Teacher teacher;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Class(Integer classId, String className, String teacherName, Teacher teacher) {
        this.classId = classId;
        this.className = className;
        this.teacherName = teacherName;
        this.teacher = teacher;
    }

    public Class() {
    }

    @Override
    public String toString() {
        return "Class{" +
                "classId=" + classId +
                ", className='" + className + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
