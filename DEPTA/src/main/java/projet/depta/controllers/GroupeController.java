package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.depta.entities.Classe;
import projet.depta.entities.Groupe;
import projet.depta.services.GroupeServices;

import java.util.List;

@RestController
public class GroupeController {

    @Autowired
    GroupeServices groupeServices;

    @GetMapping("/groupes/{id}")
    public List<Groupe> getGroupes(@PathVariable int id){
        return groupeServices.getGroupes(id);
    }

    @GetMapping("/groupes/{idclasse}/{iduser}")
    public List<Groupe> getClassesFromUser(@PathVariable int idclasse, @PathVariable int iduser){
        return groupeServices.getGroupesFromUser(idclasse,iduser);
    }

    @PutMapping("/groupe/{id}")
    public Groupe putGroupe(@PathVariable int id, @RequestBody Groupe groupe){
        return groupeServices.putGroupe(id,groupe);
    }
}
