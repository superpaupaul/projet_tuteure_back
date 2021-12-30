package com.example.demo.entities;

public class ProfessorsQCM {
    int prof_id;
    QCM qcm;

    public ProfessorsQCM(){};

    public int getProf_id() {
        return prof_id;
    }

    public void setProf_id(int prof_id) {
        this.prof_id = prof_id;
    }

    public QCM getQcm() {
        return qcm;
    }

    public void setQcm(QCM qcm) {
        this.qcm = qcm;
    }
}
