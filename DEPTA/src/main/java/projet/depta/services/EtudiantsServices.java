package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.Classe;
import projet.depta.entities.Etudiant;
import projet.depta.entities.Groupe;
import projet.depta.entities.User;
import projet.depta.repositories.EtudiantRepository;
import projet.depta.repositories.GroupeRepository;
import projet.depta.repositories.QCMRepository;
import projet.depta.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EtudiantsServices {
    @Autowired
    EtudiantRepository etudiantRepository;

    @Autowired
    ClasseRepository classeRepository;

    @Autowired
    GroupeRepository groupeRepository;

    @Autowired
    UserRepository userRepository;

    public Boolean userHasRightsOnEtudiant(long userId,Etudiant etudiant){
        Optional<Classe> classe = classeRepository.findByNomClasse(etudiant.getClasse());
        Optional<Groupe> groupe = groupeRepository.findByNomGroupe(etudiant.getClasse());
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            return false;
        }
        if(classe.isPresent()){
            for(User professeur : classe.get().getProfesseurs()){
                if(user.get().equals(professeur)){
                    return true;
                }
            }
        }
        if(groupe.isPresent()){
            for(User professeur : groupe.get().getProfesseurs()){
                if(user.get().equals(professeur)){
                    return true;
                }
            }
        }
        return false;
    }

    public Etudiant getEtudiant(long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public List<Etudiant> getEtudiantsByClasse(long classeId){
        Optional<Classe> classe = classeRepository.findById(classeId);
        if(classe.isPresent()){
            return this.etudiantRepository.findEtudiantsByClasse(classe.get().getNomClasse());
        }
        return null;
    }

    public List<Etudiant> getEtudiantsByGroupeAndClasse(long classeId, long groupeId){
        Optional<Groupe> groupe = groupeRepository.findById(groupeId);
        Optional<Classe> classe = classeRepository.findById(classeId);
        if(groupe.isPresent() && classe.isPresent()){
            return this.etudiantRepository.findEtudiantsByGroupeAndClasse(groupe.get().getNomGroupe(),classe.get().getNomClasse());
        }
        return null;
    }

    public List<Etudiant> getEtudiantsByUser(long id) {
        List<Etudiant> etudiants = new ArrayList<>();
        for(Classe classe : classeRepository.findAll()){
            for(User professeur : classe.getProfesseurs()){
                if(id == professeur.getId()){
                    etudiants.addAll(getEtudiantsByClasse(classe.getId()));
                }
            }
        }
        for(Groupe groupe : groupeRepository.findAll()){
            for(User professeur : groupe.getProfesseurs()){
                if(id == professeur.getId()){
                    etudiants.addAll(getEtudiantsByClasse(groupe.getId()));
                }
            }
        }
        return etudiants;
    }
}
