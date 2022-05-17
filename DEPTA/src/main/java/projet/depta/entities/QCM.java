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

    @Column(name="categories")
    @OneToMany(cascade=CascadeType.ALL)
    private List<Categorie> categories;

    @Column(name="isRandomized")
    private Boolean isRandomized;

    @Column(name="idcreateur")
    private int idcreateur;


    public QCM(){}


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
        if(!(qcm.getCategories().equals(this.getCategories()))){
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
        for(Categorie c : categories){
            for(Question q : c.getQuestions()) texText += q.toTex() + "\n";
        }

        texText += "\n\n%% HEADER %%\n\n"+
                "\\onecopy{10}{\n"+
                "\\noindent{\\bf QCM  \\hfill TEST}\n" +
                "\n" +
                "{\\setlength{\\parindent}{0pt}\\hspace*{\\fill}\\AMCcodeGridInt{etu}{8}\\hspace*{\\fill}\n"+
                "\\begin{minipage}[b]{6.5cm}\n"+
                "$\\longleftarrow{}$\\hspace{0pt plus 1cm} Merci de remplir votre numéro d'étudiant ici et votre prénom et nom en dessous.\n\n"+
                "\\vspace{3ex}\n\n"+
                "\\hfill\\namefield{\\fbox{\n\t"+
                "\\begin{minipage}{.9\\linewidth}\n\t\t"+
                "Prénom et nom :\n\n\t\t"+
                "\\vspace*{.5cm}\\dotfill\n\n\t\t"+
                "\\vspace*{.5cm}\\dotfill\n\n\t\t"+
                "\\vspace*{1mm}\n\t"+
                "\\end{minipage}\n"+
                "}}\\hfill\\vspace{5ex}\\end{minipage}\\hspace*{\\fill}"+

                    "}"+
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
        for(Categorie categorie : this.categories){
            themesDeQuestions.add(categorie.getNom());
        }
        Set<String> set = new HashSet<>(themesDeQuestions);
        themesDeQuestions.clear();
        themesDeQuestions.addAll(set);
        Collections.reverse(themesDeQuestions);
        return themesDeQuestions;
    }
}
