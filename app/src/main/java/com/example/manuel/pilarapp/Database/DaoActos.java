package com.example.manuel.pilarapp.Database;

import android.content.Context;
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

    private Context context;
    private final String INFO_TABLE = "CREATE TABLE info (" +
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
    private int id;
    private String title;
    private String description;
    private String programa;
    private Boolean destacada;
    private String web;
    private int diasParaTerminar;
    private String tipoEntrada;
    private String precioEntrada;
    private String image;

    public DaoActos(Context pContext) {
        super(pContext);
        super.open();
        this.context = pContext;
    }

    public List<Acto> getActos(){
        ArrayList<Acto> actos = new ArrayList<Acto>();
        c = super.mDb.rawQuery("SELECT * FROM info;", null);
        if (c.moveToFirst()) {
            do {
               actos.add(fillActo());
            } while (c.moveToNext());
        }
        return actos;
    }

    public List<Acto> getActos(Date date){
        ArrayList<Acto> actos = new ArrayList<Acto>();
        c = super.mDb.rawQuery("SELECT * FROM info " +
                                "WHERE startDate LIKE '%"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"%';", null);
        if (c.moveToFirst()) {
            do {
                actos.add(fillActo());
            } while (c.moveToNext());
        }
        return actos;
    }

    public Acto getActo(int i){
        Log.d("TAG id",id + " ");
        c = super.mDb.rawQuery("SELECT * FROM info " +
                                "WHERE id = "+id+";", null);
        if (c.moveToFirst()) {
            do {
                return fillActo();
            } while (c.moveToNext());
        }
        return null;
    }

    private Acto fillActo(){
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
        return a;
    }

    public void fillDB(List<Acto> actos, Boolean db){
     for(Acto a: actos){
         String startd = null;
         String endd = null;
         int destacada = -1;

         if (a.getStartDate()!= null)startd = new SimpleDateFormat("yyyy-MM-dd").format(a.getStartDate());
         if (a.getStartDate()!= null)endd = new SimpleDateFormat("yyyy-MM-dd").format(a.getEndDate());

         if(a.getDestacada())destacada = 1;
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
                 "lng" +
                 ") " +
                 "VALUES (" +
                 a.getId() +", '" +
                 formatSQL(a.getTitle()) +"',' " +
                 formatSQL(a.getDescription()) +"','" +
                 a.getPrograma() +"', " +
                 destacada +", '" +
                 formatSQL(a.getWeb()) +"' ," +
                 a.getDiasParaTerminar() +" ,'" +
                 formatSQL(a.getTipoEntrada()) +"','" +
                 formatSQL(a.getPrecioEntrada()) +"', '" +
                 formatSQL(startd) +"', '" +
                 formatSQL(endd) +"', '" +
                 formatSQL(a.getHoraInicio(db)) +"', '" +
                 formatSQL(a.getHoraFinal(db))+"', '" +
                 formatSQL(a.getTema(db))+"', '" +
                 formatSQL(a.getSubtema(db))+"', "+
                 a.getLat(db) + ", " +
                 a.getLng(db) + " " +
                 ");";
         Log.d("TAG consulta", consulta);
         mDb.execSQL(consulta);
     }
    }

    private String formatSQL(String s){
        String aux = "";
        if(s != null){
            for(int i = 0; i<s.length(); i++){
                if(s.charAt(i)==42){
                    aux += "\\";
                    aux += s.charAt(i);
                }
                else{
                    aux+=s.charAt(i);
                }
            }
        }
        return aux;
    }

    public void truncateDB(){
        mDb.execSQL("delete from info");
    }
}
