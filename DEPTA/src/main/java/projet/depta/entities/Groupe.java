package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="createur")
    private long idcreateur;

    @Column(name="nomgroupe")
    private String nomDuGroupe;

    @Column(name="etudiants")
    @OneToMany(cascade=CascadeType.ALL)
    private List<Etudiant> etudiants;

    public Groupe(){}
}
