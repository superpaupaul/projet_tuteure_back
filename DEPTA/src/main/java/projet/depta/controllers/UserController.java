package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
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


    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/user")
    public Long newUser(@RequestBody User user){
        System.out.println(user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServices.newUser(user);
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){return userServices.getById(id);}

    @PreAuthorize("authentication.principal.isAdmin")
    @GetMapping("/user/{username}")
    public User getUser(@PathVariable String username){return userServices.getByUsername(username);}


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
