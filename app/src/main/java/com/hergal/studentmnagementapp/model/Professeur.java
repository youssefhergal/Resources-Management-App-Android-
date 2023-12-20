package com.hergal.studentmnagementapp.model;



public class Professeur extends User {

    private String specialite ;

    public Professeur() {
    }


    public Professeur(int id, String nom, String prenom, String email, String specialite) {
        super(id, nom, prenom, email);
        this.specialite = specialite;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }


}
