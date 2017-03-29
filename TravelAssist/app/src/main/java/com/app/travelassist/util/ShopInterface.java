package com.app.travelassist.util;

/**
 * Created by aarokiax on 3/28/2017.
 */

public interface ShopInterface {
    String API_BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
    String LOCATION_KEY = "location=";
    String TYPE_KEY = "&type=";
    String RADIUS_KEY = "&radius=";
    String API_KEY = "&key=";
    String GOOGLE_API_KEY = "AIzaSyB3diyn_W-KvF2Dg_7k6WL5qmBSSrWo2H8";
    String STATUS_OK_KEY = "OK";

    int HANDLE_SHOP_DETAILS = 1;
    int HANDLE_JOURNEY_INFO = 2;

    int LOCATION_INTERVAL=5000;
    int FASTEST_INTERVAL=5000;
    float MINIMUM_DISPLACEMENT=100.0f;

}
