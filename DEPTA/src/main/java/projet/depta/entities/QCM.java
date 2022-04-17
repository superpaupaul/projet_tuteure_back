package projet.depta.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter

@Entity
@Table(name="QCM")
public class QCM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="entete")
    private String entete;

    @Column(name="titre")
    private String titre;

    @Column(name="questions")
    @OneToMany(cascade=CascadeType.ALL)
    private List<Question> questions;

    @Column(name="isRandomized")
    private Boolean isRandomized;

    @Column(name="idcreateur")
    private int idcreateur;


    public QCM(){}

    public QCM(String entete, String titre, List<Question> questions, Boolean isRandomized, int createur){
        this.entete = entete; this.titre = titre; this.questions = questions; this.isRandomized = isRandomized; this.idcreateur = createur;
    }

    public Boolean compareTo(QCM qcm){
        if(!(Objects.equals(qcm.getId(), this.getId()))){
            return false;
        }
        if(!(qcm.getIsRandomized() == this.getIsRandomized())){
            return false;
        }
        if(!(qcm.getIdcreateur() == this.getIdcreateur())){
            return false;
        }
        if(!(qcm.getEntete().equals(this.getEntete()))){
            return false;
        }
        if(!(qcm.getQuestions().equals(this.getQuestions()))){
            return false;
        }
        if(!(qcm.getThemesDeQuestions().equals(this.getThemesDeQuestions()))){
            return false;
        }
        return qcm.getTitre().equals(this.getTitre());
    }

    public String toTex(){

        String texText = "%%DEBUT DU QCM%%\n\n"+
                "\\documentclass[a4paper]{article}\n" +
                "\\usepackage[utf8x]{inputenc}\n" +
                "\\usepackage[T1]{fontenc}\n" +
                "\n" +
                "\\usepackage[box,completemulti]{automultiplechoice}\n" +
                "\n" +
                "\\begin{document}\n\n"+
                "\\setdefaultgroupmode{withoutreplacement}\n\n\n";
        for(Question q : questions){
            texText+= q.toTex() + "\n";

        }

        texText += "\n\n%% HEADER %%\n\n"+
                "\\onecopy{10}{\n"+
                "\\noindent{\\bf QCM  \\hfill TEST}\n" +
                "\n" +
                "\\vspace*{.5cm}\n" +
                "\\begin{minipage}{.4\\linewidth}\n" +
                "  \\centering\\large\\bf "+this.getEntete()+"\n" +
                "\\end{minipage}\n" +
                "\\namefield{\\fbox{\\begin{minipage}{.5\\linewidth}\n" +
                "Prénom et nom:\n" +
                "\n" +
                "\\vspace*{.5cm}\\dotfill\n" +
                "\\vspace*{1mm}\n" +
                "\\end{minipage}}}\n\n"+
                "\n\n%% FIN HEADER %%\n\n";


        for(String sujet : this.getThemesDeQuestions()){
            texText += "\\begin{center}\n\t" +
                    "\\hrule\\vspace{2mm}\n\t" +
                    "\\bf\\Large "+sujet +"\n\t" +
                    "\\vspace{1mm}\\hrule\n\t" +
                    "\\end{center}\n" +
                    "\n" +
                    "\\insertgroup{"+sujet+"}\n";
        }


        return texText+"\n" +
                "}\n"+
                "\\end{document}\n"+
                "\n\n%%FIN DU QCM%% \n\n";

    }

    public ArrayList<String> getThemesDeQuestions(){
        ArrayList<String> themesDeQuestions = new ArrayList<String>();
        for(Question question : this.questions){
            themesDeQuestions.add(question.getTheme());
        }
        Set<String> set = new HashSet<>(themesDeQuestions);
        themesDeQuestions.clear();
        themesDeQuestions.addAll(set);
        Collections.reverse(themesDeQuestions);
        return themesDeQuestions;
    }
}
