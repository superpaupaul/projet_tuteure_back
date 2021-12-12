package com.example.demo.controllers;

import com.example.demo.bashcommands.bashNewQCM;
import com.example.demo.entities.QCM;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @PostMapping("/api/v1/generateQCM")
    public QCM generateQCM(@RequestBody QCM qcm){
        qcm.regenerateSujetsQuestions();
        bashNewQCM.generateArborescence(qcm.getTitre());
        bashNewQCM.generateCopies(qcm);
        return qcm;
    }


    @GetMapping("/api/v1/pdf/subject/{titre}")
    public String getPDF(@PathVariable String titre){
        return "/Users/em/MC-Projects/"+titre+"/DOC-Subject.pdf";
    }
}
