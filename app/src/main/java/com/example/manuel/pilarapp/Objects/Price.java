
package com.example.manuel.pilarapp.Objects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Price{

    @SerializedName("fareGroup")
    @Expose
    private String fareGroup;
    @SerializedName("hasCurrencyValue")
    @Expose
    private Integer hasCurrencyValue;
    @SerializedName("hasCurrency")
    @Expose
    private String hasCurrency;
    @SerializedName("minSize")
    @Expose
    private String minSize;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Price() {
    }

    /**
     * 
     * @param minSize
     * @param hasCurrencyValue
     * @param hasCurrency
     * @param fareGroup
     */
    public Price(String fareGroup, Integer hasCurrencyValue, String hasCurrency, String minSize) {
        this.fareGroup = fareGroup;
        this.hasCurrencyValue = hasCurrencyValue;
        this.hasCurrency = hasCurrency;
        this.minSize = minSize;
    }

    /**
     * 
     * @return
     *     The fareGroup
     */
    public String getFareGroup() {
        return fareGroup;
    }

    /**
     * 
     * @param fareGroup
     *     The fareGroup
     */
    public void setFareGroup(String fareGroup) {
        this.fareGroup = fareGroup;
    }

    /**
     * 
     * @return
     *     The hasCurrencyValue
     */
    public Integer getHasCurrencyValue() {
        return hasCurrencyValue;
    }

    /**
     * 
     * @param hasCurrencyValue
     *     The hasCurrencyValue
     */
    public void setHasCurrencyValue(Integer hasCurrencyValue) {
        this.hasCurrencyValue = hasCurrencyValue;
    }

    /**
     * 
     * @return
     *     The hasCurrency
     */
    public String getHasCurrency() {
        return hasCurrency;
    }

    /**
     * 
     * @param hasCurrency
     *     The hasCurrency
     */
    public void setHasCurrency(String hasCurrency) {
        this.hasCurrency = hasCurrency;
    }

    /**
     * 
     * @return
     *     The minSize
     */
    public String getMinSize() {
        return minSize;
    }

    /**
     * 
     * @param minSize
     *     The minSize
     */
    public void setMinSize(String minSize) {
        this.minSize = minSize;
    }

}
