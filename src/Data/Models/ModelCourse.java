/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Models;

/**
 *
 * @author User
 */
public class ModelCourse {

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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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

    public ModelCourse(String courseCode, String courseName, String description,String teacherid) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.description = description;
        this.teacherid = teacherid;
    }
    
    private String courseCode;
    private String courseName;
    private String description;
    private String teacherid;
}
