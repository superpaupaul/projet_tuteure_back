package com.example.demo.jsonhandler;

import com.example.demo.entities.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QuestionsFromJson {

    public static Question treatQuestions(JSONObject question,TypeDeQuestion qType) throws JSONException {
        Question qExtraite;

        String nomQuestion = question.getString("nomQuestion");
        String sujet = question.getString("sujet");
        String intitule = question.getString("intitule");
        Double points = question.getDouble("points");


        if(qType == TypeDeQuestion.UNIQUE || qType == TypeDeQuestion.MULTIPLE){
            JSONObject reponsesextraites = question.getJSONObject("reponses");
            Map<String,Boolean> reponsesmap = new HashMap<String,Boolean>();


            Iterator<String> keys = reponsesextraites.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                reponsesmap.put(key,Boolean.parseBoolean(reponsesextraites.getString(key)));
            }

            if(qType == TypeDeQuestion.UNIQUE){

                JSONObject baremeextrait = question.getJSONObject("bareme");
                Map<TypeDeReponse,Double> baremextraitmap = new HashMap<TypeDeReponse,Double>();
                baremextraitmap.put(TypeDeReponse.BONNE,baremeextrait.getDouble("b"));
                baremextraitmap.put(TypeDeReponse.INCOHERENTE,baremeextrait.getDouble("e"));
                baremextraitmap.put(TypeDeReponse.ABSENTE,baremeextrait.getDouble("v"));
                baremextraitmap.put(TypeDeReponse.MAUVAISE,baremeextrait.getDouble("m"));

                qExtraite = new QuestionUnique(nomQuestion,intitule,sujet,points,reponsesmap,baremextraitmap);

            }
            else{
                Double coef = question.getDouble("coefficientMauvaiseReponse");
                qExtraite = new QuestionMultiple(nomQuestion,intitule,sujet,points,reponsesmap,coef);
            }
        }

        else if(qType == TypeDeQuestion.NUMERIQUE){
            Double pointsMauvaiseReponse = question.getDouble("pointsMauvaiseReponse");
            Double bonneReponse = question.getDouble("bonneReponse");
            Boolean sign = question.getBoolean("sign");

            qExtraite = new QuestionNumerique(nomQuestion,intitule,sujet,points,pointsMauvaiseReponse,bonneReponse,sign);
        }
        else{
            int nbLignes = question.getInt("nbLignes");
            JSONObject baremeextrait = question.getJSONObject("bareme");

            Map<String,Double> bareme = new HashMap<String ,Double>();
            Iterator<String> keys = baremeextrait.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                bareme.put(key,baremeextrait.getDouble(key));
            }

            qExtraite = new QuestionOuverte(nomQuestion,intitule,sujet,points,nbLignes,bareme);
        }

        return(qExtraite);
    }


}
