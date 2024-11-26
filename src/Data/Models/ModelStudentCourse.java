/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Models;

/**
 *
 * @author User
 */
public class ModelStudentCourse {

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
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the studentName
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName the studentName to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return the studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    } 

    public ModelStudentCourse(String courseCode, String courseName, String studentName, String studentID, String teacherid) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.studentName = studentName;
        this.studentID = studentID;
        this.teacherid = teacherid;
    }
    
   private String courseCode;
   private String courseName;
   private String studentName;
   private String studentID;
   private String teacherid;
}
