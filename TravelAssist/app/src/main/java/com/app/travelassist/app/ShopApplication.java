package com.app.travelassist.app;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.app.travelassist.util.ShopInterface;


/**
 * Created by Aron on 19-03-2017.
 */

public class ShopApplication extends Application{

    private static Context shopContext;

    @Override
    public void onCreate() {
        super.onCreate();
        shopContext=this;

    }

    public static Context getShopContext() {
        return shopContext;
    }

}
