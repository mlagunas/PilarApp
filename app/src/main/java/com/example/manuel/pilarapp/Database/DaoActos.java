package com.example.manuel.pilarapp.Database;

import android.content.Context;

import com.example.manuel.pilarapp.Objects.Acto;

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

    public List<Acto> getActos()
    {
        ArrayList<Acto> actos = new ArrayList<Acto>();
        c = super.mDb.rawQuery("", null);
        if (c.moveToFirst()) {
            do {
                Acto a = new Acto();
                // do smthing
            } while (c.moveToNext());
        }
        return actos;
    }

    public List<Acto> getActos(Date date)
    {
        ArrayList<Acto> actos = new ArrayList<Acto>();
        c = super.mDb.rawQuery("", null);
        if (c.moveToFirst()) {
            do {
                Acto a = new Acto();
                
            } while (c.moveToNext());
        }
        return actos;
    }
}
