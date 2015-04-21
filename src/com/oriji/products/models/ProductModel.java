package com.oriji.products.models;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Cache;
import com.activeandroid.Model;
import com.activeandroid.TableInfo;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This is an ActiveAndroid model for a single product to be stored
 * in the sqlite database table for this application
 *
 * Refer to "http://www.activeandroid.com/" online documentation for more
 */

@Table(name = "products")
public class ProductModel extends Model{

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "serialNumber")
    private String serialNumber;

    @Column(name = "manufacturedDate")
    private String manufacturedDate;

    public ProductModel(){}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getManufacturedDate() {
        return manufacturedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setManufacturedDate(String manufacturedDate) {
        this.manufacturedDate = manufacturedDate;
    }

    /**
     *
     * @return all the products from the table
     */
    public static List<ProductModel> fetchAll()
    {
        return new Select()
                .all()
                .from(ProductModel.class)
                .orderBy("manufacturedDate DESC")
                .execute();
    }

    /**
     *
     * @param id product id to be fetched
     * @return the product whose id is provided above
     */
    public static ProductModel fetchProduct(long id){
        return new Select()
                .from(ProductModel.class)
                .where("id = ?", id)
                .executeSingle();
    }

    /**
     *
     * @return the deleted products from table;
     * Remember this does not truncate the table so
     * ids are not reset as in the below method
     */
    public static List<ProductModel> deleteProducts(){

        return new Delete()
                .from(ProductModel.class)
                .execute();
    }

    /**
     * This method truncates the entire table
     * New data entries will have fresh ids startinf from 1
     * This method might not be available in current ActiveAndroid library
     * @param type the model class to be passed in
     */
    public static void truncate(Class<? extends Model> type){
        TableInfo tableInfo = Cache.getTableInfo(type);
        ActiveAndroid.execSQL("delete from " + tableInfo.getTableName() + ";");
        ActiveAndroid.execSQL("delete from sqlite_sequence where name='"+tableInfo.getTableName()+"';");
    }

}
