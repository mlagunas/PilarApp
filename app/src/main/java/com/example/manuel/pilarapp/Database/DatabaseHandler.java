package com.example.manuel.pilarapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel on 21/09/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private final String ACTOS_TABLE =
            "CREATE TABLE Actos (" +
                    "id     INTEGER PRIMARY KEY, " +
                    "title  TEXT not NUll, " +
                    "description TEXT not NULL, " +
                    "programa TEXT not NULL, " +
                    "destacada BOOLEAN, " +
                    "web TEXT, " +
                    "idEntidad INTEGER," +
                    "idLugarInscripcion INTEGER, " +
                    "lastUpdated TEXT, " +
                    "idGeometry1 INTEGER, " +
                    "idGeomtry2 INTEGER, " +
                    "diasParaTerminar INTEGER" +
                    ");";



    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }


}
