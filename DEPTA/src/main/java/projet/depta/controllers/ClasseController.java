package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.depta.entities.Classe;
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
}
