/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data.Controller;

import Data.Database.DatabaseConnection;
import Data.Models.ModelCourse;
import Data.Models.ModelStudentCourse;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

        

/**
 *
 * @author User
 */
public class AddData {
    private DefaultTableModel tableModel;
    private PreparedStatement p;
    public AddData(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
        
    public void addCourseToDatabase(ModelCourse addData) {
    try {
       String sql = "INSERT INTO courselist (coursecode, coursename,description,teacherid) VALUES (?, ?, ?,?)";
        
        
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, addData.getCourseCode());
        p.setString(2, addData.getCourseName());
        p.setString(3, addData.getDescription());
        p.setString(4, addData.getTeacherid());
        int rowsAffected = p.executeUpdate();
        if(rowsAffected > 0)
        {
            JOptionPane.showMessageDialog(null, "Data Added Succesfully");
        }else{
            JOptionPane.showMessageDialog(null, "Failed to add data.");
            
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error Adding data:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
    }finally{
        try {
            if (p !=null) p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }
    public void deleteCourseToDatabase(ModelCourse deleteData) {
    try {
       String sql = "DELETE FROM courselist WHERE coursecode = ? AND coursename = ? AND description = ? AND teacherid = ?";
        
        
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, deleteData.getCourseCode());
        p.setString(2, deleteData.getCourseName());
        p.setString(3, deleteData.getDescription());
        p.setString(4, deleteData.getTeacherid());
        int rowsAffected = p.executeUpdate();
        if(rowsAffected > 0)
        {
            JOptionPane.showMessageDialog(null, "Data deleted Succesfully");
        }else{
            JOptionPane.showMessageDialog(null, "Failed to delete data.");
            
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error Deleting data:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
    }finally{
        try {
            if (p !=null) p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }
    public void updateCourseToDatabase(ModelCourse updateData, int idcourselist) {
    try {
       String sql = "UPDATE courselist SET coursecode = ?, coursename = ?, description = ?, teacherid = ? WHERE idcourselist = ?";
        
        
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, updateData.getCourseCode());
        p.setString(2, updateData.getCourseName());
        p.setString(3, updateData.getDescription());
        p.setString(4, updateData.getTeacherid());
        p.setInt(5, idcourselist);
        int rowsAffected = p.executeUpdate();
        if(rowsAffected > 0)
        {
            JOptionPane.showMessageDialog(null, "Data updated Succesfully");
        }else{
            JOptionPane.showMessageDialog(null, "Failed to update data.");
            
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error Updating data:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
    }finally{
        try {
            if (p !=null) p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }
    public void addStudentToCourse(ModelStudentCourse addData) {
    try {
       String sql = "INSERT INTO student_to_course (coursecode, coursename, student_name, studentID, teacherid) VALUES (?, ?, ?, ?, ?)";
        
        
        p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, addData.getCourseCode());
        p.setString(2, addData.getCourseName());
        p.setString(3, addData.getStudentName());
        p.setString(4, addData.getStudentID());
        p.setString(5, addData.getTeacherid());
        int rowsAffected = p.executeUpdate();
        if(rowsAffected > 0)
        {
            JOptionPane.showMessageDialog(null, "Data Added Succesfully");
        }else{
            JOptionPane.showMessageDialog(null, "Failed to add data.");
            
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error Adding data:" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        
    }finally{
        try {
            if (p !=null) p.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    }
}
    

