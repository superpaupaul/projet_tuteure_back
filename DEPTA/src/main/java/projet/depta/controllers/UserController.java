package projet.depta.controllers;

import org.springframework.web.bind.annotation.RestController;
import projet.depta.entities.User;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    public Boolean addUser(User caller, User user){
        return true;
    }

    public Boolean removeUser(User caller, User user){
        return true;

    }

    public Boolean checkUserConnection(User user){
        return true;

    }

    public User getUser(User caller, Long id){
        return new User();
    }

    public List<User> getUsers(User caller, User user){
        return new ArrayList<User>();
    }

    public Boolean resetPassword(User caller, User user, String newPassword){
        return true;

    }
}
