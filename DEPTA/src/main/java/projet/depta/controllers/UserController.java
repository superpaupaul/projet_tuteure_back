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

    @PreAuthorize("authentication.principal.isAdmin")
    @PostMapping("/user")
    public Long newUser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServices.newUser(user);
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @PutMapping("/user")
    public Long editUser(@RequestBody User user){
        return userServices.editUser(user);
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @PutMapping("/user/resetpassword")
    public Long resetpassword(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userServices.resetPassword(user);
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @GetMapping("/user/{id}")
    public User getUser(@PathVariable String id){
        try{
            User toreturn = userServices.getById(Integer.parseInt(id));
            if(toreturn != null){
                toreturn.setPassword("");
            }
            return toreturn;
        }
        catch (NumberFormatException nfe){
            User toreturn = userServices.getByUsername(id);
            if(toreturn != null){
                toreturn.setPassword("");
            }
            return toreturn;
        }
    }

    @PreAuthorize("authentication.principal.isAdmin")
    @DeleteMapping("/user/{id}")
    public Boolean removeUser(@PathVariable String id){
        return userServices.removeUser(id);
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
