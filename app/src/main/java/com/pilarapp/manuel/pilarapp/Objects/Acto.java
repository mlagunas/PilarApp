
package com.pilarapp.manuel.pilarapp.Objects;


import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acto {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("subEvent")
    @Expose
    private List<SubEvent> subEvent = new ArrayList<SubEvent>();
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("programa")
    @Expose
    private String programa;
    @SerializedName("citaDestacada")
    @Expose
    private String citaDestacada;
    @SerializedName("destacada")
    @Expose
    private Boolean destacada;
    @SerializedName("orden")
    @Expose
    private Integer orden;
    @SerializedName("web")
    @Expose
    private String web;
    @SerializedName("twitter")
    @Expose
    private String twitter;
    @SerializedName("facebook")
    @Expose
    private String facebook;
    @SerializedName("poblacion")
    @Expose
    private List<Poblacion> poblacion = new ArrayList<Poblacion>();
    @SerializedName("festividadLocal")
    @Expose
    private String festividadLocal;
    @SerializedName("barrerasArquit")
    @Expose
    private String barrerasArquit;
    @SerializedName("prepDiscapacidad")
    @Expose
    private String prepDiscapacidad;
    @SerializedName("tipoEntrada")
    @Expose
    private String tipoEntrada;
    @SerializedName("precioEntrada")
    @Expose
    private String precioEntrada;
    @SerializedName("comentariosEntrada")
    @Expose
    private String comentariosEntrada;
    @SerializedName("subtemas")
    @Expose
    private List<Subtema> subtemas = new ArrayList<Subtema>();
    @SerializedName("subtemasAJ")
    @Expose
    private List<SubtemasAJ> subtemasAJ = new ArrayList<SubtemasAJ>();
    @SerializedName("temas")
    @Expose
    private List<Tema> temas = new ArrayList<Tema>();
    @SerializedName("permanente")
    @Expose
    private String permanente;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("descripcion2")
    @Expose
    private String descripcion2;
    @SerializedName("price")
    @Expose
    private List<Price> price = new ArrayList<Price>();
    @SerializedName("anexo")
    @Expose
    private List<Anexo> anexo = new ArrayList<Anexo>();
    @SerializedName("diasParaTerminar")
    @Expose
    private Integer diasParaTerminar;
    @SerializedName("formatoActividad")
    @Expose
    private String formatoActividad;
    @SerializedName("geometry")
    @Expose
    private Geometry geometry;
    @SerializedName("startDate")
    @Expose
    private Date startDate;
    @SerializedName("endDate")
    @Expose
    private Date endDate;

    private double lat;
    private double lng;

    private String tema;
    private String subtema;

    private String horaInicio;
    private String horaFinal;

    private String addressInfo;
    private String address;
    private String buses;

    private String imagen;

    /**
     * No args constructor for use in serialization
     */
    public Acto() {
        this.lat = -1;
        this.lng = -1;
        this.tema = "";
        this.subtema = "";
        this.horaFinal = "";
        this.horaInicio = "";
        this.buses = "";
        this.address = "";
        this.addressInfo = "";
        this.image = "";
    }

    /**
     * @param poblacion
     * @param formatoActividad
     * @param descripcion2
     * @param barrerasArquit
     * @param anexo
     * @param permanente
     * @param prepDiscapacidad
     * @param image
     * @param tipoEntrada
     * @param web
     * @param festividadLocal
     * @param id
     * @param programa
     * @param citaDestacada
     * @param orden
     * @param twitter
     * @param title
     * @param price
     * @param description
     * @param facebook
     * @param diasParaTerminar
     * @param subtemasAJ
     * @param subtemas
     * @param comentariosEntrada
     * @param precioEntrada
     * @param temas
     * @param subEvent
     * @param destacada
     * @param geometry
     */
    public Acto(Integer id, List<SubEvent> subEvent, String title, String description, String programa, String citaDestacada, Boolean destacada, Integer orden, String web,
                String twitter, String facebook, List<Poblacion> poblacion, String festividadLocal, String barrerasArquit, String prepDiscapacidad,
                String tipoEntrada, String precioEntrada, String comentariosEntrada, List<Subtema> subtemas, List<SubtemasAJ> subtemasAJ, List<Tema> temas, String permanente,
                String image, String descripcion2, List<Price> price, List<Anexo> anexo, Geometry geometry, Integer diasParaTerminar, String formatoActividad,
                Date startDate, Date endDate) {
        this.id = id;
        this.subEvent = subEvent;
        this.title = title;
        this.description = description;
        this.programa = programa;
        this.citaDestacada = citaDestacada;
        this.destacada = destacada;
        this.orden = orden;
        this.web = web;
        this.twitter = twitter;
        this.facebook = facebook;
        this.poblacion = poblacion;
        this.festividadLocal = festividadLocal;
        this.barrerasArquit = barrerasArquit;
        this.prepDiscapacidad = prepDiscapacidad;
        this.tipoEntrada = tipoEntrada;
        this.precioEntrada = precioEntrada;
        this.comentariosEntrada = comentariosEntrada;
        this.subtemas = subtemas;
        this.subtemasAJ = subtemasAJ;
        this.temas = temas;
        this.permanente = permanente;
        this.image = image;
        this.descripcion2 = descripcion2;
        this.price = price;
        this.anexo = anexo;
        this.geometry = geometry;
        this.diasParaTerminar = diasParaTerminar;
        this.formatoActividad = formatoActividad;
        this.startDate = startDate;
        this.endDate = endDate;
        this.lat = -1;
        this.lng = -1;
        this.tema = "";
        this.subtema = "";
        this.horaFinal = "";
        this.horaInicio = "";
        this.buses = "";
        this.address = "";
        this.addressInfo = "";
        this.image = "";
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The subEvent
     */
    public List<SubEvent> getSubEvent() {
        return subEvent;
    }

    /**
     * @param subEvent The subEvent
     */
    public void setSubEvent(List<SubEvent> subEvent) {
        this.subEvent = subEvent;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return The programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * @param programa The programa
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * @return The citaDestacada
     */
    public String getCitaDestacada() {
        return citaDestacada;
    }

    /**
     * @param citaDestacada The citaDestacada
     */
    public void setCitaDestacada(String citaDestacada) {
        this.citaDestacada = citaDestacada;
    }

    /**
     * @return The destacada
     */
    public Boolean getDestacada() {
        return destacada;
    }

    /**
     * @param destacada The destacada
     */
    public void setDestacada(Boolean destacada) {
        this.destacada = destacada;
    }

    /**
     * @return The orden
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * @param orden The orden
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    /**
     * @return The web
     */
    public String getWeb() {
        return web;
    }

    /**
     * @param web The web
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * @return The twitter
     */
    public String getTwitter() {
        return twitter;
    }

    /**
     * @param twitter The twitter
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    /**
     * @return The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     * @param facebook The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     * @return The poblacion
     */
    public List<Poblacion> getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion The poblacion
     */
    public void setPoblacion(List<Poblacion> poblacion) {
        this.poblacion = poblacion;
    }

    /**
     * @return The festividadLocal
     */
    public String getFestividadLocal() {
        return festividadLocal;
    }

    /**
     * @param festividadLocal The festividadLocal
     */
    public void setFestividadLocal(String festividadLocal) {
        this.festividadLocal = festividadLocal;
    }

    /**
     * @return The barrerasArquit
     */
    public String getBarrerasArquit() {
        return barrerasArquit;
    }

    /**
     * @param barrerasArquit The barrerasArquit
     */
    public void setBarrerasArquit(String barrerasArquit) {
        this.barrerasArquit = barrerasArquit;
    }

    /**
     * @return The prepDiscapacidad
     */
    public String getPrepDiscapacidad() {
        return prepDiscapacidad;
    }

    /**
     * @param prepDiscapacidad The prepDiscapacidad
     */
    public void setPrepDiscapacidad(String prepDiscapacidad) {
        this.prepDiscapacidad = prepDiscapacidad;
    }

    /**
     * @return The tipoEntrada
     */
    public String getTipoEntrada() {
        return tipoEntrada;
    }

    /**
     * @param tipoEntrada The tipoEntrada
     */
    public void setTipoEntrada(String tipoEntrada) {
        this.tipoEntrada = tipoEntrada;
    }

    /**
     * @return The precioEntrada
     */
    public String getPrecioEntrada() {
        return precioEntrada;
    }

    /**
     * @param precioEntrada The precioEntrada
     */
    public void setPrecioEntrada(String precioEntrada) {
        this.precioEntrada = precioEntrada;
    }

    /**
     * @return The comentariosEntrada
     */
    public String getComentariosEntrada() {
        return comentariosEntrada;
    }

    /**
     * @param comentariosEntrada The comentariosEntrada
     */
    public void setComentariosEntrada(String comentariosEntrada) {
        this.comentariosEntrada = comentariosEntrada;
    }

    /**
     * @return The subtemas
     */
    public List<Subtema> getSubtemas() {
        return subtemas;
    }

    /**
     * @param subtemas The subtemas
     */
    public void setSubtemas(List<Subtema> subtemas) {
        this.subtemas = subtemas;
    }

    /**
     * @return The subtemasAJ
     */
    public List<SubtemasAJ> getSubtemasAJ() {
        return subtemasAJ;
    }

    /**
     * @param subtemasAJ The subtemasAJ
     */
    public void setSubtemasAJ(List<SubtemasAJ> subtemasAJ) {
        this.subtemasAJ = subtemasAJ;
    }

    /**
     * @return The temas
     */
    public List<Tema> getTemas() {
        return temas;
    }

    /**
     * @param temas The temas
     */
    public void setTemas(List<Tema> temas) {
        this.temas = temas;
    }

    /**
     * @return The permanente
     */
    public String getPermanente() {
        return permanente;
    }

    /**
     * @param permanente The permanente
     */
    public void setPermanente(String permanente) {
        this.permanente = permanente;
    }

    /**
     * @return The image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image The image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return The descripcion2
     */
    public String getDescripcion2() {
        return descripcion2;
    }

    /**
     * @param descripcion2 The descripcion2
     */
    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    /**
     * @return The price
     */
    public List<Price> getPrice() {
        return price;
    }

    /**
     * @param price The price
     */
    public void setPrice(List<Price> price) {
        this.price = price;
    }

    /**
     * @return The anexo
     */
    public List<Anexo> getAnexo() {
        return anexo;
    }

    /**
     * @param anexo The anexo
     */
    public void setAnexo(List<Anexo> anexo) {
        this.anexo = anexo;
    }

    /**
     * @return geometry
     * The coordinates
     */
    public Geometry getGeometry() {
        return geometry;
    }

    /**
     * @param geometry The coordinates
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    /**
     * @return The diasParaTerminar
     */
    public Integer getDiasParaTerminar() {
        return diasParaTerminar;
    }

    /**
     * @param diasParaTerminar The diasParaTerminar
     */
    public void setDiasParaTerminar(Integer diasParaTerminar) {
        this.diasParaTerminar = diasParaTerminar;
    }

    /**
     * @return The formatoActividad
     */
    public String getFormatoActividad() {
        return formatoActividad;
    }

    /**
     * @param formatoActividad The formatoActividad
     */
    public void setFormatoActividad(String formatoActividad) {
        this.formatoActividad = formatoActividad;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String date) {
        this.startDate = parseDate(date);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String date) {
        this.endDate = parseDate(date);
    }

    public double getLng() {

        if (geometry != null) {
            lng = geometry.getCoordinates().get(1);
            return lng;
        } else return lng;

    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        if (geometry != null) {
            lat = geometry.getCoordinates().get(0);
            return lat;
        } else return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    private Date parseDate(String date) {
        Date parsed = new Date();
        try {
            SimpleDateFormat format =
                    new SimpleDateFormat("yyyy-mm-dd"); //EEE MMM dd HH:mm:ss zzz yyyy
            parsed = format.parse(date);

        } catch (ParseException pe) {
            return null;
            //throw new IllegalArgumentException();
        }
        return parsed;
    }

    public String getTema() {
        String result = "";

        for (int i = 0; i<temas.size();i++) {
            if (i != 0)
                result += "-";

            if (temas.get(i).getTitle() != null &&
                    (temas.get(i).getTitle()).equals(""))
                result += tema;
            else result += temas.get(i).getTitle();
        }
        return result;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getSubtema() {
        if (subtemas != null && subtemas.size() > 0) {
            if (subtemas.get(0).getTitle() != null &&
                    (subtemas.get(0).getTitle()).equals(""))
                return subtema;
            else
                return subtemas.get(0).getTitle();
        } else return subtema;

    }

    public void setSubtema(String subtema) {
        this.subtema = subtema;
    }

    public String getHoraInicio() {

        if (subEvent != null && subEvent.size() > 0) {
            if (subEvent.get(0).getHoraInicio() != null &&
                    (subEvent.get(0).getHoraInicio()).equals(""))
                return horaInicio;
            else
                return subEvent.get(0).getHoraInicio();
        } else return horaInicio;

    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {


        if (subEvent != null && subEvent.size() > 0) {
            if (subEvent.get(0).getHoraFinal() != null &&
                    (subEvent.get(0).getHoraFinal()).equals(""))
                return horaFinal;
            else
                return subEvent.get(0).getHoraFinal();
        } else return horaFinal;

    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }

    public String getAddressInfo() {
        if (subEvent != null && subEvent.size() > 0 &&
                subEvent.get(0).getLugar() != null) {
            if (subEvent.get(0).getLugar().getTitle() != null &&
                    (subEvent.get(0).getLugar().getTitle()).equals(""))
                return addressInfo;
            else
                return subEvent.get(0).getLugar().getTitle();
        } else return addressInfo;
    }

    public void setAddressInfo(String addressInfo) {
        this.addressInfo = addressInfo;
    }

    public String getAddress() {
        if (subEvent != null && subEvent.size() > 0 &&
                subEvent.get(0).getLugar() != null) {
            if (subEvent.get(0).getLugar().getDireccion() != null &&
                    (subEvent.get(0).getLugar().getDireccion() + ", " + subEvent.get(0).getLugar().getCp()).equals(""))
                return address;
            else
                return subEvent.get(0).getLugar().getDireccion();
        } else return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBuses() {
        if (subEvent != null && subEvent.size() > 0 &&
                subEvent.get(0).getLugar() != null) {
            if (subEvent.get(0).getLugar().getAutobuses() != null &&
                    (subEvent.get(0).getLugar().getAutobuses()).equals(""))
                return buses;
            else return subEvent.get(0).getLugar().getAutobuses();
        } else return buses;
    }

    public void setBuses(String buses) {

        this.buses = buses;
    }

    public String getImagen() {
        if(anexo  != null && anexo.size()>0 && anexo.get(0)!=null && anexo.get(0).getImagen()!="")
            return anexo.get(0).getImagen();
        else
            return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
