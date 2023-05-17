/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namtn.dto;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class Plant implements Serializable{
    private int plantId;
    private String plantName;
    private int price;
    private String imgpath;
    private String description;
    private int status;
    private int cateID;
    private String cateName;

    public Plant() {
    }

    public Plant(int plantId, String plantName, int price, String imgpath, String description, int status, int cateID, String cateName) {
        this.plantId = plantId;
        this.plantName = plantName;
        this.price = price;
        this.imgpath = imgpath;
        this.description = description;
        this.status = status;
        this.cateID = cateID;
        this.cateName = cateName;
    }

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgpath() {
        return imgpath;
    }

    public void setImgpath(String imgpath) {
        this.imgpath = imgpath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    
    
}
