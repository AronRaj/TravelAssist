package com.app.travelassist.volley;

import android.content.Intent;
import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.app.travelassist.app.ShopApplication;
import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.ApiResponse;
import com.app.travelassist.model.Item;
import com.app.travelassist.model.ShopData;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.util.ShopInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aron on 2/3/2017.
 */

public class ShopHandler extends Handler {

    private static final String TAG = "ShopHandler";

    private static ShopHandler sInstance;

    private ShopHandler(Looper looper) {
        super(looper);
    }

    public static ShopHandler getInstance() {
        if (null == sInstance) {
            synchronized (ShopHandler.class) {
                HandlerThread lThread = new HandlerThread("Network");
                lThread.start();
                if (null == sInstance) {
                    sInstance = new ShopHandler(lThread.getLooper());
                }
                // lThread.quitSafely();
            }
        }
        return sInstance;
    }

    @Override
    public void handleMessage(Message msg) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        switch (msg.what) {
            case ShopInterface.HANDLE_SHOP_DETAILS: {
                ApiResponse response = gson.fromJson(msg.getData().getString("data"), ApiResponse.class);
                Location currentLocation=ShopUtil.getCurrentLocationFromDB();
                if (null != response) {
                    if (response.getError().equalsIgnoreCase(ShopInterface.STATUS_OK_KEY)) {
                        List<ShopData> results = response.getData();
                        if (null!=results&&results.size() > 0) {
                            List<ShopDetail> shopsList=new ArrayList<>();
                            for(ShopData result:results){
                                ShopDetail shop=new ShopDetail();
                                shop.setShopId(result.getShopID());
                                shop.setShopName(result.getShopName());
                                shop.setShopType(result.getType());
                                shop.setShopMobile(result.getContactNo1());
                                String lat=result.getLatitude();
                                String longitude=result.getLongitude();
                                double hotelLat=Double.parseDouble(lat);
                                double hotelLong=Double.parseDouble(longitude);
                                shop.setShopLatitude(hotelLat);
                                shop.setShopLongitude(hotelLong);
                               // double distance=ShopUtil.getDistance(currentLocation.getLatitude(),currentLocation.getLongitude(),hotelLat,hotelLong);*/
                                if(null!=result.getDistance()) {
                                    shop.setDistance(Double.parseDouble(result.getDistance()));
                                }else {
                                    shop.setDistance(0.0);
                                }
                                shop.setShopImageUrl(result.getImage());
                                shop.setShopAddress(result.getAddressLine1()+result.getAddressLine2()+result.getCity()+result.getPincode());
                                shop.setShopRating(result.getRating());
                                shop.setShopTimings(result.getWorkingDays());
                                List<Item> itemsList=result.getItem();
                                if(null!=itemsList&&itemsList.size()>0){
                                    ShopUtil.addItemsList(itemsList);
                                }
                                shopsList.add(shop);
                            }
                            ShopUtil.addShopsList(shopsList);
                            Log.d(TAG, "Success");
                        }
                    }
                }
                Intent intent=new Intent();
                intent.setAction(ShopInterface.ACTION_SHOP_LIST);
                LocalBroadcastManager.getInstance(ShopApplication.getShopContext()).sendBroadcast(intent);
                break;
            }
            /*case LocationInterface.HANDLE_JOURNEY_INFO: {
                DistanceData distanceData = gson.fromJson(msg.getData().getString("data"), DistanceData.class);
                if (null != distanceData) {
                    if (distanceData.getStatus().equalsIgnoreCase(LocationInterface.STATUS_OK_KEY)) {
                        Rows[] rows = distanceData.getRows();
                        if (rows.length > 0) {
                            Rows row = rows[0];
                            if (null != row) {
                                Elements[] elements = row.getElements();
                                if (elements.length > 0) {
                                    Elements sourceData = elements[0];
                                    String totalDistance = sourceData.getDistance().getText();
                                    String totalTimeTraffic = sourceData.getDuration_in_traffic().getText();
                                    LocationUtil.updateJourneyInfo(totalDistance,totalTimeTraffic);
                                    LocalBroadcastManager.getInstance(LocationApplication.getLocationContext()).sendBroadcast(new Intent(CustomIntent.ACTION_JOURNEY_INFO_CHANGED));
                                    Log.d(TAG, "Success");
                                }
                            }
                        }
                    }
                }
                break;
            }*/
        }
    }
}
