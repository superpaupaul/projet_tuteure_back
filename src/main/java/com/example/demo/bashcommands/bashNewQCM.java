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


        try {



            BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/em/MC-Projects/genbin/tex.tex"));
            writer.write(qcm.toTex());

            writer.close();

            String AMCProjectPath = "/Users/em/MC-Projects/"+qcm.getTitre()+"/";
            Runtime.getRuntime().exec("auto-multiple-choice prepare --mode s --prefix "+AMCProjectPath+" /Users/em/MC-Projects/genbin/tex.tex --out-sujet "+AMCProjectPath+"DOC-subject.pdf --out-corrige "+AMCProjectPath+"DOC-correction.pdf --data "+AMCProjectPath+"/data --out-calage "+AMCProjectPath+"DOC-calage.xy");

            Thread.sleep(3000);

            File files[] = new File("/Users/em/MC-Projects/genbin/").listFiles();

            for(File file : files){
                Runtime.getRuntime().exec("rm " + file);
            }

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
