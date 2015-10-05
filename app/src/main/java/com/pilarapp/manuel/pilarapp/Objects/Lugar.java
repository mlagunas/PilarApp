package com.pilarapp.manuel.pilarapp.Objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lugar {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("cp")
    @Expose
    private String cp;
    @SerializedName("localidad")
    @Expose
    private String localidad;
    @SerializedName("provincia")
    @Expose
    private String provincia;
    @SerializedName("pais")
    @Expose
    private String pais;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("fax")
    @Expose
    private String fax;
    @SerializedName("mail")
    @Expose
    private String mail;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("autobuses")
    @Expose
    private String autobuses;
    @SerializedName("accesibilidad")
    @Expose
    private String accesibilidad;

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
     * @param accesibilidad
     * @param provincia
     * @param geometry
     */
    public Lugar(String id, String title, String direccion, String cp, String localidad, String provincia, String pais, String telefono, String fax, String mail, Geometry geometry, String autobuses, String accesibilidad) {
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
        this.accesibilidad = accesibilidad;
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
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     *
     * @param geometry
     * The geometry
     */
    public void setGeometry(Geometry geometry) {
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

    /**
     *
     * @return
     * The accesibilidad
     */
    public String getAccesibilidad() {
        return accesibilidad;
    }

    /**
     *
     * @param accesibilidad
     * The accesibilidad
     */
    public void setAccesibilidad(String accesibilidad) {
        this.accesibilidad = accesibilidad;
    }

}
