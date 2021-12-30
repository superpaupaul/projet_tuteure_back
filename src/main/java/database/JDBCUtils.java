package database;

import org.json.JSONException;

import java.sql.*;

public class JDBCUtils {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "projettuteure";
        String userName = "root";
        String password = "";

        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,String userName, String password) throws SQLException,ClassNotFoundException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
        Connection conn = DriverManager.getConnection(connectionURL, userName,password);
        return conn;
    }
}