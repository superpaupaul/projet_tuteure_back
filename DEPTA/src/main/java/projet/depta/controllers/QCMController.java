package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projet.depta.entities.*;
import projet.depta.repositories.QCMRepository;
import projet.depta.repositories.QuestionRepository;
import projet.depta.repositories.UserRepository;
import projet.depta.services.QCMServices;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PostUpdate;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@RestController
public class QCMController {

    @Autowired
    QCMServices qcmServices;

    //Ok
    @PostMapping("/qcm")
    public Long newQCM(@RequestBody QCM qcm){
        return qcmServices.save(qcm);
    }

    //Ok
    @PutMapping("/qcm")
    @PreAuthorize("#qcm.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public Long updateQCM(@RequestBody QCM qcm){
        return qcmServices.updateQCM(qcm);
    }

    //Ok
    @GetMapping("/qcm/{id}")
    @PostAuthorize("returnObject.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    QCM getQCM(@PathVariable int id){
        return qcmServices.getQCM((long)id);
    }

    //Ok
    @DeleteMapping("/qcm/{id}")
    @PreAuthorize("#qcm.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public Boolean deleteQCM(@PathVariable int id, @RequestBody QCM qcm){
        return qcmServices.deleteQCM(id,qcm);
    }

    //Ok
    @GetMapping("/qcms/{id}")
    @PostAuthorize("returnObject.get(1).idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    List<QCM> getAllQCM(@PathVariable int id){
        return qcmServices.getAllQCM(id);
    }

    //Ok
    @PostMapping("/qcm/{id}/generate")
    @PostAuthorize("returnObject.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public QCM generateQCM(@PathVariable int id){
        return qcmServices.generateQCM(id);
    }


    //Ok
    @PostMapping("/qcm/{id}/note")
    @PostAuthorize("returnObject.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public QCM generateNotesQCM(@PathVariable int id){
        return qcmServices.generateNotesQCM(id);
    }

    @PostMapping("/qcm/{id}/uploadcopies")
    @PostAuthorize("returnObject.idcreateur == authentication.principal.id or authentication.principal.isAdmin")
    public QCM uploadFile(
            @RequestParam("files") MultipartFile[] multipartFile, @PathVariable int id)
            throws IOException {
        QCM qcm  = qcmServices.getQCM(id);
        if(qcm == null){return null;}
        for(int i = 0 ; i < multipartFile.length ; i++){
            String fileName = StringUtils.cleanPath(multipartFile[i].getOriginalFilename());
            long size = multipartFile[i].getSize();
            qcmServices.saveCorrection(fileName, multipartFile[i],qcm);
        }


        return qcm;
    }




    public Boolean editQCM(QCM qcm){
        return true;
    }

    public Boolean replaceQCM(QCM qcm){
        return true;
    }


    public Boolean postAnswers(Long id){
        return true;
    }

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QCMRepository qcmRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    @ResponseBody
    @RequestMapping("/")
    public QCM home() {
        HashSet<Reponse> reponses1 = new HashSet<Reponse>();
        reponses1.add(new Reponse(true,"A"));
        reponses1.add(new Reponse(false,"B"));
        reponses1.add(new Reponse(false,"C"));
        reponses1.add(new Reponse(false,"D"));

        HashSet<Option> options1 = new HashSet<Option>();
        ArrayList<Object> bareme = new ArrayList();
        bareme.add(5);
        bareme.add(0);
        bareme.add(-1);
        bareme.add(-1);
        options1.add(new Option(TypeOption.BAREME,bareme));
        Question question1 = new Question("Quelle lettre est la première de l'alphabet", TypeDeQuestion.UNIQUE, reponses1, new Options(options1, TypeDeQuestion.UNIQUE),"Lettres");
        System.out.println(question1.toTex());
        System.out.println("question1.toTex()");

        HashSet<Reponse> reponses2 = new HashSet<Reponse>();
        reponses2.add(new Reponse(true,"A,B"));
        reponses2.add(new Reponse(true,"B,C"));
        reponses2.add(new Reponse(false,"C,A"));
        reponses2.add(new Reponse(false,"D,1"));

        HashSet<Option> options2 = new HashSet<Option>();
        options2.add(new Option(TypeOption.POINTS,3.0));
        options2.add(new Option(TypeOption.COEFFICIENTMAUVAISEREPONSE,0.5));
        Question question2 = new Question("Quelles lettres se suivent ?", TypeDeQuestion.MULTIPLE,reponses2,new Options(options2,TypeDeQuestion.MULTIPLE),"Lettres");
        System.out.println(question2.toTex());

        HashSet<Option> options3 = new HashSet<Option>();
        options3.add(new Option(TypeOption.POINTS,3.0));
        options3.add(new Option(TypeOption.BONNEREPONSE,26.0));
        options3.add(new Option(TypeOption.NBDIGITS,2));
        options3.add(new Option(TypeOption.SIGNE,true));
        options3.add(new Option(TypeOption.DECIMAUX,0));
        options3.add(new Option(TypeOption.PTSMAUVAISEREPONSE,0.0));

        Question question3 = new Question("Combien de lettres compte l'alphabet ?", TypeDeQuestion.NUMERIQUE,null,new Options(options3,TypeDeQuestion.NUMERIQUE),"Alphabet");
        System.out.println(question3.toTex());



        HashSet<Option> options4 = new HashSet<Option>();
        options4.add(new Option(TypeOption.POINTS,3.0));
        ArrayList<Object> nomsqouvertes = new ArrayList();
        nomsqouvertes.add("AB");
        nomsqouvertes.add("B");
        nomsqouvertes.add("TB");
        options4.add(new Option(TypeOption.LISTENOMSQOUVERTE,nomsqouvertes));
        ArrayList<Object> pointsqouvertes = new ArrayList();
        pointsqouvertes.add(0.5);
        pointsqouvertes.add(1);
        pointsqouvertes.add(2);
        options4.add(new Option(TypeOption.LISTEPOINTSQOUVERTE,pointsqouvertes));
        options4.add(new Option(TypeOption.NBLIGNES,5));

        Question question4 = new Question("Racontez l'histoire de l'alphabet.",TypeDeQuestion.OUVERTE,null, new Options(options4,TypeDeQuestion.OUVERTE),"Alphabet");
        System.out.println(question4.toTex());


        ArrayList<Question> questions= new ArrayList<Question>();
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        userRepository.save(new User("nom","prenom","mdp",true,new ArrayList<Groupe>()));
        QCM qcm = new QCM("QCM 1","QCM SUR L'ALPHABET",questions,true,1);
        System.out.println(qcm.toTex());
        qcmRepository.save(qcm);

        return qcm;
        //return questionRepository.findByTypeDeQuestion(TypeDeQuestion.UNIQUE);
    }*/

    /*
    @ResponseBody
    @RequestMapping("/question")
    public Question getQuestion() {
        return questionRepository.findById(7);
    }

    @PostMapping("/addquestion")
    public void generateQCM(@RequestBody Question question){
        System.out.println(question.getIntitule());
        questionRepository.save(question);
    }

    @PostMapping("/user")
    public void add(){
        Groupe groupe1 = new Groupe();
        Groupe groupe2 = new Groupe();
        groupe1.setNomDuGroupe("Groupe 1");
        List<Etudiant> etudiants1 = new ArrayList<Etudiant>();

        Etudiant etudiant1_1 = new Etudiant();
        etudiant1_1.setNom("etudiant1_1");
        etudiant1_1.setPrenom("etudiant1_1");

        Etudiant etudiant1_2 = new Etudiant();
        etudiant1_2.setNom("etudiant1_2");
        etudiant1_2.setPrenom("etudiant1_2");

        etudiants1.add(etudiant1_1);
        etudiants1.add(etudiant1_2);

        groupe1.setEtudiants(etudiants1);
        groupe2.setNomDuGroupe("Groupe 2");

        List<Etudiant> etudiants2 = new ArrayList<Etudiant>();

        Etudiant etudiant2_1 = new Etudiant();

        etudiant2_1.setNom("etudiant2_1");
        etudiant2_1.setPrenom("etudiant2_1");

        Etudiant etudiant2_2 = new Etudiant();

        etudiant2_2.setNom("etudiant2_2");
        etudiant2_2.setPrenom("etudiant2_2");

        groupe2.setEtudiants(etudiants2);

        etudiants2.add(etudiant2_1);
        etudiants2.add(etudiant2_2);

        ArrayList<Groupe> grouplist = new ArrayList<Groupe>();
        grouplist.add(groupe1);
        grouplist.add(groupe2);

        User user = new User();
        user.setGroupes(grouplist);
        user.setMdp("mdp");
        user.setNom("Nom");
        user.setPrenom("Prenom");
        userRepository.save(user);
    }*/

}
