package Data.Models;

public class ModelUser {

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getUserName() {
        return username;
    }

    public void setUserName(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }
    public String getconfirmPassword () {
        return confirmPassword ;
    }

    public void setVerifyCode(String confirmPassword ) {
        this.confirmPassword = confirmPassword ;
    }

    public ModelUser( String username, String email, char[] password) {
        
        this.username = username;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
    public ModelUser( String username, char[] password) {
       
        this.username = username;
        this.password = password;
    }

    public ModelUser() {
    }
        public enum UserRole {
        ADMIN,
        TEACHER,
        STUDENT
    }
    
    private String username;
    private String email;
    private char[] password;
    private String confirmPassword;
    private UserRole role;
}