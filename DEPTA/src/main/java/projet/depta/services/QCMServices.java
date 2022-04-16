package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.QCM;
import projet.depta.repositories.QCMRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QCMServices {

    @Autowired
    BashServices bashServices;

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
        if(!qcm.isPresent()){
            return "Unknow QCM";
        }
        if(bashServices.generateArborescence(((Integer)qcm.get().getIdcreateur()).toString(),qcm.get().getId().toString()) && bashServices.generateCopies(qcm.get())){
            return "krapo.me/MC-PDF/"+qcm.get().getId()+"catalog.pdf";
        }
        return "";
    }

    public Boolean deleteQCM(long id) {
        Optional<QCM> qcm = repository.findById(id);
        if(!qcm.isPresent()){
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
        Optional<QCM> qcm = repository.findById(id);
        if (!qcm.isPresent()) {
            return null;
        }
        return qcm.get();
    }
}
