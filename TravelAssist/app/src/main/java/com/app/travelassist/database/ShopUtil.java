package com.app.travelassist.database;



import android.content.ContentValues;
import android.database.Cursor;
import android.location.Location;
import android.net.Uri;
import android.util.Log;


import com.app.travelassist.model.MenuItem;
import com.app.travelassist.model.ShopDetail;
import com.app.travelassist.app.ShopApplication;

import java.util.ArrayList;
import java.util.List;

public class ShopUtil {
    private static final String TAG = ShopUtil.class.getSimpleName();


    /*public static String getDefaultMovie() {
        String id = null;
        String lSelection = ShopProvider.SHOP_COLUMNS.TYPE + "= ? AND " + ShopProvider.VIDEO_COLUMNS.SELECTED_STATE + "= ?";
        String[] lSelectionArg = {"" + ShopProvider.VIDEO_TYPE.MOVIE, "" + 1};
        Cursor lCursor = null;
        try {
            lCursor = VideoApplication.getVideoContext().getContentResolver().query(ShopProvider.CONTENT_URI_VIDEO_TABLE, null, lSelection, lSelectionArg, null);
            while (null != lCursor && lCursor.moveToNext()) {
                id = lCursor.getString(lCursor.getColumnIndex(ShopProvider.VIDEO_COLUMNS.VIDEO_ID));
                break;
            }
        } catch (Exception e) {
            Log.d(TAG, "Exception :: getDefaultMovie() :: ", e);
        } finally {
            if (null != lCursor && !lCursor.isClosed()) {
                lCursor.close();
            }
        }
        Logger.debug(TAG, "getDefaultMovie() :: movie id " + id);
        return id;
    }*/

    public static ShopDetail getShopInfo(String shopId){
        Cursor lCursor = null;
        ShopDetail shopInfo=new ShopDetail();
        String lSelection = ShopProvider.MENU_ITEM_COLUMNS.SHOP_ID + " = ?";
        String[] lSelectionArg = {"" + shopId};

        lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_SHOP_TABLE, null, lSelection, lSelectionArg, null);
        while (null!=lCursor&&lCursor.moveToNext()){
            shopInfo.setShopId((lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_ID))));
            shopInfo.setShopName((lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_NAME))));
            shopInfo.setShopCuisine((lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_CUISINE))));
            shopInfo.setShopRating((lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_RATING))));
        }
        return shopInfo;
    }


    public static List<ShopDetail> getShopsList() {
        List<ShopDetail> shopsList=new ArrayList<>();
        String orderBy = ShopProvider.SHOP_COLUMNS.SHOP_DISTANCE + " ASC";

            Cursor lCursor = null;
            try {
                lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_SHOP_TABLE, null, null, null, orderBy);
                while (null != lCursor && lCursor.moveToNext()) {
                    ShopDetail data=new ShopDetail();
                    data.setShopId(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_ID)));
                    data.setShopName(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_NAME)));
                    data.setShopType(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_TYPE)));
                    data.setShopCuisine(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_CUISINE)));
                    data.setDistance(lCursor.getDouble(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_DISTANCE)));
                    data.setShopRating(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_RATING)));
                    data.setShopStatus(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_STATUS)));
                    shopsList.add(data);
                }
            } catch (Exception e) {
                Log.d(TAG, "Exception :: getMovieData() :: ", e);
            } finally {
                if (null != lCursor && !lCursor.isClosed()) {
                    lCursor.close();
                }
            }
        return shopsList;
    }

    public static void addShopsList(List<ShopDetail> shopsList){
        List<ShopDetail> list=shopsList;
        for(ShopDetail shop:shopsList){
            ContentValues lShopContentValue = new ContentValues();
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_ID, shop.getShopId());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_NAME, shop.getShopName());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_CUISINE, shop.getShopCuisine());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_TYPE, shop.getShopType());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_DISTANCE, shop.getDistance());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_RATING, shop.getShopRating());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_STATUS, shop.getShopStatus());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_LATITUDE, shop.getShopLatitude());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_LONGITUDE, shop.getShopLongitude());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_MOBILE, shop.getShopMobile());
            lShopContentValue.put(ShopProvider.SHOP_COLUMNS.SHOP_ADDRESS, shop.getShopAddress());
            /*int count = ShopApplication.getShopContext().getContentResolver().update(ShopProvider.CONTENT_URI_SHOP_TABLE, lShopContentValue, null, null);
                Log.d(TAG, "insertOrUpdateRouteInfo() :: CONTENT_URI_LOCATION_INFO_TABLE rows count " + count);
            if (count == 0) {*/
                Uri lUri = ShopApplication.getShopContext().getContentResolver().insert(ShopProvider.CONTENT_URI_SHOP_TABLE, lShopContentValue);
            //}
        }
    }

    public static void addItemsList(List<MenuItem> itemsList){
        List<MenuItem> list=itemsList;
        for(MenuItem item:itemsList){
            ContentValues lItemContentValue = new ContentValues();
            lItemContentValue.put(ShopProvider.MENU_ITEM_COLUMNS.ITEM_ID, item.getItemId());
            lItemContentValue.put(ShopProvider.MENU_ITEM_COLUMNS.ITEM_NAME, item.getItemName());
            lItemContentValue.put(ShopProvider.MENU_ITEM_COLUMNS.ITEM_PRICE, item.getItemPrice());
            lItemContentValue.put(ShopProvider.MENU_ITEM_COLUMNS.ITEM_CATEGORY, item.getItemCategory());
            lItemContentValue.put(ShopProvider.MENU_ITEM_COLUMNS.SHOP_ID, item.getShopId());
            /*int count = ShopApplication.getShopContext().getContentResolver().update(ShopProvider.CONTENT_URI_SHOP_TABLE, lShopContentValue, null, null);
                Log.d(TAG, "insertOrUpdateRouteInfo() :: CONTENT_URI_LOCATION_INFO_TABLE rows count " + count);
            if (count == 0) {*/
            Uri lUri = ShopApplication.getShopContext().getContentResolver().insert(ShopProvider.CONTENT_URI_MENU_ITEM_TABLE, lItemContentValue);
            //}
        }
    }

    public static List<String> getCategoriesForShop(String shopId){
        Cursor lCursor = null;
        List<String> categoriesList=new ArrayList<>();
        String[] projection = new String[]{"Distinct " + ShopProvider.MENU_ITEM_COLUMNS.ITEM_CATEGORY};
        String selection = ShopProvider.MENU_ITEM_COLUMNS.SHOP_ID + " = ?";
        String[] selectionArg = new String[]{shopId};

        lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_MENU_ITEM_TABLE, projection, selection, selectionArg, null);
        while (null!=lCursor&&lCursor.moveToNext()){
            categoriesList.add(lCursor.getString(lCursor.getColumnIndex(ShopProvider.MENU_ITEM_COLUMNS.ITEM_CATEGORY)));
        }
        return categoriesList;
    }

    public static List<String> getItemsForShop(String shopId,String category){
        Cursor lCursor = null;
        List<String> itemsList=new ArrayList<>();
        String selection = ShopProvider.MENU_ITEM_COLUMNS.SHOP_ID + " = ? AND "+ ShopProvider.MENU_ITEM_COLUMNS.ITEM_CATEGORY + " = ?";
        String[] selectionArg = new String[]{shopId,category};

        lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_MENU_ITEM_TABLE, null, selection, selectionArg, null);
        while (null!=lCursor&&lCursor.moveToNext()){
            itemsList.add(lCursor.getString(lCursor.getColumnIndex(ShopProvider.MENU_ITEM_COLUMNS.ITEM_NAME)));
        }
        return itemsList;
    }

    public static List<ShopDetail> getAllUnprocessedLocation(){
        Cursor lCursor = null;
        List<ShopDetail> shopLocationList=new ArrayList<>();
        String selection = ShopProvider.SHOP_COLUMNS.SHOP_INFO_PROCESSED + " = ?";
        String[] selectionArg = new String[]{""+ShopProvider.SHOP_COLUMNS.SHOP_LOCATION_UN_PROCESSED};

        lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_MENU_ITEM_TABLE, null, selection, selectionArg, null);
        while (null!=lCursor&&lCursor.moveToNext()){
            ShopDetail lLocation=new ShopDetail();
            lLocation.setShopId(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_ID)));
            lLocation.setShopLatitude(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_LATITUDE)));
            lLocation.setShopLongitude(lCursor.getString(lCursor.getColumnIndex(ShopProvider.SHOP_COLUMNS.SHOP_LONGITUDE)));
            shopLocationList.add(lLocation);
        }
        return shopLocationList;
    }

    public static void updateAllShopDistance(List<ShopDetail> locationDetail){
        List<ShopDetail> list=locationDetail;
        for(ShopDetail item:list){
            String lSelection = ShopProvider.SHOP_COLUMNS.SHOP_ID + "= ?";
            String[] lSelectionArg = {item.getShopId()};
            ContentValues lValue = new ContentValues();
            lValue.put(ShopProvider.SHOP_COLUMNS.SHOP_DISTANCE, item.getDistance());
            lValue.put(ShopProvider.SHOP_COLUMNS.SHOP_INFO_PROCESSED, ShopProvider.SHOP_COLUMNS.SHOP_LOCATION_PROCESSED);
            int count = ShopApplication.getShopContext().getContentResolver().update(ShopProvider.CONTENT_URI_SHOP_TABLE, lValue, lSelection, lSelectionArg);
            Log.d(TAG, "updateAllShopDistance() :: update count " + count);
        }
    }

    public static double getDistance(double currentLat,double currentLong,double hotelLat,double hotelLong){
        Location startPoint=new Location("current");
        startPoint.setLatitude(currentLat);
        startPoint.setLongitude(currentLong);

        Location endPoint=new Location("hotel");
        endPoint.setLatitude(hotelLat);
        endPoint.setLongitude(hotelLong);

        double distance=startPoint.distanceTo(endPoint);
        Log.d(TAG, "getDistance() :: distance to shop " + distance);
        return distance;
    }

    public static Location getCurrentLocationFromDB(){
        Cursor lCursor = null;
        Location location=new Location("current");

        lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_CURRENT_LOCATION_TABLE, null, null, null, null);
        while (null!=lCursor&&lCursor.moveToNext()){
            location.setLatitude((lCursor.getInt(lCursor.getColumnIndex(ShopProvider.LOCATION_COLUMNS.CURRENT_LATITUDE))));
            location.setLongitude((lCursor.getInt(lCursor.getColumnIndex(ShopProvider.LOCATION_COLUMNS.CURRENT_LONGITUDE))));
        }
        Log.d(TAG, "getCurrentLocationFromDB() :: location " + location.getLatitude()+"--"+location.getLongitude());
        return location;
    }

    public static void updateCurrentLocation(Location location){
        ContentValues lValue = new ContentValues();
        lValue.put(ShopProvider.LOCATION_COLUMNS.CURRENT_LATITUDE, location.getLatitude());
        lValue.put(ShopProvider.LOCATION_COLUMNS.CURRENT_LONGITUDE, location.getLongitude());
        int count = ShopApplication.getShopContext().getContentResolver().update(ShopProvider.CONTENT_URI_CURRENT_LOCATION_TABLE, lValue, null, null);
        Log.d(TAG, "updateCurrentLocation() :: count " + count);
        if(count==0){
          Uri lUri=  ShopApplication.getShopContext().getContentResolver().insert(ShopProvider.CONTENT_URI_CURRENT_LOCATION_TABLE,lValue);
            Log.d(TAG, "updateCurrentLocation() :: Uri " + lUri);
        }
    }

}
