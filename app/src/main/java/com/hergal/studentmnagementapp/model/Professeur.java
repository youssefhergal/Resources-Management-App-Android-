package com.hergal.studentmnagementapp.model;



public class Professeur extends User {
    private String matiereEnseignee;

//    public Professeur(int id, String nom, String prenom, String identifiant, String motDePasse,
//                      String matiereEnseignee) {
//        super(id, nom, prenom, "Professeur", identifiant, motDePasse);
//        this.matiereEnseignee = matiereEnseignee;
//    }

    public String getMatiereEnseignee() {
        return matiereEnseignee;
    }

    public void setMatiereEnseignee(String matiereEnseignee) {
        this.matiereEnseignee = matiereEnseignee;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "matiereEnseignee='" + matiereEnseignee + '\'' +
                '}';
    }
}
