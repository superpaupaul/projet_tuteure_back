package com.example.demo.entities;

public enum TypeDeReponse {
    b,m,e,v;

    public String toString() {
        switch (this){
            case b : return "b";
            case m : return "m";
            case e : return "e";
            case v : return "v";
            default: return "Réponse par défaut";
        }
    }
}
