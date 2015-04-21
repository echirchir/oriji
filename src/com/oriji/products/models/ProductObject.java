package com.oriji.products.models;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is a ListViewItem model - represents the item displayed
 * in a list view for users to see and click. POJO.
 */
public class ProductObject {

    private String pCategory;
    private String pName;
    private String pYear;
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public ProductObject(){}

    public ProductObject(String name, String category, String year){

        this.pName = name;
        this.pCategory = category;
        this.pYear = year;
    }

    public String getpName() {
        return pName;
    }

    public String getpCategory() {
        return pCategory;
    }

    public String getpYear() {
        return pYear;
    }

    public void setpCategory(String pCategory) {
        this.pCategory = pCategory;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public void setpYear(String pYear) {
        this.pYear = pYear;
    }


}
