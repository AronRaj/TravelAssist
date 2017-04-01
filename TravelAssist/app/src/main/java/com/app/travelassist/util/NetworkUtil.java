package com.app.travelassist.util;

import android.content.Context;
import android.location.*;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by intel on 4/1/17.
 */

public class NetworkUtil {
    private static final String TAG = "NetworkUtil";

    public static boolean isInternetAvailable(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        Log.d(TAG, "isInternetAvailable() " + isConnected);
        return isConnected;
    }

    public static boolean isGPSEnabled(Context mContext) {
        android.location.LocationManager locationManager = (android.location.LocationManager)
                mContext.getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(android.location.LocationManager.GPS_PROVIDER);
    }
}
