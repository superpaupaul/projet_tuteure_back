package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.Classe;
import projet.depta.entities.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClasseService {

    @Autowired
    ClasseRepository classeRepository;

    public List<Classe> getClasses(){
        return (List<Classe>) classeRepository.findAll();
    }

    public Classe getClasse(long id) {
        return classeRepository.findById(id).orElse(null);
    }

    public Classe putClasse(long id,Classe classe) {
        if(classe.getId() != id){
            return null;
        }
        Classe localclasse = getClasse(classe.getId());
        for(User prof : localclasse.getProfesseurs()){
            if(!classe.getProfesseurs().contains(prof)){
                return null;
            }
        }

        return classeRepository.save(classe);
    }

    public List<Classe> getClassesFromUser(int id) {
        List<Classe> allClasses = getClasses();
        List<Classe> classesToReturn = new ArrayList<>();
        for(Classe classe : allClasses){
            for(User professeur : classe.getProfesseurs()){
                if(professeur.getId() == id){ classesToReturn.add(classe); break;}
            }
        }
        return classesToReturn;
    }
}
