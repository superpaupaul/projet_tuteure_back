package com.example.demo.controllers;

import com.example.demo.bashcommands.bashNewQCM;
import com.example.demo.entities.ProfessorsQCM;
import com.example.demo.entities.QCM;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static database.JDBCRequests.*;
import static database.JDBCUtils.getMySQLConnection;

@RestController
public class APIController {

    @PostMapping("/api/v1/generateQCM")
    public QCM generateQCM(@RequestBody ProfessorsQCM profQCM) throws SQLException, ClassNotFoundException {
        profQCM.getQcm().regenerateSujetsQuestions();
        bashNewQCM.generateArborescence(profQCM.getQcm().getTitre());
        bashNewQCM.generateCopies(profQCM.getQcm());

        newQCMInDB(profQCM.getQcm(),profQCM.getProf_id());

        return profQCM.getQcm();
    }


    @GetMapping("/api/v1/pdf/subject/{titre}")
    public String getPDFPath(@RequestParam String titre) throws SQLException, ClassNotFoundException {
        String result = getPath(getId(titre,14));
        try{
            if(Integer.parseInt(result) ==1){
                return "No MCQ for this id";
            }
            else{
                return "Too much MCQ for this id";
            }
        }
        catch (NumberFormatException e){
            return result+"/DOC-Subject.pdf";
        }
    }
}
