package com.hergal.studentmnagementapp.model ;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String email ;
    private String password;

    private int image ;

    public User() {
    }


    public User(int id, String nom, String prenom, String email, String password, int image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;

        this.email = email;
        this.password = password;
        this.image = image;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(int id, String nom, String prenom,  String email, int image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.image = image;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImage() {
        return image;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
