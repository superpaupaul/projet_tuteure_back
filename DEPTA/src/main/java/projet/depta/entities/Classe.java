package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Classe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nomClasse")
    private String nomClasse;

    @Column(name="professeurs")
    @ManyToMany(cascade= CascadeType.ALL)
    private List<User> professeurs;
}
