/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;

/**
 *
 * @author ryan2
 */
public class Entry {
    
    private String Poster;
    private String ItemName;
    private int ItemCount;
    private Date ExpDate;
    private String OfferType;
    private String UnitPrice;
    
    public String getPoster(){
        return Poster;
    }
    public void setPoster(String in){
        this.Poster = in;
    }
    
    public String getItemName(){
        return ItemName;
    }
    public void setItemName(String in){
        this.ItemName = in;
    }
    
    public int getItemCount(){
        return ItemCount;
    }
    public void setItemCount(int in){
        this.ItemCount = in;
    }
    
    public Date getExpDate(){
        return ExpDate;
    }
    public void setExpDate(Date in){
        this.ExpDate = in;
    }
    
    public String getOfferType(){
        return OfferType;
    }
    public void setOfferType(String in){
        this.OfferType = in;
    }
    
    public String getUnitPrice(){
        return UnitPrice;
    }
    public void setUnitPrice(String in){
        this.UnitPrice = in;
    }
}
