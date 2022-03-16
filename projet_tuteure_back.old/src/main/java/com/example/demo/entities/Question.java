package com.example.demo.entities;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "typeDeQuestion")
@JsonSubTypes({
        @JsonSubTypes.Type(value = QuestionMultiple.class, name = "MULTIPLE"),
        @JsonSubTypes.Type(value = QuestionUnique.class, name = "UNIQUE"),
        @JsonSubTypes.Type(value = QuestionOuverte.class, name = "OUVERTE"),
        @JsonSubTypes.Type(value = QuestionNumerique.class, name = "NUMERIQUE")
})


/**
 * Classe abstraite permettant l'instanciation de Questions.
 * Cette classe est abstraite et son constructeur doit-être appelé dans les constructeurs des classes filles
 */
public abstract class Question {
    private TypeDeQuestion typeDeQuestion;
    private String intitule;
    private String sujet;
    private Double points;
    private String nomQuestion;

    /**
     * Constructeur d'une question générique
     * @param intitule l'intitulé de la question
     * @param sujet le sujet de la question
     */
    public Question(TypeDeQuestion typeDeQuestion, String nomQuestion, String intitule, String sujet, Double points){
        this.typeDeQuestion = typeDeQuestion;
        this.nomQuestion = nomQuestion;
        this.intitule = intitule;
        this.sujet = sujet;
        this.points = points;
    }

    abstract String toTex();

    public TypeDeQuestion getTypeDeQuestion() {
        return this.typeDeQuestion;
    }

    public String getNomQuestion() {
        return nomQuestion;
    }

    public void setNomQuestion(String nomQuestion) {
        this.nomQuestion = nomQuestion;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }
}
