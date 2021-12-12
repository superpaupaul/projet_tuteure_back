package com.example.demo;

import com.example.demo.entities.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.HashMap;
import java.util.Map;
import com.google.gson.Gson;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class DemoApplication {

	public static void main(String[] args) throws JSONException {

		Map<String,Double> bareme = new HashMap<String,Double>();
		bareme.put("AB",0.5);
		bareme.put("B",1.0);
		bareme.put("TB",1.5);
		QuestionOuverte maQ = new QuestionOuverte("Henri IV","Quelle est la couleur du cheval blanc d'Henri IV ?","histoire",1.5,4,bareme);
		System.out.println(maQ.toTex());


		Map<String,Boolean> reponses = new HashMap<String,Boolean>();
		reponses.put("Bleu",false);
		reponses.put("Blanc",true);
		reponses.put("Noir",false);
		reponses.put("Rouge",false);
		Map<TypeDeReponse,Double> baremeMaQ2 = new HashMap<TypeDeReponse,Double>();
		baremeMaQ2.put(TypeDeReponse.b,1.5);
		baremeMaQ2.put(TypeDeReponse.v,0.0);
		baremeMaQ2.put(TypeDeReponse.e,-1.0);
		baremeMaQ2.put(TypeDeReponse.m,-1.0);
		QuestionUnique maQ2 = new QuestionUnique("Henri IV couleur","Quelle est la couleur du cheval blanc d'Henri IV ?","histoire",1.5,reponses,baremeMaQ2);

		System.out.println(maQ2.toTex());

		Map<String,Boolean> reponsesQ3 = new HashMap<String,Boolean>();
		reponsesQ3.put("Bleu",false);
		reponsesQ3.put("Blanc",true);
		reponsesQ3.put("White",true);
		reponsesQ3.put("Rouge",false);
		QuestionMultiple maQ3 = new QuestionMultiple("Henri IV couleur 2","Quelle est la couleur du cheval blanc d'Henri IV ?","histoire",1.5,reponsesQ3,0.5);

		System.out.println(maQ3.toTex());


		QuestionNumerique maQ4 = new QuestionNumerique("Henri IV nombre","Quel est le numéro de Henri IV ?","histoire",1.5,-0.5,4.7569789,true);

		System.out.println(maQ4.toTex());

		QCM qcm1 = new QCM("dfghj",10.6,"aaa",true);
		qcm1.addQuestion(maQ);
		qcm1.addQuestion(maQ2);
		qcm1.addQuestion(maQ3);
		qcm1.addQuestion(maQ4);
		System.out.println(qcm1.toTex());


		QCM qcm2 = new QCM("dfghj",10.6,"aaa",true);

		JSONObject json = new JSONObject(new Gson().toJson(qcm1));

		System.out.println(qcm1.getTitre());
		System.out.println(json);




		SpringApplication.run(DemoApplication.class, args);

	}

}
