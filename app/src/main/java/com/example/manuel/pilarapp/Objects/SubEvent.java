
package com.example.manuel.pilarapp.Objects;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SubEvent {

    @SerializedName("horario")
    @Expose
    private String horario;
    @SerializedName("comentarios")
    @Expose
    private String comentarios;
    @SerializedName("horaInicio")
    @Expose
    private String horaInicio;
    @SerializedName("horaFinal")
    @Expose
    private String horaFinal;
    @SerializedName("openingHours")
    @Expose
    private List<OpeningHour> openingHours = new ArrayList<OpeningHour>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public SubEvent() {
    }

    /**
     * 
     * @param horaFinal
     * @param horario
     * @param horaInicio
     * @param openingHours
     * @param comentarios
     */
    public SubEvent(String horario, String comentarios, String horaInicio, String horaFinal, List<OpeningHour> openingHours) {
        this.horario = horario;
        this.comentarios = comentarios;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
        this.openingHours = openingHours;
    }

    /**
     * 
     * @return
     *     The horario
     */
    public String getHorario() {
        return horario;
    }

    /**
     * 
     * @param horario
     *     The horario
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }

    /**
     * 
     * @return
     *     The comentarios
     */
    public String getComentarios() {
        return comentarios;
    }

    /**
     * 
     * @param comentarios
     *     The comentarios
     */
    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    /**
     * 
     * @return
     *     The horaInicio
     */
    public String getHoraInicio() {
        return horaInicio;
    }

    /**
     * 
     * @param horaInicio
     *     The horaInicio
     */
    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    /**
     * 
     * @return
     *     The horaFinal
     */
    public String getHoraFinal() {
        return horaFinal;
    }

    /**
     * 
     * @param horaFinal
     *     The horaFinal
     */
    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    /**
     * 
     * @return
     *     The openingHours
     */
    public List<OpeningHour> getOpeningHours() {
        return openingHours;
    }

    /**
     * 
     * @param openingHours
     *     The openingHours
     */
    public void setOpeningHours(List<OpeningHour> openingHours) {
        this.openingHours = openingHours;
    }

}
