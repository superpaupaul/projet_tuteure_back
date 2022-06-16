package projet.depta.services;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.Classe;
import projet.depta.entities.Etudiant;

import java.util.Optional;

public interface ClasseRepository extends CrudRepository<Classe,Long> {
    Optional<Classe> findByNomClasse(String nomclasse);
}
