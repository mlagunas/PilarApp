package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class SubEvent {

    private Lugar lugar;
    private String comentarios;
    private String fechaInicio;
    private String fechaFinal;

    /**
     * No args constructor for use in serialization
     *
     */
    public SubEvent() {
    }

    /**
     *
     * @param fechaFinal
     * @param comentarios
     * @param lugar
     * @param fechaInicio
     */
    public SubEvent(Lugar lugar, String comentarios, String fechaInicio, String fechaFinal) {
        this.lugar = lugar;
        this.comentarios = comentarios;
        this.fechaInicio = fechaInicio;
        this.fechaFinal = fechaFinal;
    }

    /**
     *
     * @return
     * The lugar
     */
    public Lugar getLugar() {
        return lugar;
    }

    /**
     *
     * @param lugar
     * The lugar
     */
    public void setLugar(Lugar lugar) {
        this.lugar = lugar;
    }

    /**
     *
     * @return
     * The comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     *
     * @param comentarios
     * The comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     *
     * @return
     * The fechaInicio
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     *
     * @param fechaInicio
     * The fechaInicio
     */
    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     *
     * @return
     * The fechaFinal
     */
    public String getFechaFinal() {
        return fechaFinal;
    }

    /**
     *
     * @param fechaFinal
     * The fechaFinal
     */
    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }
}
