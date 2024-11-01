
package Data.Controller;

import Data.Models.ModelFacultyUser;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import Data.Database.DatabaseConnection;
import Data.Models.ModelFacultyUser.UserRole;

public class UserController {
    AddUser DAO;
    public UserController() {
        this.DAO = new AddUser();
    }
    public boolean registerUser(ModelFacultyUser data) throws ClassNotFoundException{
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return DAO.addFacultyToDatabase(data);
    }
    public ModelFacultyUser LogIn(ModelFacultyUser data){
        String encryptedPassword = DAO.encryptPass(new String(data.getPassword()));
        data.setPassword(encryptedPassword.toCharArray());
        return this.DAO.SignIn(data);
            }
    public boolean isAdmin(ModelFacultyUser data) throws ClassNotFoundException {
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
}

