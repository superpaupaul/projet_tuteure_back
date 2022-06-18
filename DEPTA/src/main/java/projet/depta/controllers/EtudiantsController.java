package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import projet.depta.entities.Etudiant;
import projet.depta.entities.User;
import projet.depta.services.EtudiantsServices;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

@RestController
public class EtudiantsController {

    @Autowired
    EtudiantsServices etudiantsServices;

    Boolean hasRights = false;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


    @GetMapping("/etudiant/{id}")
    @PostAuthorize("authentication.principal.isAdmin or returnObject == null or returnObject.classe.professeurs.contains(authentication.principal) or returnObject.groupe.professeurs.contains(authentication.principal)")
    public Etudiant getEtudiant(@PathVariable int id){
        Etudiant etudiant = etudiantsServices.getEtudiant(id);
        return etudiant.getClasse() == null ? null : etudiant;
    }

    @GetMapping("/etudiants/byUser/{id}")
    public List<Etudiant> getEtudiantsByUser(@PathVariable int id){
        return etudiantsServices.getEtudiantsByUser(id);
    }

    @GetMapping("/etudiants/{idClasse}/{idGroupe}")
    public List<Etudiant> getEtudiantsByClasseAndGroupe(@PathVariable int idClasse, @PathVariable int idGroupe){
        List<Etudiant> etudiants = etudiantsServices.getEtudiantsByGroupeAndClasse(idClasse,idGroupe);
        /*for(Etudiant etudiant: etudiants){
            hasRights = etudiantsServices.userHasRightsOnEtudiant(userId,etudiant);
            if(!hasRights){return null;}
        }*/
        return etudiants;
    }

    @GetMapping("/etudiants/{idClasse}")
    public List<Etudiant> getEtudiantsByClasse(@PathVariable int idClasse){
        List<Etudiant> etudiants = etudiantsServices.getEtudiantsByClasse(idClasse);
        /*for(Etudiant etudiant: etudiants){
            hasRights = etudiantsServices.userHasRightsOnEtudiant(userId,etudiant);
            if(!hasRights){return null;}
        }*/
        return etudiants;
    }

    @PostMapping("/etudiant")
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant){
        return etudiantsServices.createEtudiant(etudiant);
    }
}
