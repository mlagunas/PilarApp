package com.pilarapp.manuel.pilarapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manuel on 21/09/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    private final String INFO_TABLE =
            "CREATE TABLE info (" +
                    "id     INTEGER PRIMARY KEY, " +    //1
                    "title  TEXT not NUll, " +          //2
                    "description TEXT, " +              //3
                    "programa TEXT, " +                 //4
                    "destacada BOOLEAN, " +             //5
                    "web TEXT, " +                      //6
                    "diasParaTerminar INTEGER, " +      //7
                    "tipoEntrada TEXT, " +              //8
                    "precioEntrada TEXT, " +            //9
                    "startDate TEXT, " +                //10
                    "endDate TEXT," +                   //11
                    "horaInicio TEXT, " +               //12
                    "horaFinal TEXT, " +                //13
                    "tema TEXT, " +                     //14
                    "subTema TEXT, " +                  //15
                    "lat DOUBLE, " +                    //16
                    "lng DOUBLE, " +                    //17
                    "buses TEXT, " +                    //18
                    "address TEXT, " +                   //19
                    "addressInfo TEXT, " +               //20
                    "imagen TEXT," +
                    "horario TEXT " +                    //21
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
