package com.example.manuel.pilarapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel on 21/09/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private final String INFO_TABLE =
            "CREATE TABLE info (" +
                    "id     INTEGER PRIMARY KEY, " +
                    "title  TEXT not NUll, " +
                    "description TEXT not NULL, " +
                    "programa TEXT not NULL, " +
                    "destacada BOOLEAN, " +
                    "web TEXT, " +
                    "diasParaTerminar INTEGER, " +
                    "tipoEntrada TEXT, " +
                    "precioEntrada TEXT, " +
                    "startDate TEXT, " +
                    "endDate TEXT," +
                    "lat DOUBLE," +
                    "lng DOUBLE " +
                    ");";



    public DatabaseHandler(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(INFO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
