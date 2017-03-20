package com.app.travelassist.database;



import android.content.ContentValues;
import android.database.Cursor;
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

}
