package projet.depta.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import projet.depta.entities.Groupe;
import projet.depta.entities.User;
import projet.depta.repositories.GroupeRepository;
import projet.depta.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupeServices {

    @Autowired
    GroupeRepository groupeRepository;

    @Autowired
    UserRepository userRepository;

    public List<Groupe> getByUserId(Long id) {
        List<Groupe> groupeList = groupeRepository.findByIdcreateur(id);
        return groupeList.isEmpty() ? null : groupeList;
    }

    public Groupe getById(Long id) {
        return groupeRepository.findById(id).orElse(null);
    }
}
