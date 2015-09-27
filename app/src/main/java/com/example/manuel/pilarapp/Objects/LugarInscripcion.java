package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class LugarInscripcion {

    private String requisitosInscrip;
    private String telefonoInscrip;
    private String webInscip;



    /**
     * No args constructor for use in serialization
     *
     */
    public LugarInscripcion() {
    }

    /**
     *
     * @param requisitosInscrip
     */
    public LugarInscripcion(String requisitosInscrip) {
        this.requisitosInscrip = requisitosInscrip;
    }

    /**
     *
     * @return
     * The requisitosInscrip
     */
    public String getRequisitosInscrip() {
        return requisitosInscrip;
    }

    /**
     *
     * @param requisitosInscrip
     * The requisitosInscrip
     */
    public void setRequisitosInscrip(String requisitosInscrip) {
        this.requisitosInscrip = requisitosInscrip;
    }

    public String getWebInscip() {
        return webInscip;
    }

    public void setWebInscip(String webInscip) {
        this.webInscip = webInscip;
    }

    public String getTelefonoInscrip() {
        return telefonoInscrip;
    }

    public void setTelefonoInscrip(String telefonoInscrip) {
        this.telefonoInscrip = telefonoInscrip;
    }
}
