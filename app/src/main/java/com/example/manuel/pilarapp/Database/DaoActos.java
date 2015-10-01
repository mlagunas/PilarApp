package com.example.manuel.pilarapp.Database;

import android.content.Context;
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
        c = super.mDb.rawQuery("SELECT * FROM info" +
                                "WHERE campofecha LIKE %"+new SimpleDateFormat("yyyy-MM-dd").format(date)+"%;", null);
        if (c.moveToFirst()) {
            do {
                actos.add(fillActo());
            } while (c.moveToNext());
        }
        return actos;
    }

    public Acto getActo(int i){
        c = super.mDb.rawQuery("SELECT * FROM info" +
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
        a.setLat(c.getDouble(11));
        return a;
    }

    public void fillDB(List<Acto> actos){
     for(Acto a: actos){
         String startd = null;
         if (a.getStartDate()!= null){
             startd = new SimpleDateFormat("yyyy-MM-dd").format(a.getStartDate());
         }
         String consulta = "INSERT INTO info " +
                 "(" +
                 "id, " +
                 "title, " +
                 "description," +
                 "programa, " +
                 "destacada, " +
                 "web, " +
                 "diasParaTerminar, " +
                 "tipoEntrada, " +
                 "precioEntrada, " +
                 "startDate, " +
                 "endDate, " +
                 "lat, " +
                 "lng) " +
                 "VALUES (" +
                 a.getId() +", '" +
                 formatSQL(a.getTitle()) +"', '" +
                 formatSQL(a.getDescription()) +"', '" +
                 formatSQL(a.getPrograma()) +"', " +
                 a.getDestacada() +", '" +
                 formatSQL(a.getWeb()) +"', " +
                 a.getDiasParaTerminar() +", '" +
                 formatSQL(a.getTipoEntrada()) +"', '" +
                 formatSQL(a.getPrecioEntrada()) +"', '" +
                 formatSQL(startd) +"', '" +
                 formatSQL(new SimpleDateFormat("yyyy-MM-dd").format(a.getEndDate())) +"', " +
                 a.getLat(true) + ", " +
                 a.getLng(true) + " " +
                 ");";
         Log.d("TAG consulta", consulta);
         //mDb.execSQL(consulta);
     }
    }

    private String formatSQL(String s){
        String aux = "";
        if(s != null){
            for(int i = 0; i<s.length(); i++){
                if(s.charAt(i)==42){
                    aux +="''";
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
