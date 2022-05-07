package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
@Entity
@Table(name="Question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "intitule",nullable = false)
    private String intitule;


    @Column(name = "typeDeQuestion",nullable = true)
    private TypeDeQuestion typeDeQuestion;


    @OneToMany(cascade=CascadeType.ALL)
    @Column(name = "reponses",nullable = false)
    private Set<Reponse> reponses;


    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "options",nullable = false)
    private Options options;

    @Column(name="categorie")
    private String categorie;


    public Question(){}


    public String toTex(){
        if(this.typeDeQuestion == TypeDeQuestion.UNIQUE){
            String texText = "\\element{"+this.getCategorie()+"}{\n\t" +
                    "\\begin{question}{"+this.getIntitule()+"}\\bareme{";
            for(Option option : this.getOptions().getOptionsset()){
                if(option.getTypeOption() == TypeOption.BAREME){
                    String[] bareme = option.getValeur().replace("[","").replace("]","").replace(" ","").split(",");
                    texText += "e="+bareme[0];
                    texText += ",b="+bareme[1];
                    texText += ",v="+bareme[2];
                    texText += ",m="+bareme[3];
                }
            }
            texText += "}\n" +
                    this.getIntitule() +"\n\t\t"+
                    "\\begin{choices}\n\t\t";
            for(Reponse reponse : reponses){
                if(reponse.getIsGood()){
                    texText+="\t\\correctchoice{"+reponse.getTexte()+"}\n\t\t";
                }
                else{
                    texText+="\t\\wrongchoice{"+reponse.getTexte()+"}\n\t\t";
                }
            }
            texText += "\\end{choices}\n\t"+
                    "\\end{question}\n" +
                    "}";
            return  texText;
        }
        if(this.typeDeQuestion == TypeDeQuestion.MULTIPLE){
            for(Option option : this.getOptions().getOptionsset()){
                if(option.getTypeOption() == TypeOption.PTSMAUVAISEREPONSE){
                    String ptsmauvaisereponse = option.getValeur();
                }
            }

            String texText = "\\element{"+this.getCategorie()+"}{\n\t" +
                    "\\begin{questionmult}"+this.getIntitule()+"\n\t\t\\bareme{formula=NBC-";

            for(Option option : this.getOptions().getOptionsset()){
                if(option.getTypeOption() == TypeOption.COEFFICIENTMAUVAISEREPONSE){
                    texText += option.getValeur().toString()+"*NMC}\n\t";
                }
            }

            texText += this.getIntitule() +"\n\t\t"+
            "\\begin{choices}\n\t\t";
            for(Reponse reponse : reponses){
                if(reponse.getIsGood()){
                    texText+="\t\\correctchoice{"+reponse.getTexte()+"}\n\t\t";
                }
                else{
                    texText+="\t\\wrongchoice{"+reponse.getTexte()+"}\n\t\t";
                }
            }

            texText += "\\end{choices}\n\t"+
                    "\\end{questionmult}\n" +
                    "}";
            return  texText;
        }
        if(this.typeDeQuestion == TypeDeQuestion.OUVERTE) {
            String texText = "\\element{" + this.getCategorie() + "}{\n\t" +
                    "\\begin{question}{" + this.getIntitule() + "}\n" +
                    this.getIntitule() + "\n\t\t" +
                    "\\AMCOpen{";

            for (Option option : this.getOptions().getOptionsset()) {
                if (option.getTypeOption() == TypeOption.NBLIGNES) {
                    texText += "lines=" + option.getValeur();
                }
            }


            texText += ",dots=true}{";

            String[] baremepoints = new String[0];
            String[] baremenoms = new String[0];
            for (Option option : this.getOptions().getOptionsset()) {
                if (option.getTypeOption() == TypeOption.LISTEPOINTSQOUVERTE) {
                    baremepoints = option.getValeur().replace("[", "").replace("]", "").replace(" ", "").split(",");
                }
                if (option.getTypeOption() == TypeOption.LISTENOMSQOUVERTE) {
                    baremenoms = option.getValeur().replace("[", "").replace("]", "").replace(" ", "").split(",");
                }
            }
            int i = 0;
            for (String point : baremepoints) {
                if (i != baremepoints.length - 1)
                    texText += "\\wrongchoice{" + baremenoms[i] + "}\\scoring{" + point + "}";
                else
                    texText += "\\correctchoice{" + baremenoms[i] + "}\\scoring{" + point + "}";

                i++;
            }
            texText += "}\n\t" +
                    "\\end{question}\n" +
                    "}";

            return texText;

        }
        if(this.typeDeQuestion == TypeDeQuestion.NUMERIQUE) {
            String bonnereponse = "";
            String nbdigits = "";
            String decimaux = "";
            String sign = "";
            String ptsmauvaisereponse = "";
            String points = "";
            for (Option option : this.getOptions().getOptionsset()) {
                if(option.getTypeOption() == TypeOption.BONNEREPONSE){
                    bonnereponse = option.getValeur();
                }
                if(option.getTypeOption() == TypeOption.NBDIGITS){
                    nbdigits = option.getValeur();
                }
                if(option.getTypeOption() == TypeOption.DECIMAUX){
                    decimaux = option.getValeur();
                }
                if(option.getTypeOption() == TypeOption.SIGNE){
                    sign = option.getValeur();
                }
                if(option.getTypeOption() == TypeOption.POINTS){
                    points = option.getValeur();
                }
                if(option.getTypeOption() == TypeOption.PTSMAUVAISEREPONSE){
                    ptsmauvaisereponse = option.getValeur();
                }
            }
            return"\\element{"+this.getCategorie()+"}{\n\t" +
                    "\\begin{questionmultx}{"+this.getIntitule()+"}\n\t"+
                    this.getIntitule() +"\n\t\t"+
                    "\\begin{center}\n\t\t"+
                    "\\AMCnumericChoices{"+bonnereponse+"}\n\t\t\t"+
                    "{digits="+nbdigits+",decimals="+decimaux+",sign="+sign+",scoreexact="+points+",scorewrong="+ptsmauvaisereponse+"}\n\t\t" +
                    "\\end{center}\n\t"+
                    "\\end{questionmultx}\n" +
                    "}";
        }
        return null;

    }

}
