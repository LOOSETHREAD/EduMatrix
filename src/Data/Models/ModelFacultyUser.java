package Data.Models;

public class ModelFacultyUser {

    /**
     * @return the teacherid
     */
    public String getTeacherid() {
        return teacherid;
    }

    /**
     * @param teacherid the teacherid to set
     */
    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the program
     */
    public String getProgram() {
        return program;
    }

    /**
     * @param program the program to set
     */
    public void setProgram(String program) {
        this.program = program;
    }

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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public ModelFacultyUser(String fullname, String program, String username, char[] password, String status, String teacherid) {
        this.fullname = fullname;
        this.program = program;
        this.username = username;
        this.password = password;
        this.status = status;
        this.teacherid = teacherid;
    }
    
    public ModelFacultyUser( String username, char[] password, String fullname, String status, String teacherid) {
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.status = status;
        this.teacherid = teacherid;
    }

        public enum UserRole {
        ADMIN,
        TEACHER
    }
    private String fullname;
    private String program;
    private String username;
    private char[] password;
    private UserRole role;
    private String status;
    private String teacherid;
}