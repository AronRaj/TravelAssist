package com.app.travelassist.model;

/**
 * Created by Aron on 19-03-2017.
 */

public class ShopDetail {
    private String shopId;
    private String shopName;
    private String shopType;
    private String shopCuisine;
    private double distance;
    private String shopRating;
    private String shopStatus;

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopType() {
        return shopType;
    }

    public void setShopType(String shopType) {
        this.shopType = shopType;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopCuisine() {
        return shopCuisine;
    }

    public void setShopCuisine(String shopCuisine) {
        this.shopCuisine = shopCuisine;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getShopRating() {
        return shopRating;
    }

    public void setShopRating(String shopRating) {
        this.shopRating = shopRating;
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
    }
}
