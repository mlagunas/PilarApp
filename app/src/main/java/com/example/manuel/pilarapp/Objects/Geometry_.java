package com.example.manuel.pilarapp.Objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 20/09/2015.
 */
public class Geometry_ {

    private String type;
    private List<Float> coordinates = new ArrayList<Float>();

    /**
     *
     * @param type
     * @param coordinates
     */
    public Geometry_(String type, List<Float> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The coordinates
     */
    public List<Float> getCoordinates() {
        return coordinates;
    }

    /**
     *
     * @param coordinates
     * The coordinates
     */
    public void setCoordinates(List<Float> coordinates) {
        this.coordinates = coordinates;
    }
}
