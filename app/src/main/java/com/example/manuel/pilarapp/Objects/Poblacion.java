package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class Poblacion {

    private int id;
    private String title;

    /**
     * No args constructor for use in serialization
     *
     */
    public Poblacion() {
    }

    /**
     *
     * @param id
     * @param title
     */
    public Poblacion(int id, String title) {
        this.id = id;
        this.title = title;
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

}
