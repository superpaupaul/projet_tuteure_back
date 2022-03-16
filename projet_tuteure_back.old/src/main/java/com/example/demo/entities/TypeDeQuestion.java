package com.example.demo.entities;

public enum TypeDeQuestion {
    UNIQUE,MULTIPLE,NUMERIQUE,OUVERTE;
    public String toString(){
        switch(this){
            case UNIQUE : return "Unique";
            case MULTIPLE: return "Multiple";
            case NUMERIQUE: return "Numerique";
            case OUVERTE: return "Ouverte";
            default: return "Type de question inconnu";
        }
    }
}
