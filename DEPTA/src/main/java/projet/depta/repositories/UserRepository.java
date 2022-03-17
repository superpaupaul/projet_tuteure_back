package projet.depta.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    @Override
    Optional<User> findById(Long aLong);

    List<User> findByEmail(String email);

    List<User> findByIdentifiant(String identifiant);

    Optional<User> findByIdentifiantAndMdp(String identifiant, String mdp);

}
