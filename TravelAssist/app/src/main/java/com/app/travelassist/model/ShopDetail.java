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
    private String shopLatitude;
    private String shopLongitude;
    private String shopAddress;
    private String shopMobile;
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

    public String getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(String shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public String getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(String shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopMobile() {
        return shopMobile;
    }

    public void setShopMobile(String shopMobile) {
        this.shopMobile = shopMobile;
    }
}
