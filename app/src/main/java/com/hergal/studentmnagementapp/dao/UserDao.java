package com.hergal.studentmnagementapp.dao;




import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hergal.studentmnagementapp.model.User;

public class UserDao {

    private DatabaseHelper dbHelper;

    public UserDao(Context context) {
        dbHelper = new DatabaseHelper(context);
    }

    // Méthode pour ajouter un utilisateur à la base de données
    public long addUser(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_USERNAME, user.getNom());
        values.put(DatabaseHelper.COLUMN_PASSWORD, user.getPassword());
        long result = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        db.close();
        return result;
    }

    // Méthode pour vérifier si un utilisateur existe dans la base de données
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_USERNAME};
        String selection = DatabaseHelper.COLUMN_USERNAME + "=? AND " + DatabaseHelper.COLUMN_PASSWORD + "=?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs,
                null, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }

    // Méthode pour vérifier si un utilisateur existe dans la base de données par son nom d'utilisateur
    public boolean checkUserExists(String username) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] columns = {DatabaseHelper.COLUMN_USERNAME};
        String selection = DatabaseHelper.COLUMN_USERNAME + "=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.query(DatabaseHelper.TABLE_USERS, columns, selection, selectionArgs,
                null, null, null);
        boolean result = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return result;
    }
}
