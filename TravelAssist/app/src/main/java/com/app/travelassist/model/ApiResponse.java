package com.app.travelassist.model;

import java.util.List;

/**
 * Created by intel on 4/1/17.
 */

public class ApiResponse {
    private String message;
    List<ShopData> data;
    private String error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ShopData> getData() {
        return data;
    }

    public void setData(List<ShopData> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
