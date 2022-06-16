package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.*;

import java.util.List;

public interface EtudiantRepository extends CrudRepository<Etudiant,Long> {

    List<Etudiant> findEtudiantsByClasse(String classe);
    List<Etudiant> findEtudiantsByGroupeAndClasse(String groupe,String classe);
    Etudiant findEtudiantBynOEtudiant(long nOEtudiant);
}
