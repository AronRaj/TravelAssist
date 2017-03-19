package com.app.travelassist.database;



import android.database.Cursor;
import android.util.Log;

import com.app.travelassist.ShopsList;
import com.app.travelassist.app.ShopApplication;

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


    public static List<ShopsList> getShopsList() {
        List<ShopsList> shopsList=null;
        ShopsList lData = null;
        String lSelection;
        String[] lSelectionArg;
        String orderBy = ShopProvider.SHOP_COLUMNS.SHOP_DISTANCE + " ASC";

            Cursor lCursor = null;
            try {
                lCursor = ShopApplication.getShopContext().getContentResolver().query(ShopProvider.CONTENT_URI_SHOP_TABLE, null, null, null, orderBy);
                while (null != lCursor && lCursor.moveToNext()) {

                    break;
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
}
