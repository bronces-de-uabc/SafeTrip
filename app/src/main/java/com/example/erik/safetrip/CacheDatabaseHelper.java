package com.example.erik.safetrip;

import android.content.ContentValues;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class CacheDatabaseHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "infoUsuario";
    private static final int DB_VERSION = 1;

    CacheDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(DB_VERSION < 1){
            db.execSQL("CREATE TABLE INFO ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "NOMBRE TEXT, "
                    + "CORREO TEXT, "
                    + "PASSWOR TEXT, "
                    + "MENSAJE TEXT, "
                    + "CONTACTO0 TEXT, "
                    + "CONTSCTO1 TEXT, "
                    + "CONTACTO2 TEXT);");
        }
    }

    public void insert(SQLiteDatabase db, String nombre, String correo, String password,
                       String contacto0, String contacto1, String contacto2){
        ContentValues usuarioValues = new ContentValues();
        usuarioValues.put("NOMBRE",nombre);
        usuarioValues.put("CORREO",correo);
        usuarioValues.put("PASSWORD",password);
        usuarioValues.put("MENSAJE","Se ha regitrado una actividad fuera de lo normal, este usuario podría estar en peligro");
        usuarioValues.put("CONTACTO0", contacto0);
        usuarioValues.put("CONTACTO1", contacto1);
        usuarioValues.put("CONTACTO2", contacto2);
        db.insert("INFO",null,usuarioValues);
    }
}

