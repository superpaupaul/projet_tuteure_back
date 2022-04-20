package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import projet.depta.entities.QCM;
import projet.depta.entities.User;
import projet.depta.services.UserServices;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PreAuthorize("authentication.principal.isAdmin")
    @PostMapping("/user")
    public Long newUser(@RequestBody User user){
        return userServices.newUser(user);
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @DeleteMapping("/user/{id}")
    public Boolean removeUser(@PathVariable int id){
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
