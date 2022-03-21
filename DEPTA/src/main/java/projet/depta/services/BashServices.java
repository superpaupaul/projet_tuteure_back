package projet.depta.services;

import projet.depta.entities.QCM;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BashServices {

    public static Boolean generateArborescence(String userid, String QCMID){
        Process process;
        try {
            process = Runtime.getRuntime().exec("/Users/em/MC-Projects/new-project.sh " + userid + " " + QCMID);
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
            auto-multiple-choice prepare --mode s --prefix /Users/em/MC-Projects/10 /Users/em/MC-Projects/genbin/tex.tex --out-sujet /Users/em/MC-Projects/10/DOC-subject.pdf --out-corrige /Users/em/MC-Projects/10/DOC-correction.pdf --data  /Users/em/MC-Projects/10/data --out-calage /Users/em/MC-Projects/10/DOC-calage.xy
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
