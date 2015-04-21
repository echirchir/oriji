package com.oriji.products.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This is a POJO class that represents a single Product
 * Matches the JSON fields returned by the GET request for a single item
 *
 * Generated from "jsonschema2pojo.org" online tool.
 */
public class ProductItem {

    @Expose
    private String name;
    @Expose
    private String description;
    @Expose
    private String type;
    @Expose
    private String category;
    @SerializedName("serial_number")
    @Expose
    private String serialNumber;
    @SerializedName("manufactured_date")
    @Expose
    private String manufacturedDate;

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     *
     * @return
     * The serialNumber
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     *
     * @param serialNumber
     * The serial_number
     */
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     *
     * @return
     * The manufacturedDate
     */
    public String getManufacturedDate() {
        return manufacturedDate;
    }

    /**
     *
     * @param manufacturedDate
     * The manufactured_date
     */
    public void setManufacturedDate(String manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

}

