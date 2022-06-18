package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projet.depta.entities.Classe;
import projet.depta.entities.QCM;
import projet.depta.services.ClasseService;

import java.util.List;

@RestController
public class ClasseController {

    @Autowired
    ClasseService classeService;

    @GetMapping("/classes")
    public List<Classe> getClasses(){
        return classeService.getClasses();
    }

    @GetMapping("/classes/{id}")
    public List<Classe> getClassesFromUser(@PathVariable int id){
        return classeService.getClassesFromUser(id);
    }

    @PutMapping("/classe/{id}")
    public Classe putClasse(@PathVariable int id, @RequestBody Classe classe){
        return classeService.putClasse(id,classe);
    }
}
