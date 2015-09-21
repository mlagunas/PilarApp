package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class Lugar {


    private String id;
    private String title;
    private String direccion;
    private String cp;
    private String localidad;
    private String provincia;
    private String pais;
    private String telefono;
    private String fax;
    private String mail;
    private Geometry_ geometry;
    private String autobuses;

    /**
     * No args constructor for use in serialization
     *
     */
    public Lugar() {
    }

    /**
     *
     * @param id
     * @param mail
     * @param localidad
     * @param direccion
     * @param title
     * @param fax
     * @param cp
     * @param pais
     * @param telefono
     * @param autobuses
     * @param provincia
     * @param geometry
     */
    public Lugar(String id, String title, String direccion, String cp, String localidad, String provincia, String pais, String telefono, String fax, String mail, Geometry_ geometry, String autobuses) {
        this.id = id;
        this.title = title;
        this.direccion = direccion;
        this.cp = cp;
        this.localidad = localidad;
        this.provincia = provincia;
        this.pais = pais;
        this.telefono = telefono;
        this.fax = fax;
        this.mail = mail;
        this.geometry = geometry;
        this.autobuses = autobuses;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
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
     * The direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     *
     * @param direccion
     * The direccion
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     *
     * @return
     * The cp
     */
    public String getCp() {
        return cp;
    }

    /**
     *
     * @param cp
     * The cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

    /**
     *
     * @return
     * The localidad
     */
    public String getLocalidad() {
        return localidad;
    }

    /**
     *
     * @param localidad
     * The localidad
     */
    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    /**
     *
     * @return
     * The provincia
     */
    public String getProvincia() {
        return provincia;
    }

    /**
     *
     * @param provincia
     * The provincia
     */
    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    /**
     *
     * @return
     * The pais
     */
    public String getPais() {
        return pais;
    }

    /**
     *
     * @param pais
     * The pais
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     *
     * @return
     * The telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     *
     * @param telefono
     * The telefono
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     *
     * @return
     * The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     * The fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     * The mail
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     * The mail
     */
    public void setMail(String mail) {
        this.mail = mail;
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
     * The autobuses
     */
    public String getAutobuses() {
        return autobuses;
    }

    /**
     *
     * @param autobuses
     * The autobuses
     */
    public void setAutobuses(String autobuses) {
        this.autobuses = autobuses;
    }
}
