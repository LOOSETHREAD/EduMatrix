package Data.Database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class DatabaseConnection {
    private static volatile DatabaseConnection instance;
    private Connection connection;

    private static final String SERVER = "127.0.0.1";
    private static final String PORT = "3306";
    private static final String DATABASE = "edumatrix";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    private DatabaseConnection() {
        try {
            connectToDatabase();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    private void connectToDatabase() throws SQLException, ClassNotFoundException {
        // Load the MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Establish a new connection
        connection = DriverManager.getConnection(
                "jdbc:mysql://" + SERVER + ":" + PORT + "/" + DATABASE, USER, PASSWORD);
    }

    public synchronized Connection getConnection() {
        try {
            // Check if the connection is valid (timeout = 2 seconds)
            if (connection == null || connection.isClosed() || !connection.isValid(2)) {
                connectToDatabase(); // Reconnect if the connection is invalid
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
