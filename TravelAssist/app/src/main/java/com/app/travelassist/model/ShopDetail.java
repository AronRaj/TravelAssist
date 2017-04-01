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
    private double shopLatitude;
    private double shopLongitude;
    private String shopAddress;
    private String shopMobile;
    private String shopRating;
    private String shopTotalRated;
    private String shopStatus;
    private String shopImageUrl;

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

    public double getShopLatitude() {
        return shopLatitude;
    }

    public void setShopLatitude(double shopLatitude) {
        this.shopLatitude = shopLatitude;
    }

    public double getShopLongitude() {
        return shopLongitude;
    }

    public void setShopLongitude(double shopLongitude) {
        this.shopLongitude = shopLongitude;
    }

    public String getShopRating() {
        return shopRating;
    }

    public void setShopRating(String shopRating) {
        if(null!=shopRating) {
            this.shopRating = shopRating;
        }else{
            this.shopRating="0.0";
        }
    }

    public String getShopTotalRated() {
        return shopTotalRated;
    }

    public void setShopTotalRated(String shopTotalRated) {
        if(null!=shopTotalRated) {
            this.shopTotalRated = shopTotalRated;
        }else{
            this.shopTotalRated="0";
        }
    }

    public String getShopStatus() {
        return shopStatus;
    }

    public void setShopStatus(String shopStatus) {
        this.shopStatus = shopStatus;
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

    public String getShopImageUrl() {
        return shopImageUrl;
    }

    public void setShopImageUrl(String shopImageUrl) {
        this.shopImageUrl = shopImageUrl;
    }
}
