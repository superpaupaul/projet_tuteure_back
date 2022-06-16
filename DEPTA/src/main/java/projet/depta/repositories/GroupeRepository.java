package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.Etudiant;
import projet.depta.entities.Groupe;
import projet.depta.entities.QCM;
import projet.depta.entities.Question;

import java.util.List;
import java.util.Optional;

public interface GroupeRepository extends CrudRepository<Groupe,Long> {
    Optional<Groupe> findByNomGroupe(String nomgroupe);
}
