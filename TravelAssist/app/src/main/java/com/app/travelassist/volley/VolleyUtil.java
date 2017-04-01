package com.app.travelassist.volley;


import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.app.travelassist.app.ShopApplication;
import com.app.travelassist.util.ShopInterface;

import org.json.JSONObject;

/**
 * Created by aarokiax on 2/15/2017.
 */

public class VolleyUtil {
    public interface ApiConstants{

    }
    private static final String TAG="VolleyUtil";

    /**
     * Method to get all the data with shopid
     */
    public static void getShopsWithLatLng(double currentLat, double currentLong, String type, String radius) {
        String lUrl = ShopInterface.API_BASE_URL+ShopInterface.LATITUDE_KEY+currentLat+ShopInterface.LONGITUDE_KEY+currentLong;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "getLocationInfo() :: onResponse() ::" + response);
                        try {
                            Bundle lBundle=new Bundle();
                            lBundle.putString("data",response.toString());
                            Message msg= Message.obtain();
                            msg.what=ShopInterface.HANDLE_SHOP_DETAILS;
                            msg.setData(lBundle);
                            ShopHandler.getInstance().sendMessage(msg);
                            Log.i(TAG, "Response : " + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "getAllData() :: onErrorResponse() ::" + error);


                    }
                });
        VolleySingleton.getInstance(ShopApplication.getShopContext()).addToRequestQueue(jsObjRequest);
    }

    public static void submitShopRating(String shopId,float shopRating, String comments) {
        String lUrl = "";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "getLocationInfo() :: onResponse() ::" + response);
                        try {
                            Log.i(TAG, "Response : " + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, "Error Response : "+e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "getAllData() :: onErrorResponse() ::" + error);


                    }
                });
        VolleySingleton.getInstance(ShopApplication.getShopContext()).addToRequestQueue(jsObjRequest);
    }

    public static void getTravelInfo(String source, String destination) {
        /*String lUrl = LocationInterface.MATRIX_API_BASE_URL+LocationInterface.ORIGIN_KEY+source
                +LocationInterface.DESTINATION_KEY+destination+LocationInterface.API_KEY+LocationInterface.DISTANCE_MATRIX_API_KEY;

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, lUrl, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "getLocationInfo() :: onResponse() ::" + response);
                        try {
                            Bundle lBundle=new Bundle();
                            lBundle.putString("data",response.toString());
                            Message msg=Message.obtain();
                            msg.what=LocationInterface.HANDLE_JOURNEY_INFO;
                            msg.setData(lBundle);
                            LocationHandler.getInstance().sendMessage(msg);
                            Log.i(TAG, "Response : " + response.toString());
                        } catch (Exception e) {
                            Log.e(TAG, e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "getAllData() :: onErrorResponse() ::" + error);


                    }
                });
        VolleySingleton.getInstance(ShopApplication.getShopContext()).addToRequestQueue(jsObjRequest);*/
    }
}
