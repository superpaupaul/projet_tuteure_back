package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.*;

import java.util.List;
import java.util.Optional;

public interface GroupeRepository extends CrudRepository<Groupe,Long> {
    Optional<Groupe> findByNomGroupe(String nomgroupe);

    List<Groupe> findByClasse(Classe classe);
}
