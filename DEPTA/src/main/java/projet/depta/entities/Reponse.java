package projet.depta.entities;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="Reponse")
public class Reponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="isGood")
    private Boolean isGood;

    @Column(name="texte")
    private String texte;


    public Reponse(Boolean isGood,String texte){
        this.texte=texte;
        this.isGood = isGood;
    }

    public Reponse() {
    }
}
