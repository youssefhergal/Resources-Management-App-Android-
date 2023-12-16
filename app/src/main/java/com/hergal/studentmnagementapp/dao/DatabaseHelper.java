package com.hergal.studentmnagementapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Les constantes pour la base de données
    protected static final String DATABASE_NAME = "app_database";
    protected static final int DATABASE_VERSION = 1;
    protected static final String TABLE_USERS = "users";
    protected static final String TABLE_ETUDIANTS = "etudiants";
    protected static final String TABLE_FILIERES = "filieres"; // Nouvelle table pour les filières
    protected static final String TABLE_PROFESSEURS = "professeurs"; // Nouvelle table pour les professeurs
    protected static final String COLUMN_USERNAME = "username";
    protected static final String COLUMN_PASSWORD = "password";
    protected static final String COLUMN_NOM = "nom";
    protected static final String COLUMN_PRENOM = "prenom";
    protected static final String COLUMN_ID = "id";
    protected static final String COLUMN_NOM_FILIERE = "nom_filiere";
    protected static final String COLUMN_CODE_FILIERE = "code_filiere";
    protected static final String COLUMN_DESCRIPTION_FILIERE = "description_filiere";
    protected static final String COLUMN_IMAGE_FILIERE = "image_filiere";
    protected static final String COLUMN_ID_COORDONNATEUR = "id_coordonnateur";

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

        // Créer la table "professeurs" avec les colonnes nécessaires
        String createProfesseurTableQuery = "CREATE TABLE " + TABLE_PROFESSEURS + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NOM + " TEXT, " +
                COLUMN_PRENOM + " TEXT)";
        db.execSQL(createProfesseurTableQuery);

        // Créer la table "filieres" avec les colonnes nécessaires
        String createFiliereTableQuery = "CREATE TABLE " + TABLE_FILIERES + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CODE_FILIERE + " TEXT, " +
                COLUMN_NOM_FILIERE + " TEXT, " +
                COLUMN_DESCRIPTION_FILIERE + " TEXT, " +
                COLUMN_IMAGE_FILIERE + " BLOB, " +
                COLUMN_ID_COORDONNATEUR + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_ID_COORDONNATEUR + ") REFERENCES " + TABLE_PROFESSEURS + "(" + COLUMN_ID + "))";
        Log.d("DatabaseHelper", "onCreate SQL Query: " + createFiliereTableQuery);
        db.execSQL(createFiliereTableQuery);


        // Ajouter quelques exemples de filières
        insertFiliere(db, "WISD", "Web Intelligence & Data Science", "Description de WISD", null, 1);
        insertFiliere(db, "Mlaim", "Machine Learning Avancées & Intelligence Multimédia", "Description de Mlaim", null, 2);
        insertFiliere(db, "MQL", "Qualité Logiciel", "Description de MQL", null, 3);
        insertFiliere(db, "BDASS", "Big Data Analytics & Smart Systems", "Description de BDASS", null, 1);
    }

    // Méthode appelée lors de la mise à niveau de la base de données
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Supprimer les tables existantes et les recréer
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ETUDIANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFESSEURS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FILIERES);
        onCreate(db);
    }

    private void insertFiliere(SQLiteDatabase db, String code, String nom, String description, byte[] image, int idCoordonnateur) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CODE_FILIERE, code);
        values.put(COLUMN_NOM_FILIERE, nom);
        values.put(COLUMN_DESCRIPTION_FILIERE, description);
        values.put(COLUMN_IMAGE_FILIERE, image);
        values.put(COLUMN_ID_COORDONNATEUR, idCoordonnateur);
        db.insert(TABLE_FILIERES, null, values);
    }
}
