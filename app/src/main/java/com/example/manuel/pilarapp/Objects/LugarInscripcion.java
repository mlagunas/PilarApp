package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class LugarInscripcion {

    private String requisitosInscrip;

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
}
