package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.Question;
import projet.depta.entities.TypeDeQuestion;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    @Override
    Optional<Question> findById(Long aLong);
}
