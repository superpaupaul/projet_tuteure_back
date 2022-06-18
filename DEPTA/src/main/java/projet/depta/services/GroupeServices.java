package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import projet.depta.entities.Classe;
import projet.depta.entities.Etudiant;
import projet.depta.entities.Groupe;
import projet.depta.entities.User;
import projet.depta.repositories.GroupeRepository;
import projet.depta.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupeServices {

    @Autowired
    GroupeRepository groupeRepository;

    @Autowired
    ClasseService classeService;

    public List<Groupe> getGroupes(long id) {
        Classe classe = classeService.getClasse(id) == null ? null : classeService.getClasse(id);
        if(classe != null){
            return groupeRepository.findByClasse(classe);
        }
        return null;
    }


    private Groupe getGroupe(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }

    public Groupe putGroupe(int id, Groupe groupe) {
        if(groupe.getId() != id){
            return null;
        }
        Groupe localgroupe = getGroupe(groupe.getId());
        for(User prof : localgroupe.getProfesseurs()){
            if(!groupe.getProfesseurs().contains(prof)){
                return null;
            }
        }

        return groupeRepository.save(groupe);
    }

    public List<Groupe> getGroupesFromUser(int idclasse,int iduser) {
        List<Groupe> allGroupes = getGroupes(idclasse);
        List<Groupe> groupesToReturn = new ArrayList<>();
        for(Groupe groupe : allGroupes){
            for(User professeur : groupe.getProfesseurs()){
                if(professeur.getId() == iduser){ groupesToReturn.add(groupe); break;}
            }
        }
        return groupesToReturn;
    }

}
