package database;

import org.json.JSONException;

import java.sql.*;

public class JDBCUtils {

    // Connect to MySQL
    public static Connection getMySQLConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "projettuteure";
        String userName = "root";
        String password = "";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException,
            ClassNotFoundException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // Get Connection
        Connection connection = getMySQLConnection();

        // Create statement
        Statement statement = connection.createStatement();

        String sql = "Select * from PROFS";

        // Execute SQL statement returns a ResultSet object.
        ResultSet rs = statement.executeQuery(sql);

        // Fetch on the ResultSet
        // Move the cursor to the next record.
        while (rs.next()) {
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            String prenom = rs.getString(3);
            System.out.println("--------------------");
            System.out.println("EmpId:" + id);
            System.out.println("EmpNo:" + nom);
            System.out.println("EmpName:" + prenom);
        }

        // Close connection.
        connection.close();
    }


    }