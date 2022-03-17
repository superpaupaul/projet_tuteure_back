package projet.depta.services;

import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projet.depta.entities.User;
import projet.depta.repositories.UserRepository;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Service
public class UserServices {

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
            System.out.println(user.getGroupes());
            Pattern p = Pattern.compile("^(.+)@(.+)$");
            Matcher m = p.matcher(user.getEmail() );
            if(m.find()){
                if(repository.findByEmail(user.getEmail()).size() != 0 && repository.findByIdentifiant(user.getEmail()).size() != 0){
                    return (long) -3;
                }
                if(user.getIsAdmin()){
                    return (long)-4;
                }
                return repository.save(user).getId();
            }
            else if(user.getEmail() == ""){
                return 0L;
            }
            else{return (long) -2;}
        }
        return (long) -1;
    }

    public String connect(String identifiant, String mdp){
        if(repository.findByIdentifiantAndMdp(identifiant, mdp).isPresent()){
            return Hashing.sha256()
                    .hashString(identifiant+new Date(), StandardCharsets.UTF_8)
                    .toString();
        }
        else{
            return "-1";
        }
    }
}
