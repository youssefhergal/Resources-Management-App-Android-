package com.hergal.studentmnagementapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.hergal.studentmnagementapp.model.Etudiant;
import com.hergal.studentmnagementapp.model.Filiere;
import com.hergal.studentmnagementapp.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Les constantes pour la base de données
    protected static final String DATABASE_NAME = "app_database";
    protected static final int DATABASE_VERSION = 1;
    protected static final String TABLE_USERS = "users";
    protected static final String TABLE_ETUDIANTS = "etudiants";
    protected static final String TABLE_FILIERES = "filieres"; // Nouvelle table pour les filières
    protected static final String COLUMN_USERNAME = "username";
    protected static final String COLUMN_PASSWORD = "password";
    protected static final String COLUMN_NOM = "nom";
    protected static final String COLUMN_PRENOM = "prenom";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_NOM_FILIERE = "nom_filiere";

    // Constructeur
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Méthode appelée lors de la création de la base de données
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table "users" avec les colonnes nécessaires
        String createUserTableQuery = "CREATE TABLE " + TABLE_USERS + " (" +
                COLUMN_USERNAME + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT)";
        db.execSQL(createUserTableQuery);

        // Créer la table "etudiants" avec les colonnes nécessaires
        String createEtudiantTableQuery = "CREATE TABLE " + TABLE_ETUDIANTS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOM + " TEXT, " +
                COLUMN_PRENOM + " TEXT)";
        db.execSQL(createEtudiantTableQuery);

        // Créer la table "filieres" avec les colonnes nécessaires
        String createFiliereTableQuery = "CREATE TABLE " + TABLE_FILIERES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOM_FILIERE + " TEXT)";
        db.execSQL(createFiliereTableQuery);
    }

    // Méthode appelée lors de la mise à niveau de la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer les tables existantes et les recréer
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILIERES);
        onCreate(db);
    }



}
