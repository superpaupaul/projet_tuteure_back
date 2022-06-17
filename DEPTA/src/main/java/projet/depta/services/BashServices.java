package projet.depta.services;

import org.apache.tomcat.jni.Proc;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import projet.depta.entities.QCM;

import java.io.*;

@Service
public class BashServices {

    public static Boolean generateArborescence(String userid, String QCMID){
        Process process;
        try {
            process = Runtime.getRuntime().exec("/home/DEPTA/DATA/new-project.sh " + userid + " " + QCMID);
            process.waitFor();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean cleanCopies(QCM qcm){
        Process process;
        try {
            process = Runtime.getRuntime().exec("rm -r /home/DEPTA/DATA/" + qcm.getIdcreateur() + "/" + qcm.getId() + "/scans/*");
            process.waitFor();
            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    @PreAuthorize("#qcm.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public static Boolean generateCopies(QCM qcm){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/DEPTA/DATA/genbin/tex.tex"));
            writer.write(qcm.toTex());
            writer.close();

            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";
            Process proc = Runtime.getRuntime().exec("auto-multiple-choice prepare --mode s --prefix "+AMCProjectPath+" /home/DEPTA/DATA/genbin/tex.tex --out-sujet "+AMCProjectPath+"DOC-subject.pdf --out-corrige "+AMCProjectPath+"DOC-correction.pdf --data "+AMCProjectPath+"/data --out-calage "+AMCProjectPath+"/DOC-calage.xy");
            proc.waitFor();
            proc = Runtime.getRuntime().exec("auto-multiple-choice prepare --mode b --prefix "+AMCProjectPath+"  /home/DEPTA/DATA/genbin/tex.tex --data "+AMCProjectPath+"/data/");
            proc.waitFor();

            Runtime.getRuntime().exec("rm -r /home/DEPTA/DATA/genbin" );
            File output = new File(AMCProjectPath+"/catalog.pdf");
            if(!output.exists()){
                return false;
            }
            proc = Runtime.getRuntime().exec("cp /home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/catalog.pdf /var/www/html/MC-PDF/"+qcm.getId()+"catalog.pdf");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Boolean mepTexProject(QCM qcm){
        try {
            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";

            Process proc = Runtime.getRuntime().exec("auto-multiple-choice meptex --src "+AMCProjectPath+"DOC-calage.xy --data "+AMCProjectPath+"data/");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //On considère ici que les copies ont été importées dans le dossier scans du projet
    public static Boolean generateNotes(QCM qcm){
        try{
            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";
            //Process proc = Runtime.getRuntime().exec("ls -1 -d "+AMCProjectPath+"scans/* "/*> "+AMCProjectPath+"liste.txt"*/);
            /*Process proc = Runtime.getRuntime().exec( "ls -la");
            //nouveau commentaire
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(proc.getInputStream()));

            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(proc.getErrorStream()));

// Read the output from the command
            System.out.println("Here is the standard output of the command:\n");
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }

// Read any errors from the attempted command
            System.out.println("Here is the standard error of the command (if any):\n");
            while ((s = stdError.readLine()) != null) {
                System.out.println(s);
            }
            proc.waitFor();*/
            Process proc = Runtime.getRuntime().exec("/home/DEPTA/DATA/get-scans.sh " + qcm.getIdcreateur() + " " + qcm.getId());
            proc.waitFor();
            proc = Runtime.getRuntime().exec("auto-multiple-choice getimages --list "+AMCProjectPath+"liste.txt --copy-to "+AMCProjectPath+"scans/ ");
            proc.waitFor();
            proc = Runtime.getRuntime().exec("/home/DEPTA/DATA/analyse-scans.sh " + qcm.getIdcreateur() + " " + qcm.getId());
            proc.waitFor();
            proc = Runtime.getRuntime().exec("auto-multiple-choice note --data "+AMCProjectPath+"data --seuil 0.15");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

    //Liste des étudiants impliqués doit avoir été importée ici
    public static Boolean associateNotes(QCM qcm){
        try{
            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";

            Process proc = Runtime.getRuntime().exec("auto-multiple-choice association-auto --data "+AMCProjectPath+"data --notes-id etu --liste "+AMCProjectPath+"students-list.csv --liste-key no");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Boolean exportNotes(QCM qcm){
        try{
            String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";

            Process proc = Runtime.getRuntime().exec("auto-multiple-choice export --data "+AMCProjectPath+"data   --module csv   --fich-noms "+AMCProjectPath+"students-list.csv   --o "+AMCProjectPath+"output-note.csv");
            proc.waitFor();

            return true;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }



}
