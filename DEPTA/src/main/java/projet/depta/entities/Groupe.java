package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class Groupe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nomGroupe")
    private String nomGroupe;

    @Column(name="professeurs")
    @ManyToMany
    private List<User> professeurs;

    @JoinColumn(name = "classe")
    @OneToOne
    private Classe classe;

    public Groupe(){}
}
