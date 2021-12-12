package com.example.demo.bashcommands;

import com.example.demo.entities.QCM;

import java.io.*;

public class bashNewQCM {

    public static Boolean generateArborescence(String QCMID){
        Process process;
        try {
            process = Runtime.getRuntime().exec("/Users/em/MC-Projects/new-project.sh "+QCMID);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean generateCopies(QCM qcm){


        Process process;
        try {



            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/em/MC-Projects/genbin/tex.tex"));
            writer.write(qcm.toTex());

            writer.close();

            String AMCProjectPath = "/Users/em/MC-Projects/"+qcm.getTitre()+"/";
            process = Runtime.getRuntime().exec("auto-multiple-choice prepare --mode s --prefix "+AMCProjectPath+" /Users/em/MC-Projects/genbin/tex.tex --out-sujet "+AMCProjectPath+"DOC-subject.pdf --out-corrige "+AMCProjectPath+"DOC-correction.pdf --data "+AMCProjectPath+"/data --out-calage "+AMCProjectPath+"DOC-calage.xy");

            process = Runtime.getRuntime().exec("rm /Users/em/MC-Projects/genbin/tex.tex");

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
