package com.example.manuel.pilarapp.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 19/09/2015.
 */
public class Actos {

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

    /**
     * No args constructor for use in serialization
     *
     */
    public Actos() {
    }

    public Actos(int id, List<SubEvent> subEvent, String title, String description, String programa, boolean destacada, String web, List<Poblacion> poblacion, LugarInscripcion lugarInscripcion, String startDate, String endDate, Entidad entidad, List<Tema> temas, String lastUpdated, Geometry_ geometry, int diasParaTerminar) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.programa = programa;
        this.destacada = destacada;
        this.web = web;
        this.diasParaTerminar = diasParaTerminar;
    }

    /**
     *
     * @return
     * The id
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     * The programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     *
     * @param programa
     * The programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     *
     * @return
     * The destacada
     */
    public boolean isDestacada() {
        return destacada;
    }

    /**
     *
     * @param destacada
     * The destacada
     */
    public void setDestacada(boolean destacada) {
        this.destacada = destacada;
    }

    /**
     *
     * @return
     * The web
     */
    public String getWeb() {
        return web;
    }

    /**
     *
     * @param web
     * The web
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     *
     * @return
     * The diasParaTerminar
     */
    public int getDiasParaTerminar() {
        return diasParaTerminar;
    }

    /**
     *
     * @param diasParaTerminar
     * The diasParaTerminar
     */
    public void setDiasParaTerminar(int diasParaTerminar) {
        this.diasParaTerminar = diasParaTerminar;
    }

}
