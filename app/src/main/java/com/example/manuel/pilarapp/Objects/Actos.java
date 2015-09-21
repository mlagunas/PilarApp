package com.example.manuel.pilarapp.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 19/09/2015.
 */
public class Actos {

    private int id;
    private List<SubEvent> subEvent = new ArrayList<SubEvent>();
    private String title;
    private String description;
    private String programa;
    private boolean destacada;
    private String web;
    private List<Poblacion> poblacion = new ArrayList<Poblacion>();
    private LugarInscripcion lugarInscripcion;
    private String startDate;
    private String endDate;
    private Entidad entidad;
    private List<Tema> temas = new ArrayList<Tema>();
    private String lastUpdated;
    private Geometry_ geometry;
    private int diasParaTerminar;

    /**
     * No args constructor for use in serialization
     *
     */
    public Actos() {
    }

    /**
     *
     * @param startDate
     * @param poblacion
     * @param lastUpdated
     * @param web
     * @param endDate
     * @param geometry
     * @param id
     * @param programa
     * @param title
     * @param lugarInscripcion
     * @param description
     * @param entidad
     * @param diasParaTerminar
     * @param temas
     * @param subEvent
     * @param destacada
     */
    public Actos(int id, List<SubEvent> subEvent, String title, String description, String programa, boolean destacada, String web, List<Poblacion> poblacion, LugarInscripcion lugarInscripcion, String startDate, String endDate, Entidad entidad, List<Tema> temas, String lastUpdated, Geometry_ geometry, int diasParaTerminar) {
        this.id = id;
        this.subEvent = subEvent;
        this.title = title;
        this.description = description;
        this.programa = programa;
        this.destacada = destacada;
        this.web = web;
        this.poblacion = poblacion;
        this.lugarInscripcion = lugarInscripcion;
        this.startDate = startDate;
        this.endDate = endDate;
        this.entidad = entidad;
        this.temas = temas;
        this.lastUpdated = lastUpdated;
        this.geometry = geometry;
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
     * The subEvent
     */
    public List<SubEvent> getSubEvent() {
        return subEvent;
    }

    /**
     *
     * @param subEvent
     * The subEvent
     */
    public void setSubEvent(List<SubEvent> subEvent) {
        this.subEvent = subEvent;
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
     * The poblacion
     */
    public List<Poblacion> getPoblacion() {
        return poblacion;
    }

    /**
     *
     * @param poblacion
     * The poblacion
     */
    public void setPoblacion(List<Poblacion> poblacion) {
        this.poblacion = poblacion;
    }

    /**
     *
     * @return
     * The lugarInscripcion
     */
    public LugarInscripcion getLugarInscripcion() {
        return lugarInscripcion;
    }

    /**
     *
     * @param lugarInscripcion
     * The lugarInscripcion
     */
    public void setLugarInscripcion(LugarInscripcion lugarInscripcion) {
        this.lugarInscripcion = lugarInscripcion;
    }

    /**
     *
     * @return
     * The startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     *
     * @param startDate
     * The startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     *
     * @return
     * The endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     *
     * @param endDate
     * The endDate
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     *
     * @return
     * The entidad
     */
    public Entidad getEntidad() {
        return entidad;
    }

    /**
     *
     * @param entidad
     * The entidad
     */
    public void setEntidad(Entidad entidad) {
        this.entidad = entidad;
    }

    /**
     *
     * @return
     * The temas
     */
    public List<Tema> getTemas() {
        return temas;
    }

    /**
     *
     * @param temas
     * The temas
     */
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    /**
     *
     * @return
     * The lastUpdated
     */
    public String getLastUpdated() {
        return lastUpdated;
    }

    /**
     *
     * @param lastUpdated
     * The lastUpdated
     */
    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    /**
     *
     * @return
     * The geometry
     */
    public Geometry_ getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry_ geometry) {
        this.geometry = geometry;
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
