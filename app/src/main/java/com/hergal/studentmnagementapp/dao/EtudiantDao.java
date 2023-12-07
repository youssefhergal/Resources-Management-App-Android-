package com.hergal.studentmnagementapp.dao;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hergal.studentmnagementapp.model.Etudiant;

public class EtudiantDao {

    private DatabaseHelper dbHelper;

    public EtudiantDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Méthode pour ajouter un étudiant à la base de données
    public long addEtudiant(Etudiant etudiant) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOM, etudiant.getNom());
        values.put(DatabaseHelper.COLUMN_PRENOM, etudiant.getPrenom());
        // Ajoutez d'autres colonnes d'étudiant ici...
        long result = db.insert(DatabaseHelper.TABLE_ETUDIANTS, null, values);
        db.close();
        return result;
    }

    // Méthode pour récupérer tous les étudiants de la base de données
    public Cursor getAllEtudiants() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_NOM, DatabaseHelper.COLUMN_PRENOM};
        return db.query(DatabaseHelper.TABLE_ETUDIANTS, columns, null, null,
                null, null, null);
    }

    // Méthode pour récupérer un étudiant par son ID
    public Cursor getEtudiantById(int etudiantId) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_NOM, DatabaseHelper.COLUMN_PRENOM};
        String selection = DatabaseHelper.COLUMN_ID + "=?";
        String[] selectionArgs = {String.valueOf(etudiantId)};
        return db.query(DatabaseHelper.TABLE_ETUDIANTS, columns, selection, selectionArgs,
                null, null, null);
    }

    // Ajoutez d'autres méthodes pour les opérations sur les étudiants si nécessaire
}

