package projet.depta.services;

import org.springframework.stereotype.Service;
import projet.depta.entities.QCM;

import java.io.*;

@Service
public class BashServices {

    public static Boolean generateArborescence(String userid, String QCMID){
        Process process;
        try {
            //process = Runtime.getRuntime().exec("/Users/em/MC-Projects/new-project.sh " + userid + " " + QCMID);
            process = Runtime.getRuntime().exec("/home/DEPTA/DATA/new-project.sh " + userid + " " + QCMID);
            process.waitFor();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean generateCopies(QCM qcm){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/DEPTA/DATA/genbin/tex.tex"));
            //BufferedWriter writer = new BufferedWriter(new FileWriter("/Users/em/MC-Projects/genbin/tex.tex"));
            writer.write(qcm.toTex());
            writer.close();

            //String AMCProjectPath = "/Users/em/MC-Projects/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";
            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";
            System.out.println(AMCProjectPath);
            //Process proc = Runtime.getRuntime().exec("auto-multiple-choice prepare --mode s --prefix "+AMCProjectPath+" /Users/em/MC-Projects/genbin/tex.tex --out-sujet "+AMCProjectPath+"DOC-subject.pdf --out-corrige "+AMCProjectPath+"DOC-correction.pdf --data "+AMCProjectPath+"/data --out-calage "+AMCProjectPath+"DOC-calage.xy");
            Process proc = Runtime.getRuntime().exec("auto-multiple-choice prepare --mode s --prefix "+AMCProjectPath+" /home/DEPTA/DATA/genbin/tex.tex --out-sujet "+AMCProjectPath+"DOC-subject.pdf --out-corrige "+AMCProjectPath+"DOC-correction.pdf --data "+AMCProjectPath+"/data --out-calage "+AMCProjectPath+"DOC-calage.xy");
            proc.waitFor();


            //Runtime.getRuntime().exec("rm -r /Users/em/MC-Projects/genbin" );
            Runtime.getRuntime().exec("rm -r /home/DEPTA/DATA/genbin" );
            File output = new File(AMCProjectPath+"/catalog.pdf");
            if(!output.exists()){
                return false;
            }
            proc = Runtime.getRuntime().exec("cp /home/DEPTA/DATA/"qcm.getIdcreateur()+"/"+qcm.getId()+"/catalog.pdf /var/www/html/MC-PDF/"+qcm.getId()+"catalog.pdf");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
