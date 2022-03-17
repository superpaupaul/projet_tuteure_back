package projet.depta.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/api/v1/user/newUser")
    public Long newUser(@RequestParam Long caller, @RequestBody User user){
        if(userServices.isAdmin(caller)){
            return userServices.newUser(user);
        }
        return (long)-1;
    }

    @PostMapping("/api/v1/")

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
