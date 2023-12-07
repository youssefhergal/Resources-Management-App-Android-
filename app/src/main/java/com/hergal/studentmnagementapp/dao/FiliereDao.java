package com.hergal.studentmnagementapp.dao;



import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hergal.studentmnagementapp.model.Filiere;

public class FiliereDao {

    private DatabaseHelper dbHelper;

    public FiliereDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Méthode pour ajouter une filière à la base de données
    public long addFiliere(Filiere filiere) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NOM_FILIERE, filiere.getNom());
        long result = db.insert(DatabaseHelper.TABLE_FILIERES, null, values);
        db.close();
        return result;
    }

    // Méthode pour récupérer toutes les filières de la base de données
    public Cursor getAllFilieres() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_NOM_FILIERE};
        return db.query(DatabaseHelper.TABLE_FILIERES, columns, null, null,
                null, null, null);
    }

}
