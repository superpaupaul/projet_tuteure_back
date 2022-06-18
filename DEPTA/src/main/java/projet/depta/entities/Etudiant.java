package projet.depta.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nom")
    private String nom;

    @Column(name="prenom")
    private String prenom;

    @Column(name="noetudiant", unique = true)
    private Long noetudiant;

    @Column(name = "classe")
    private String classe;

    @Column(name = "groupe")
    private String groupe;

    public Etudiant(){}
}
