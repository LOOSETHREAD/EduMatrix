/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Models;

/**
 *
 * @author User
 */
public class ModelStudentUser {

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

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public char[] getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(char[] password) {
        this.password = password;
    }

//    public String getStatus() {
//        return status;
//    }

//    public void setStatus(String status) {
//        this.status = status;
//    }

    public ModelStudentUser(String fullname, String program, String username, char[] password) {
        this.fullname = fullname;
        this.program = program;
        this.username = username;
        this.password = password;
    }

    public ModelStudentUser(String username, char[] password) {
        this.username = username;
        this.password = password;
    }
    
    private String fullname;
    private String program;
    private String username;
    private char[] password;
}
