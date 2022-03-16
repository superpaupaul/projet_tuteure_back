package database;

import com.example.demo.entities.QCM;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static database.JDBCUtils.getMySQLConnection;

public class JDBCRequests {

    public static int newQCMInDB(QCM qcm,int prof_id) throws SQLException, ClassNotFoundException {

        Connection connection = getMySQLConnection();
        Statement stmt = connection.createStatement();

        String sql = "INSERT INTO QCM(path,prof_id,titre) VALUES ('/Users/em/MC-Projects/"+qcm.getTitre()+"',"+prof_id+",'"+qcm.getTitre()+"');";

        int result = stmt.executeUpdate(sql);
        connection.close();
        return result;
    }

    public static int getId(String titre,int prof_id) throws SQLException, ClassNotFoundException {

        Connection connection = getMySQLConnection();
        Statement stmt = connection.createStatement();

        String sql = "SELECT qcm_id FROM QCM WHERE titre='"+titre+"' AND prof_id="+prof_id;


        ResultSet rs = stmt.executeQuery(sql);
        int rowcount = 0;
        int result = 0;

        while (rs.next()) {
            rowcount += 1;
            result = rs.getInt("qcm_id");
        }
        connection.close();
        if(rowcount == 0){return 0;}
        if(rowcount > 1){return  -1;}
        else{return result;}
    }

    public static String getPath(int id) throws SQLException, ClassNotFoundException {

        Connection connection = getMySQLConnection();
        Statement stmt = connection.createStatement();


        String sql = "Select path from QCM where qcm_id="+id;

        ResultSet rs = stmt.executeQuery(sql);
        int rowcount = 0;
        String result = "";

        while (rs.next()) {
            rowcount += 1;
            result = rs.getString("path");
        }
        connection.close();
        if(rowcount == 0){return "1";}
        if(rowcount > 1){return  "2";}
        else{return result;}
    }
}
