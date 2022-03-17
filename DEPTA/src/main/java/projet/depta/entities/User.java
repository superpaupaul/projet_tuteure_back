package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="identifiant")
    private String identifiant;

    @Column(name="email")
    private String email;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="mdp")
    private String mdp;

    @Column(name="isAdmin")
    private Boolean isAdmin;


    @Column(name="groupes")
    @OneToMany(cascade = CascadeType.ALL)
    private List<Groupe> groupes;

    public User(){}

    public User(String nom, String prenom, String mdp, Boolean isAdmin, ArrayList<Groupe> groupes){
        this.nom=nom;this.prenom=prenom;this.mdp=mdp;this.isAdmin=isAdmin;this.groupes=groupes;
    }
}
