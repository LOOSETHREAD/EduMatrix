package Data.Controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Data.Models.ModelFacultyUser;
import Data.Database.DatabaseConnection;
import Data.Models.ModelStudentUser;
import com.sun.jdi.connect.spi.Connection;



public class AddUser {
    
    
    public  boolean addFacultyToDatabase(ModelFacultyUser data) throws ClassNotFoundException {
    try {
        
        String checkSql = "SELECT COUNT(*) AS count FROM facultyuser";
        PreparedStatement checkStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(checkSql);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int rowCount = resultSet.getInt("count");
        
        String sql;
        sql = switch (rowCount) {
            case 0 -> "INSERT INTO facultyuser (fullname, program, username, password, role, status, teacherid) VALUES (?, ?, ?, ?, 'ADMIN', ?, ?)";
            default -> "INSERT INTO facultyuser (fullname, program, username, password, role, status, teacherid) VALUES (?, ?, ?, ?, 'TEACHER', ?, ?)";
        };
        
        
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, data.getFullname());
        p.setString(2, data.getProgram());
        p.setString(3, data.getUserName());
        p.setString(4, new String(data.getPassword()));
        p.setString(5, data.getStatus());
        p.setString(6, data.getTeacherid());
        p.executeUpdate();
        return true; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    }
    }
    public ModelFacultyUser SignInFaculty(ModelFacultyUser data){
          try {
             
              String sql = "SELECT * FROM facultyuser WHERE username LIKE ? AND password LIKE ?";
              PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
              p.setString(1, data.getUserName());
              p.setString(2, new String(data.getPassword()));
              ResultSet rs = p.executeQuery();
            if (rs.next()) {
                
                return new ModelFacultyUser(rs.getString("username"), rs.getString("password").toCharArray(), rs.getString("fullname"), rs.getString("status"),rs.getString("teacherid"));
            } else {
                
                return null;
            }
          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
        
     
      }
    public  boolean addStudentToDatabase(ModelStudentUser data) throws ClassNotFoundException {
    try {
        
        String checkSql = "SELECT COUNT(*) AS count FROM studentuser";
        PreparedStatement checkStatement = DatabaseConnection.getInstance().getConnection().prepareStatement(checkSql);
        ResultSet resultSet = checkStatement.executeQuery();
        resultSet.next();
        int rowCount = resultSet.getInt("count");
        
        String sql;
        sql = switch (rowCount) {
            default -> "INSERT INTO studentuser (fullname, program, username, password, status, studentid) VALUES (?, ?, ?, ?, ?, ?)";
        };
        
        
        PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
        p.setString(1, data.getFullname());
        p.setString(2, data.getProgram());
        p.setString(3, data.getUsername());
        p.setString(4, new String(data.getPassword()));
        p.setString(5,  data.getStatus()); 
        p.setString(6, data.getStudentid());
        p.executeUpdate();
        return true; 
    } catch (SQLException e) {
        e.printStackTrace();
        return false; 
    }
    }
    public ModelStudentUser SignInStudent(ModelStudentUser data){
          try {
             
              String sql = "SELECT * FROM studentuser WHERE username LIKE ? AND password LIKE ?";
              PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
              p.setString(1, data.getUsername());
              p.setString(2, new String(data.getPassword()));
              ResultSet rs = p.executeQuery();
            if (rs.next()) {
                
                return new ModelStudentUser(rs.getString("username"), rs.getString("password").toCharArray(),rs.getString("status"), rs.getString("fullname"));
            } else {
                
                return null;
            }
          } catch (Exception e) {
              e.printStackTrace();
              return null;
          }
        
     
      }
   public String encryptPass(String password){
        try {
            MessageDigest digestor = MessageDigest.getInstance("SHA-256");
            byte [] encodeHash = digestor.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder encryptionValue = new StringBuilder(2 * encodeHash.length);
            
             for (int i = 0; i < encodeHash.length; i++) {
                String hexVal = Integer.toHexString(0xff & encodeHash[i]);
                if (hexVal.length() == 1) {
                    encryptionValue.append('0');
                }
                encryptionValue.append(hexVal);
            }
             return encryptionValue.toString();
        } catch (Exception e) {
             return e.getMessage();
        }
        
    
}

}
