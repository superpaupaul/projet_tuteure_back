package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projet.depta.entities.Etudiant;
import projet.depta.entities.Groupe;
import projet.depta.services.GroupeServices;

import java.util.List;

@RestController
public class GroupesController {

    @Autowired
    GroupeServices groupeServices;

    @GetMapping("/groupes/{id}")
    @PreAuthorize("authentication.principal.isAdmin or authentication.principal.id == #id")
    public List<Groupe> getGroupes(@PathVariable("id") Long id){
        System.out.println(id);
        return groupeServices.getByUserId(id);
    }

    @GetMapping("/groupe/{id}")
    @PreAuthorize("authentication.principal.id == #idUser")
    public Groupe getGroupe(@PathVariable("id") Long id, @RequestBody int idUser){
        return groupeServices.getById(id);
    }

    /*
    public List<Etudiant> getEtudiants(Long id){

    }
    public List<Etudiant> getEtudiant(Long id){

    }*/
}
