package com.hergal.studentmnagementapp.model ;

public class User {
    private int id;
    private String nom;
    private String prenom;
    private String role; // Peut être "Etudiant" ou "Professeur"
    private String username; // Un identifiant unique pour l'utilisateur
    private String password;

    public User() {
    }

    public User(String nom, String prenom, String role, String identifiant, String motDePasse) {
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.username = identifiant;
        this.password = motDePasse;
    }

    public User(int id, String nom, String prenom, String role, String identifiant, String motDePasse) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.role = role;
        this.username = identifiant;
        this.password = motDePasse;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
