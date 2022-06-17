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
}
