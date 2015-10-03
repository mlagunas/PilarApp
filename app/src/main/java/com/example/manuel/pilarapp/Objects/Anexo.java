
package com.example.manuel.pilarapp.Objects;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Anexo{

    @SerializedName("codAnexo")
    @Expose
    private Integer codAnexo;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("documento")
    @Expose
    private String documento;
    @SerializedName("media_name")
    @Expose
    private String mediaName;
    @SerializedName("media_description")
    @Expose
    private String mediaDescription;
    @SerializedName("media_body")
    @Expose
    private String mediaBody;
    @SerializedName("sonido")
    @Expose
    private String sonido;
    @SerializedName("nombreDocumento")
    @Expose
    private String nombreDocumento;
    @SerializedName("imagen2")
    @Expose
    private String imagen2;
    @SerializedName("nombreDocumento2")
    @Expose
    private String nombreDocumento2;
    @SerializedName("video")
    @Expose
    private String video;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Anexo() {
    }

    /**
     * 
     * @param imagen2
     * @param imagen
     * @param nombreDocumento
     * @param sonido
     * @param mediaBody
     * @param codAnexo
     * @param nombreDocumento2
     * @param mediaName
     * @param mediaDescription
     * @param documento
     * @param video
     */
    public Anexo(Integer codAnexo, String imagen, String documento, String mediaName, String mediaDescription, String mediaBody, String sonido, String nombreDocumento, String imagen2, String nombreDocumento2, String video) {
        this.codAnexo = codAnexo;
        this.imagen = imagen;
        this.documento = documento;
        this.mediaName = mediaName;
        this.mediaDescription = mediaDescription;
        this.mediaBody = mediaBody;
        this.sonido = sonido;
        this.nombreDocumento = nombreDocumento;
        this.imagen2 = imagen2;
        this.nombreDocumento2 = nombreDocumento2;
        this.video = video;
    }

    /**
     * 
     * @return
     *     The codAnexo
     */
    public Integer getCodAnexo() {
        return codAnexo;
    }

    /**
     * 
     * @param codAnexo
     *     The codAnexo
     */
    public void setCodAnexo(Integer codAnexo) {
        this.codAnexo = codAnexo;
    }

    /**
     * 
     * @return
     *     The imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * 
     * @param imagen
     *     The imagen
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * 
     * @return
     *     The documento
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * 
     * @param documento
     *     The documento
     */
    public void setDocumento(String documento) {
        this.documento = documento;
    }

    /**
     * 
     * @return
     *     The mediaName
     */
    public String getMediaName() {
        return mediaName;
    }

    /**
     * 
     * @param mediaName
     *     The media_name
     */
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    /**
     * 
     * @return
     *     The mediaDescription
     */
    public String getMediaDescription() {
        return mediaDescription;
    }

    /**
     * 
     * @param mediaDescription
     *     The media_description
     */
    public void setMediaDescription(String mediaDescription) {
        this.mediaDescription = mediaDescription;
    }

    /**
     * 
     * @return
     *     The mediaBody
     */
    public String getMediaBody() {
        return mediaBody;
    }

    /**
     * 
     * @param mediaBody
     *     The media_body
     */
    public void setMediaBody(String mediaBody) {
        this.mediaBody = mediaBody;
    }

    /**
     * 
     * @return
     *     The sonido
     */
    public String getSonido() {
        return sonido;
    }

    /**
     * 
     * @param sonido
     *     The sonido
     */
    public void setSonido(String sonido) {
        this.sonido = sonido;
    }

    /**
     * 
     * @return
     *     The nombreDocumento
     */
    public String getNombreDocumento() {
        return nombreDocumento;
    }

    /**
     * 
     * @param nombreDocumento
     *     The nombreDocumento
     */
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    /**
     * 
     * @return
     *     The imagen2
     */
    public String getImagen2() {
        return imagen2;
    }

    /**
     * 
     * @param imagen2
     *     The imagen2
     */
    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    /**
     * 
     * @return
     *     The nombreDocumento2
     */
    public String getNombreDocumento2() {
        return nombreDocumento2;
    }

    /**
     * 
     * @param nombreDocumento2
     *     The nombreDocumento2
     */
    public void setNombreDocumento2(String nombreDocumento2) {
        this.nombreDocumento2 = nombreDocumento2;
    }

    /**
     * 
     * @return
     *     The video
     */
    public String getVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(String video) {
        this.video = video;
    }

}
