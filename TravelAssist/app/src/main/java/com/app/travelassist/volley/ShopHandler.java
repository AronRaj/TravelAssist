package com.app.travelassist.volley;

import android.location.Location;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.app.travelassist.database.ShopUtil;
import com.app.travelassist.model.GoogleResult;
import com.app.travelassist.model.Result;
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
                GoogleResult hotelsData = gson.fromJson(msg.getData().getString("data"), GoogleResult.class);
                Location currentLocation=ShopUtil.getCurrentLocationFromDB();
                if (null != hotelsData) {
                    if (hotelsData.getStatus().equalsIgnoreCase(ShopInterface.STATUS_OK_KEY)) {
                        List<Result> results = hotelsData.getResults();
                        if (results.size() > 0) {
                            List<ShopDetail> shopsList=new ArrayList<>();
                            for(Result result:results){
                                ShopDetail shop=new ShopDetail();
                                shop.setShopId(result.getId());
                                shop.setShopName(result.getName());
                                String lat=result.getGeometry().getLocation().getLat();
                                String longitude=result.getGeometry().getLocation().getLng();
                                double hotelLat=Double.parseDouble(lat);
                                double hotelLong=Double.parseDouble(longitude);
                                shop.setShopLatitude(hotelLat);
                                shop.setShopLongitude(hotelLong);
                                double distance=ShopUtil.getDistance(currentLocation.getLatitude(),currentLocation.getLongitude(),hotelLat,hotelLong);
                                shop.setDistance(distance);
                                shop.setShopAddress(result.getVicinity());
                                shop.setShopRating(result.getRating());
                                if(null!=result.getOpening_hours()) {
                                    shop.setShopStatus(result.getOpening_hours().getOpen_now());
                                }
                                shopsList.add(shop);
                            }
                            ShopUtil.addShopsList(shopsList);
                            Log.d(TAG, "Success");
                        }
                    }
                }
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
