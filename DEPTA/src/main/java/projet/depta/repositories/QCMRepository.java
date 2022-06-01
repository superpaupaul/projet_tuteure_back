package projet.depta.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import projet.depta.entities.QCM;

import java.util.List;
import java.util.Optional;

@Repository
public interface QCMRepository extends JpaRepository<QCM,Integer> {
    Optional<QCM> findById(Long aLong);

    List<QCM> findByIdcreateur(int aLong);
}
