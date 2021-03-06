package projet.depta.services;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projet.depta.entities.Option;
import projet.depta.entities.User;
import projet.depta.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    public Boolean isAdmin(Long id){
        if(repository.existsById(id)){
            return repository.findById(id).get().getIsAdmin();
        }
        else{return false;}
    }

    /**
     * @return -1 : Aucun champs email; -2 Email incorrect ; -3 Email déjà existant ; -4 Tentative de création d'admin
     */
    public Long newUser(User user) {
        if(user.getEmail() != null ){
            Pattern p = Pattern.compile("^(.+)@(.+)$");
            Matcher m = p.matcher(user.getEmail() );
            if(m.find()){
                if(repository.findByEmail(user.getEmail()).size() != 0 || repository.findByUsername(user.getUsername()).size() != 0){
                    return (long) -3;
                }
                if(user.getIsAdmin()){
                    return (long)-4;
                }
                return repository.save(user).getId();
            }
            else if(Objects.equals(user.getEmail(), "")){
                return (long)-5;
            }
            else{return (long) -2;}
        }
        return (long) -1;
    }

    public long editUser(User user){
        if(user.getEmail() != null ){
            Pattern p = Pattern.compile("^(.+)@(.+)$");
            Matcher m = p.matcher(user.getEmail() );
            if(m.find()){
                User userLocal = this.getById(user.getId());
                if( userLocal!= null){
                    user.setPassword(userLocal.getPassword());
                    System.out.println("User username :" + user.getUsername());
                    System.out.println("User password :" + user.getPassword());
                    return repository.save(user).getId();
                }
            }
            else if(Objects.equals(user.getEmail(), "")) return -3;
            else return -2;
        }
        return -1;
    }

    public User getByUsername(String username){
        List<User> userloaded =  repository.findByUsername(username);
        if(userloaded.size() ==1){
            return repository.findByUsername(username).get(0);
        }
        return null;
    }

    public User getById(long id){
        return repository.findById(id).orElse(null);
    }

    public Boolean removeUser(String id){
        System.out.println(id);
        try{
            User user = getById(Integer.parseInt(id));
            if(user != null){
                repository.delete(user);
                return true;
            }
        }
        catch (NumberFormatException nfe){
            User user = getByUsername(id);
            if(user != null){
                repository.delete(user);
                return true;
            }
        }
        return false;
    }

    public Long resetPassword(User user) {
        return repository.save(user).getId();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("1");
        if(repository.findByUsername(username).size() ==1){
            return repository.findByUsername(username).get(0);
        }
        throw new UsernameNotFoundException("Username not found");
    }
}
