package com.hergal.studentmnagementapp.model;



public class Filiere {


    private int id;
    private String code;
    private String intitule;
    private String description;
    private String cordonateur;

    private String date_ouverture;

    public Filiere() {
    }

    public Filiere(int id, String code, String intitule, String description,String cordonateur,String date_ouverture) {
        this.id = id;
        this.code = code;
        this.intitule = intitule;
        this.description = description;
        this.cordonateur = cordonateur;
        this.date_ouverture = date_ouverture;
    }

    public Filiere(String code, String intitule, String description, String cordonateur, String date_ouverture) {
        this.code = code;
        this.intitule = intitule;
        this.description = description;
        this.cordonateur = cordonateur;
        this.date_ouverture = date_ouverture;
    }

    public String getCordonateur() {
        return cordonateur;
    }

    public void setCordonateur(String cordonateur) {
        this.cordonateur = cordonateur;
    }

    public String getDate_ouverture() {
        return date_ouverture;
    }

    public void setDate_ouverture(String date_ouverture) {
        this.date_ouverture = date_ouverture;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }


}

