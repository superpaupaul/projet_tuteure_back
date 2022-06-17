package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.Classe;

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
}
