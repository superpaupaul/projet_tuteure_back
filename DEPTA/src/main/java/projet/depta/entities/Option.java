package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name="InOption")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="typeoption")
    private TypeOption typeOption;

    @Column(name="valeur")
    private String valeur;

    public Option(TypeOption typeOption, Object valeur){
        this.typeOption = typeOption;
        if(typeOption == TypeOption.BAREME || typeOption == TypeOption.LISTEPOINTSQOUVERTE || typeOption == TypeOption.LISTENOMSQOUVERTE){
            this.valeur = ((ArrayList)valeur).toString();
        }
        else if (typeOption == TypeOption.NBDIGITS || typeOption == TypeOption.DECIMAUX || typeOption == TypeOption.NBLIGNES){
            this.valeur = ((Integer)valeur).toString();
        }
        else if(typeOption == TypeOption.COEFFICIENTMAUVAISEREPONSE || typeOption == TypeOption.POINTS || typeOption == TypeOption.PTSMAUVAISEREPONSE || typeOption == TypeOption.BONNEREPONSE){
            this.valeur = ((Double)valeur).toString();
        }
        else if(typeOption == TypeOption.SIGNE){
            this.valeur = ((Boolean)valeur).toString();
        }
    }

    public Option() {}
}
