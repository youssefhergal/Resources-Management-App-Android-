package com.hergal.studentmnagementapp.model;



public class Filiere {
    private int id;
    private String nom;

    private String code;

    private String description;

    public Filiere(int id, String nom, String code , String description) {
        this.id = id;
        this.nom = nom;
        this.code = code;
        this.description = description;
    }

    public Filiere() {
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


}

