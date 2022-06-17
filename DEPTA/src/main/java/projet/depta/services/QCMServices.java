package projet.depta.services;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projet.depta.entities.QCM;
import projet.depta.entities.User;
import projet.depta.repositories.QCMRepository;

import java.io.IOException;
import java.io.InputStream;
import java.net.Authenticator;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class QCMServices {
    @Autowired
    QCMRepository repository;


    public List<QCM> getAllQCM(int idCreateur){
        return new ArrayList<>(repository.findByIdcreateur(idCreateur));
    }

    public QCM generateQCM(long id){
        Optional<QCM> qcm = repository.findById(id);
        if(qcm.isEmpty()){
            return null;
        }
        System.out.println(qcm.get().toTex());
        if(BashServices.generateArborescence(((Integer)qcm.get().getIdcreateur()).toString(),qcm.get().getId().toString()) && BashServices.generateCopies(qcm.get())){
            return qcm.get();
        }
        return null;
    }

    public QCM generateNotesQCM(long id){
        Optional<QCM> qcm = repository.findById(id);
        if(qcm.isEmpty()){
            return null;
        }
        Boolean keepnoting = BashServices.mepTexProject(qcm.get())
        && BashServices.generateNotes(qcm.get())
        && BashServices.associateNotes(qcm.get())
        && BashServices.exportNotes(qcm.get());
        return keepnoting ? qcm.get() : null;
    }

    public Boolean deleteQCM(long id,QCM qcmrecu) {
        Optional<QCM> qcm = repository.findById(id);
        if(qcm.isEmpty() || !(qcm.get().compareTo(qcmrecu))){
            return false;
        }
        repository.deleteById(id);
        return true;
    }

    public Long save(QCM qcm) {
        return repository.save(qcm).getId();
    }

    public Long updateQCM(QCM qcmrecu) {
        Optional<QCM> qcm = repository.findById(qcmrecu.getId());
        if(qcm.isPresent() && Objects.equals(qcmrecu.getId(), qcm.get().getId()) && qcmrecu.getIdcreateur() == qcm.get().getIdcreateur()){
            return repository.save(qcmrecu).getId();
        }
        return null;
    }

    public QCM getQCM(long id) {
        return repository.findById(id).orElse(null);
    }

    public QCM saveCorrection(String fileName, MultipartFile multipartFile,QCM qcm) throws IOException {
        String AMCProjectPath = "/home/DEPTA/DATA/"+qcm.getIdcreateur()+"/"+qcm.getId()+"/";
        Path uploadPath = Paths.get(AMCProjectPath+"/scans");

        BashServices.cleanCopies(qcm);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            return null;
        }

        return qcm;
    }
}
