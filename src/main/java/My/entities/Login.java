package My.entities;

/**
 * 定义登陆信息结构体，把每个用户的登陆信息结构化处理，省去区分loginName，loginPassword和stuId的麻烦
 */
public class Login {

    private String stuId;
    private String loginName;
    private String loginPassword;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public Login(String stuId, String loginName, String loginPassword) {
        this.stuId = stuId;
        this.loginName = loginName;
        this.loginPassword = loginPassword;
    }

    public Login() {
    }

    @Override
    public String toString() {
        return "Login{" +
                "stuId='" + stuId + '\'' +
                ", loginName='" + loginName + '\'' +
                ", loginPassword='" + loginPassword + '\'' +
                '}';
    }
}
