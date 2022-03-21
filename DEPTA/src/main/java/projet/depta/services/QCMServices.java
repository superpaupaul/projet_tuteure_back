package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.QCM;

@Service
public class QCMServices {

    @Autowired
    BashServices bashServices;

    public static void newQCM(QCM qcm){
        System.out.println(qcm.toTex());
    }
    public static void editQCM(){

    }
    public String GenerateQCM(QCM qcm){
        if(bashServices.generateArborescence(((Integer)qcm.getIdcreateur()).toString(),qcm.getId().toString()) && bashServices.generateCopies(qcm)){
            return "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/catalog.pdf";
        }
        return "";
    }
}
