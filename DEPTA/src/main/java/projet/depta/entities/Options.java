package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="Options")
public class Options {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "optionsset",nullable = false)
    @OneToMany(cascade=CascadeType.ALL)
    private Set<Option> optionsset;


    @Column(name = "typeDeQuestion",nullable = false)
    private TypeDeQuestion typeDeQuestion;

    public Options(Set<Option> options, TypeDeQuestion typeDeQuestion){
        this.optionsset = options;
        this.typeDeQuestion = typeDeQuestion;
    };

    public Options() {}

}
