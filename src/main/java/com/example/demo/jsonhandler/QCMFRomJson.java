package com.example.demo.jsonhandler;

import com.example.demo.entities.QCM;
import com.example.demo.entities.TypeDeQuestion;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class QCMFRomJson {

    public static QCM serialize(JSONObject json) throws JSONException {



        QCM qcm = new QCM(json.getString("titre"),json.getDouble("duree"),json.getString("entete"),json.getBoolean("isRandomized"));

        JSONArray questions = json.getJSONArray("questions");
        for(int i = 0; i < questions.length() ; i++){
            String type2Question = questions.getJSONObject(i).getString("typeDeQuestion");
            JSONObject question = questions.getJSONObject(i);
            switch(type2Question){
                case "UNIQUE" :
                    qcm.addQuestion(QuestionsFromJson.treatQuestions(question, TypeDeQuestion.UNIQUE));
                case "MULTIPLE" :
                    qcm.addQuestion(QuestionsFromJson.treatQuestions(question, TypeDeQuestion.MULTIPLE));
                case "NUMERIQUE" :
                    qcm.addQuestion(QuestionsFromJson.treatQuestions(question, TypeDeQuestion.NUMERIQUE));
                case "OUVERTE" :
                    qcm.addQuestion(QuestionsFromJson.treatQuestions(question, TypeDeQuestion.OUVERTE));
            }
        }
        return(qcm);
    }
}
