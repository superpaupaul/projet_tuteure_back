package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.Notes;
import projet.depta.entities.Question;

import java.util.List;

public interface NotesRepository extends CrudRepository<Notes, Long> {
}
