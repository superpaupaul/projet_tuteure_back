package com.example.demo.controllers;

import com.example.demo.bashcommands.bashNewQCM;
import com.example.demo.entities.QCM;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static database.JDBCUtils.getMySQLConnection;

@RestController
public class APIController {

    @PostMapping("/api/v1/generateQCM")
    public QCM generateQCM(@RequestBody QCM qcm) throws SQLException, ClassNotFoundException {
        qcm.regenerateSujetsQuestions();
        bashNewQCM.generateArborescence(qcm.getTitre());
        bashNewQCM.generateCopies(qcm);



        Connection connection = getMySQLConnection();
        Statement statement = connection.createStatement();

        String sql = "insert into QCM(path,prof_id) values ('/Users/em/MC-Projects/"+qcm.getTitre()+"/DOC-Subject.pdf',14)";

        statement.executeUpdate(sql);
        connection.close();


        return qcm;
    }


    @GetMapping("/api/v1/pdf/subject/{titre}")
    public String getPDF(@PathVariable String titre) throws SQLException, ClassNotFoundException {


        Connection connection = getMySQLConnection();
        Statement statement = connection.createStatement();

        String sql = "select path from qcm where qcm_id=4";

        ResultSet rs = statement.executeQuery(sql);

        String result = "";
        while(rs.next()){
            result = rs.getString("path");
        }

        connection.close();
        return result;
    }
}
