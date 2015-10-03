package com.example.manuel.pilarapp.Database;

import android.content.Context;
import android.database.Cursor;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;

import com.example.manuel.pilarapp.Objects.Acto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Manuel on 30/09/2015.
 */
public class DaoActos extends DaoBase {

    private transient Context context;
    private final String INFO_TABLE = "CREATE TABLE info (" +
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
            "address TEXT," +                   //19
            "addressInfo TEXT " +               //20
            ");";

    public DaoActos(Context pContext) {
        super(pContext);
        this.context = pContext;
    }

    public List<Acto> getActos() {
        super.open();
        ArrayList<Acto> actos = new ArrayList<Acto>();
        c = super.mDb.rawQuery("SELECT * FROM info;", null);
        if (c.moveToFirst()) {
            do {
                actos.add(fillActo());
            } while (c.moveToNext());
        }
        super.close();
        return actos;
    }

    public void insertActo(Acto a) {
        String startd = null;
        String endd = null;
        int destacada = -1;

        if (a.getStartDate() != null)
            startd = new SimpleDateFormat("yyyy-MM-dd").format(a.getStartDate());
        if (a.getStartDate() != null)
            endd = new SimpleDateFormat("yyyy-MM-dd").format(a.getEndDate());

        if (a.getDestacada()) destacada = 1;
        else destacada = 0;

        String consulta = "INSERT INTO info " +
                "(" +
                "id, " +
                "title, " +
                "description, " +
                "programa, " +
                "destacada, " +
                "web, " +
                "diasParaTerminar, " +
                "tipoEntrada, " +
                "precioEntrada, " +
                "startDate, " +
                "endDate, " +
                "horaInicio, " +
                "horaFinal, " +
                "tema, " +
                "subtema, " +
                "lat, " +
                "lng, " +
                "buses," +
                "address, " +
                "addressInfo" +
                ") " +
                "VALUES (" +
                a.getId() + ", '" +
                formatSQL(a.getTitle()) + "',' " +
                formatSQL(a.getDescription()) + "','" +
                a.getPrograma() + "', " +
                destacada + ", '" +
                formatSQL(a.getWeb()) + "' ," +
                a.getDiasParaTerminar() + " ,'" +
                formatSQL(a.getTipoEntrada()) + "','" +
                formatSQL(a.getPrecioEntrada()) + "', '" +
                formatSQL(startd) + "', '" +
                formatSQL(endd) + "', '" +
                formatSQL(a.getHoraInicio()) + "', '" +
                formatSQL(a.getHoraFinal()) + "', '" +
                formatSQL(a.getTema()) + "', '" +
                formatSQL(a.getSubtema()) + "', " +
                a.getLat() + ", " +
                a.getLng() + ", '" +
                a.getBuses() + "', '" +
                a.getAddress() + "', '" +
                a.getAddressInfo() + "'" +
                ");";
        Log.d("TAG consulta", consulta);
        mDb.execSQL(consulta);
    }

    public List<Acto> getActos(Date date) {
        super.open();
        ArrayList<Acto> actos = new ArrayList<Acto>();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(date);
        c = super.mDb.rawQuery("SELECT * FROM info " +
                "WHERE endDate   <= '2015-10-18' " +
                "AND startDate   >= '2015-10-09' " +
                "AND '"+fecha+"' >= startDate " +
                "AND '"+fecha+"' <= endDate  "
                , null);
        if (c.moveToFirst()) {
            do {
                actos.add(fillActo());
            }
            while (c.moveToNext());
        }
        super.close();
        return actos;
    }


    public Acto getActo(int i) {
        super.open();
        c = super.mDb.rawQuery("SELECT * FROM info " +
                "WHERE id = " + i + ";", null);
        if (c.moveToFirst()) return fillActo();
        super.close();
        return null;
    }

    private Acto fillActo() {
        Acto a = new Acto();
        a.setId((c.getInt(0)));
        a.setTitle(c.getString(1));
        a.setDescription(c.getString(2));
        a.setPrograma(c.getString(3));
        a.setDestacada(c.getInt(4) > 0);
        a.setWeb(c.getString((5)));
        a.setDiasParaTerminar(c.getInt(6));
        a.setTipoEntrada(c.getString(7));
        a.setPrecioEntrada(c.getString(8));
        a.setStartDate(c.getString(9));
        a.setEndDate(c.getString(10));
        a.setHoraInicio(c.getString(11));
        a.setHoraFinal(c.getString(12));
        a.setTema(c.getString(13));
        a.setSubtema(c.getString(14));
        a.setLat(c.getDouble(15));
        a.setLng(c.getDouble(16));
        a.setBuses(c.getString(17));
        a.setAddress(c.getString(18));
        a.setAddressInfo(c.getString(19));
        Log.d("TAG ACTO", a.getAddress() + a.getTitle());
        return a;
    }

    public void fillDB(List<Acto> actos, Boolean db) {
        super.open();
        for (Acto a : actos) {
            insertActo(a);
        }
        super.close();
    }

    private String formatSQL(String s) {
        String aux = "";
        if (s != null) {
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == 42) {
                    aux += "\\";
                    aux += s.charAt(i);
                } else {
                    aux += s.charAt(i);
                }
            }
        }
        return aux;
    }

    public void truncateDB() {
        super.open();
        mDb.execSQL("delete from info");
        super.close();
    }
}
