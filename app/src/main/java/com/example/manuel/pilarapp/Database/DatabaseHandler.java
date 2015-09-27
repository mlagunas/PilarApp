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

    private final String ENTIDAD_TABLE =
            "CREATE TABLE entidad (" +
                    "id INTEGER PRIMARY KEY, " +
                    "title TEXT not NULL, " +
                    "telefonoEntidad  TEXT, " +
                    "horarioEntidad  TEXT, " +
                    "webEntidad  TEXT" +
                    ");";

    private final String GEOMETRY_TABLE = "" +
            "CREATE TABLE geometry (" +
            "coordinate1 INTEGER, " +
            "coordinate2 INTEGER, " +
            "typo TEXT," +
            "PRIMARY KEY (coordinate1, coordinate2)" +
            ");";

    private final String LUGAR_TABLE = "" +
            "CREATE TABLE lugar (" +
            "id TEXT PRIMARY KEY, " +
            "title TEXT, " +
            "direccion TEXT, " +
            "cp TEXT, " +
            "provincia TEXT, " +
            "pais TEXT, " +
            "telefono TEXT, " +
            "fax TEXT, " +
            "mail TEXT, " +
            "idGeometry1 INTEGER, " +
            "idGeomtry2 INTEGER, " +
            "autobuses TEXT" +
            ");";

    private final String LUGAR_INSCRIPCION_TABLE = "";
    private final String POBLACION_TABLE = "";
    private final String REQUEST_TABLE = "";
    private final String SUBEVENT_TABLE = "";
    private final String TEMA_TABLE = "";


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
