
package Data.Controller;

import Data.Models.ModelUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Data.Database.DatabaseConnection;
import Data.Models.ModelUser.UserRole;

public class UserController {
    AddUser DAO;
    public UserController() {
        this.DAO = new AddUser();
    }
    public boolean registerUser(ModelUser data) throws ClassNotFoundException{
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return DAO.addUserToDatabase(data);
    }
    public ModelUser LogIn(ModelUser data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return this.DAO.SignIn(data);
            }
    public boolean isAdmin(ModelUser data) throws ClassNotFoundException {
        try {
            String sql = "SELECT role FROM user WHERE username = ?";
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
    public boolean isTeacher(ModelUser data) throws ClassNotFoundException {
        try {
            String sql = "SELECT role FROM user WHERE username = ?";
            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
            p.setString(1, data.getUserName());
            ResultSet rs = p.executeQuery();
            if (rs.next()) {
                return UserRole.valueOf(rs.getString("role")) == UserRole.TEACHER;
            } else {
                return false; 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

