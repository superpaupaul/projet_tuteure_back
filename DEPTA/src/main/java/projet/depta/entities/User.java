package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter

@Entity
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="identifiant")
    private String username;

    @Column(name="email")
    private String email;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="mdp")
    private String password;

    @Column(name="isAdmin")
    private Boolean isAdmin;


    @Column(name="groupes")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Groupe> groupes;

    public User(){}

    public User(String nom, String prenom, String mdp, Boolean isAdmin, ArrayList<Groupe> groupes){
        this.nom=nom;this.prenom=prenom;this.password=mdp;this.isAdmin=isAdmin;this.groupes=groupes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
