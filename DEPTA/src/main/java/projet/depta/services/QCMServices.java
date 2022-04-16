package projet.depta.services;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import projet.depta.entities.QCM;
import projet.depta.entities.User;
import projet.depta.repositories.QCMRepository;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QCMServices {
    @Autowired
    QCMRepository repository;


    public List<QCM> getAllQCM(int idCreateur){
        return new ArrayList<>(repository.findByIdcreateur(idCreateur));
    }

    public static void newQCM(QCM qcm){
        System.out.println(qcm.toTex());
    }

    public static void editQCM(){

    }
    public String generateQCM(long id){
        Optional<QCM> qcm = repository.findById(id);
        if(qcm.isEmpty()){
            return "Unknow QCM or missing permission";
        }
        if(BashServices.generateArborescence(((Integer)qcm.get().getIdcreateur()).toString(),qcm.get().getId().toString()) && BashServices.generateCopies(qcm.get())){
            return "krapo.me/MC-PDF/"+qcm.get().getId()+"catalog.pdf";
        }
        return "";
    }

    public Boolean deleteQCM(long id) {
        Optional<QCM> qcm = repository.findById(id);
        if(qcm.isEmpty()){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public Long save(QCM qcm) {
        return repository.save(qcm).getId();
    }

    public Long updateQCM(QCM qcm) {
        return repository.save(qcm).getId();
    }

    public QCM getQCM(long id) {
        return repository.findById(id).orElse(null);
    }
}
