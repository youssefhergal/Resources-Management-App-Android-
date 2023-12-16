package com.hergal.studentmnagementapp.model;



public class Filiere {


    private int id;
    private String code;
    private String nom;
    private String description;
    private byte[] image;
    private Professeur coordonnateur;

    public Filiere(int id, String code, String nom, String description, byte[] image, Professeur coordonnateur) {
        this.id = id;
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.coordonnateur = coordonnateur;
    }

    public Filiere(String code, String nom, String description, byte[] image, Professeur coordonnateur) {
        this.code = code;
        this.nom = nom;
        this.description = description;
        this.image = image;
        this.coordonnateur = coordonnateur;
    }

    public Filiere(int id, String code, String nom, String description, byte[] image, int i) {
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Professeur getCoordonnateur() {
        return coordonnateur;
    }

    public void setCoordonnateur(Professeur coordonnateur) {
        this.coordonnateur = coordonnateur;
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

