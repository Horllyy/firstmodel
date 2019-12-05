package My.entities;

public class Role {

    private String stuId;
    private String role;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Role(String stuId, String role) {
        this.stuId = stuId;
        this.role = role;
    }

    public Role() {
    }

    @Override
    public String toString() {
        return "Role{" +
                "stuId='" + stuId + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
