package com.hergal.studentmnagementapp.model;


import java.util.Date;

public class Etudiant extends User {
    private String  date_inscriptiopn;
    private String filiere;

    public String getDate_inscriptiopn() {
        return date_inscriptiopn;
    }

    public void setDate_inscriptiopn(String date_inscriptiopn) {
        this.date_inscriptiopn = date_inscriptiopn;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public Etudiant() {
    }

    public Etudiant(int id, String nom, String prenom,  String email, int image, String date_inscriptiopn, String filiere) {
        super(id, nom, prenom, email, image);
        this.date_inscriptiopn = date_inscriptiopn;
        this.filiere = filiere;
    }
}
