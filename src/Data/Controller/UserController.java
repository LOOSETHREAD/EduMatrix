
package Data.Controller;

import Data.Models.ModelFacultyUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Data.Database.DatabaseConnection;
import Data.Models.ModelFacultyUser.UserRole;
import Data.Models.ModelStudentUser;
import java.util.Random;
import java.sql.Connection;

public class UserController {
    AddUser DAO;
    private Connection connection;
    public UserController() {
        this.DAO = new AddUser();
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    public boolean registerFacultyUser(ModelFacultyUser data) throws ClassNotFoundException{
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return DAO.addFacultyToDatabase(data);
    }
    public boolean registerStudentUser(ModelStudentUser data) throws ClassNotFoundException{
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return DAO.addStudentToDatabase(data);
    }
    public ModelFacultyUser LogInFaculty(ModelFacultyUser data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return this.DAO.SignInFaculty(data);
            }
        public ModelStudentUser LogInStudent(ModelStudentUser data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return this.DAO.SignInStudent(data);
            }
    public boolean isAdmin(ModelFacultyUser data) throws ClassNotFoundException {
        try {
            String sql = "SELECT role FROM facultyuser WHERE username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserName());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return UserRole.valueOf(rs.getString("role")) == UserRole.ADMIN;
            } else {
                return false; 
            }
        } catch (SQLException e) {  
            e.printStackTrace();
            return false;
        }
        
    }
//    public boolean signInUser(ModelStudentUser data) throws ClassNotFoundException {
//    try {
//        // Step 1: Verify if the user exists with the given username and password
//        String query = "SELECT * FROM students WHERE username = ? AND password = ?";
//        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
//        p.setString(1, data.getUsername());
//        p.setString(2, String.valueOf(data.getPassword())); // Convert char[] to String
//        ResultSet rs = p.executeQuery();
//        
//        if (rs.next()) {
//            // Step 2: Update status to 'unregistered' if user exists
//            String updateQuery = "UPDATE students SET status = 'unregistered' WHERE username = ?";
//            PreparedStatement updateStmt = DatabaseConnection.getInstance().getConnection().prepareStatement(updateQuery);
//            updateStmt.setString(1, data.getUsername());
//            updateStmt.executeUpdate();
//            return true; // User successfully signed in and status updated
//        } else {
//            return false; // Invalid login
//        }
//    } catch (SQLException e) {
//        e.printStackTrace();
//        return false; // Handle SQL exceptions gracefully
//    }
//
//}
    public boolean isAdminExists() throws ClassNotFoundException {
    try {
        // Step 1: Query to check if any user with status 'ADMIN' exists
        String query = "SELECT COUNT(*) FROM facultyuser WHERE status = 'ADMIN'";
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

        ResultSet rs = p.executeQuery();

        // Step 2: Check the result of the query
        if (rs.next()) {
            return rs.getInt(1) > 0; // Returns true if the count is greater than 0
        }
        return false; // No 'ADMIN' found
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // Handle SQL exceptions gracefully
    }
}
 public String generateStudentId() {
        Random random = new Random();
        String studentId;
        boolean isUnique;

        do {
            // Generate a random 6-digit number
            studentId = String.format("%06d", random.nextInt(1000000));

            // Check if it already exists in the database
            isUnique = checkStudentIdUniqueness(studentId);
        } while (!isUnique);

        return studentId;
    }
 private boolean checkStudentIdUniqueness(String studentId) {
        String sql = "SELECT COUNT(*) FROM studentuser WHERE studentid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // Returns true if no matching record is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Assume not unique if an error occurs
    }
 public String generateTeacherId() {
        Random random = new Random();
        String teacherId;
        boolean isUnique;

        do {
            // Generate a random 6-digit number
            teacherId = String.format("%06d", random.nextInt(100000));

            // Check if it already exists in the database
            isUnique = checkTeacherIdUniqueness(teacherId);
        } while (!isUnique);

        return teacherId;
    }
 private boolean checkTeacherIdUniqueness(String teacherId) {
        String sql = "SELECT COUNT(*) FROM facultyuser WHERE teacherid = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, teacherId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) == 0; // Returns true if no matching record is found
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Assume not unique if an error occurs
    }
}

