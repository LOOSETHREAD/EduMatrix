
package Data.Controller;
import Data.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PopulateTable{

    public static void populateStudentTable( JTable table) {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        
        ps = DatabaseConnection.getInstance().getConnection().prepareStatement("SELECT * FROM studentuser WHERE status = 'not verified'");
       

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        rs = ps.executeQuery();
        while (rs.next()) {
           
            
                
            
                Vector <Object> v = new Vector<>();
                v.add(rs.getString("fullname"));
                v.add(rs.getString("program"));
                v.add(rs.getString("status"));
                model.addRow(v);
           
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in finally block to ensure they are always closed
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
       e.printStackTrace();
        }
    }
   }
   public static void populateTeacherTable(JTable table) {
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // SQL query to filter by role = 'teacher' and status = 'not verified'
        String query = "SELECT * FROM facultyuser WHERE role = 'TEACHER' AND status = 'not verified'";
        ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing rows in the table

        rs = ps.executeQuery();

        // Populate the table with filtered results
        while (rs.next()) {
            Vector<Object> v = new Vector<>();
            v.add(rs.getString("fullname"));
            v.add(rs.getString("program"));
            v.add(rs.getString("role"));
            v.add(rs.getString("status"));
            v.add(rs.getString("teacherid"));
            model.addRow(v); // Add each row to the table model
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    } finally {
        // Close resources in finally block
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
    public static void populateCourseTable(JTable table, String teacherid) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM courselist WHERE teacherid = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, teacherid);  // Set the teacherid parameter

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows in the table

            rs = ps.executeQuery();

            // Populate the table with filtered results
            while (rs.next()) {
                String courseCode = rs.getString("coursecode");
                String courseName = rs.getString("coursename");
                String description = rs.getString("description");
                int id = rs.getInt("idcourselist");
                String teacherIdInTable = rs.getString("teacherid");
                if (teacherIdInTable.equals(teacherid)) {
                    model.addRow(new Object[]{courseCode, courseName, description, id, teacherIdInTable});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    public static void populateRequestStudentToCourseTable(JTable table, String teacherid) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM studentrequestcourse WHERE teacherid = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, teacherid);  // Set the teacherid parameter

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows in the table

            rs = ps.executeQuery();

            // Populate the table with filtered results
            while (rs.next()) {
                String fullName = rs.getString("fullname");
                String studentid = rs.getString("studentid");
                String courseCode = rs.getString("coursecode");
                int id = rs.getInt("idstudentrequestcourse");
                String teacherIdInTable = rs.getString("teacherid");
                if (teacherIdInTable.equals(teacherid)) {
                    model.addRow(new Object[]{fullName, studentid, courseCode, id, teacherIdInTable});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
    public static void populateStudentToCourseTable(JTable table, String teacherid) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String query = "SELECT * FROM student_to_course WHERE teacherid = ?";
            ps = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            ps.setString(1, teacherid);  // Set the teacherid parameter

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear existing rows in the table

            rs = ps.executeQuery();

            // Populate the table with filtered results
            while (rs.next()) {
                String courseCode = rs.getString("coursecode");
                String courseName = rs.getString("coursename");
                String fullName = rs.getString("student_name");
                String studentid = rs.getString("studentID");
                int id = rs.getInt("idcourses");
                String teacherIdInTable = rs.getString("teacherid");
                if (teacherIdInTable.equals(teacherid)) {
                    model.addRow(new Object[]{courseCode,courseName,fullName, studentid, id, teacherIdInTable});
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error closing connection: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }

public static String getTeacherIDForLoggedInUser(String username) {
    String teacherID = null; // Variable to store the retrieved teacherID
    Connection connection = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        // SQL query to get teacherid for the logged-in user
        String query = "SELECT teacherid FROM facultyuser WHERE username = ? LIMIT 1";
        
        // Get the database connection
        connection = DatabaseConnection.getInstance().getConnection();
        
        // Prepare the statement
        ps = connection.prepareStatement(query);
        ps.setString(1, username); // Set the logged-in user's username
        
        // Execute the query
        rs = ps.executeQuery();
        
        // Retrieve the result
        if (rs.next()) {
            teacherID = rs.getString("teacherid"); // Get the teacherid from the result
        }
    } catch (SQLException ex) {
        JOptionPane.showMessageDialog(null, "Error retrieving teacher ID: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        ex.printStackTrace();
    } finally {
        // Close resources
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error closing resources: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    return teacherID; // Return the retrieved teacherid (or null if not found)
}



}