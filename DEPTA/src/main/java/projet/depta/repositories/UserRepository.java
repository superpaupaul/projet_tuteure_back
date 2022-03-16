package projet.depta.repositories;

import org.springframework.data.repository.CrudRepository;
import projet.depta.entities.User;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Long> {
}
