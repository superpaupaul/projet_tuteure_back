package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.QCM;

import java.util.List;
import java.util.Optional;

public interface QCMRepository extends CrudRepository<QCM,Long> {
    @Override
    Optional<QCM> findById(Long aLong);

    List<QCM> findByIdcreateur(int aLong);
}
