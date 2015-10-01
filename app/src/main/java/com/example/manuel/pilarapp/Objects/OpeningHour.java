
package com.example.manuel.pilarapp.Objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OpeningHour {

    @SerializedName("dia")
    @Expose
    private String dia;
    @SerializedName("horaApertura")
    @Expose
    private String horaApertura;
    @SerializedName("horaCierre")
    @Expose
    private String horaCierre;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OpeningHour() {
    }

    /**
     * 
     * @param dia
     * @param horaApertura
     * @param horaCierre
     */
    public OpeningHour(String dia, String horaApertura, String horaCierre) {
        this.dia = dia;
        this.horaApertura = horaApertura;
        this.horaCierre = horaCierre;
    }

    /**
     * 
     * @return
     *     The dia
     */
    public String getDia() {
        return dia;
    }

    /**
     * 
     * @param dia
     *     The dia
     */
    public void setDia(String dia) {
        this.dia = dia;
    }

    /**
     * 
     * @return
     *     The horaApertura
     */
    public String getHoraApertura() {
        return horaApertura;
    }

    /**
     * 
     * @param horaApertura
     *     The horaApertura
     */
    public void setHoraApertura(String horaApertura) {
        this.horaApertura = horaApertura;
    }

    /**
     * 
     * @return
     *     The horaCierre
     */
    public String getHoraCierre() {
        return horaCierre;
    }

    /**
     * 
     * @param horaCierre
     *     The horaCierre
     */
    public void setHoraCierre(String horaCierre) {
        this.horaCierre = horaCierre;
    }

}
