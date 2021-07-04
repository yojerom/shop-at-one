package com.one.shopone.model;

/**
 * Created by sathurshan on 8/3/2017.
 */

public class Trending_details {

    private String name;
    private String itemId;
    private String customerRating;
    private String largeImage;
    private String salePrice;
    private String msrp;

    public Trending_details(){}

    public Trending_details(String name, String itemId, String customerRating, String largeImage, String salePrice, String msrp, String brandName) {
        this.name = name;
        this.itemId = itemId;
        this.customerRating = customerRating;
        this.largeImage = largeImage;
        this.salePrice = salePrice;
        this.msrp = msrp;
        this.brandName = brandName;
    }

    private String brandName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCustomerRating() {
        return customerRating;
    }

    public void setCustomerRating(String customerRating) {
        this.customerRating = customerRating;
    }

    public String getLargeImage() {
        return largeImage;
    }

    public void setLargeImage(String largeImage) {
        this.largeImage = largeImage;
    }

    public String getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(String salePrice) {
        this.salePrice = salePrice;
    }

    public String getMsrp() { return msrp; }

    public void setMsrp(String msrp) { this.msrp = msrp; }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }


}
