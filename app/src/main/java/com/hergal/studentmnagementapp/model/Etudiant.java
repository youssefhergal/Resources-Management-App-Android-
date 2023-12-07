package com.hergal.studentmnagementapp.model;


public class Etudiant extends User {
    private String numeroEtudiant;
    private int idFiliere; // Référence vers la filière à laquelle l'étudiant est inscrit

    public String getNumeroEtudiant() {
        return numeroEtudiant;
    }

    public void setNumeroEtudiant(String numeroEtudiant) {
        this.numeroEtudiant = numeroEtudiant;
    }

    public int getIdFiliere() {
        return idFiliere;
    }

    public void setIdFiliere(int idFiliere) {
        this.idFiliere = idFiliere;
    }

    public Etudiant(int id, String nom, String prenom, String identifiant, String motDePasse,
                    String numeroEtudiant, int idFiliere) {
        super(id, nom, prenom, "Etudiant", identifiant, motDePasse);
        this.numeroEtudiant = numeroEtudiant;
        this.idFiliere = idFiliere;
    }

    // Autres méthodes si nécessaire
}
