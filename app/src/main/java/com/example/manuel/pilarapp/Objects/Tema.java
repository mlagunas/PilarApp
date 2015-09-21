package com.example.manuel.pilarapp.Objects;

/**
 * Created by Manuel on 20/09/2015.
 */
public class Tema {

    private int id;
    private String title;
    private String image;

    /**
     * No args constructor for use in serialization
     *
     */
    public Tema() {
    }

    /**
     *
     * @param id
     * @param title
     * @param image
     */
    public Tema(int id, String title, String image) {
        this.id = id;
        this.title = title;
        this.image = image;
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

    /**
     *
     * @return
     * The image
     */
    public String getImage() {
        return image;
    }

    /**
     *
     * @param image
     * The image
     */
    public void setImage(String image) {
        this.image = image;
    }
}
