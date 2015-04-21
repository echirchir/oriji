package com.oriji.products.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This is a simple class to check whether a device is connected to the
 * internet or not.
 */

public class InternetConnectionDetector {

    private Context context;

    public InternetConnectionDetector(Context context){
        this.context = context;
    }

    /**
     *
     * @return true or false depending on whether
     * connection is established or not on the device
     */
    public boolean isConnectedToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++){
                    if (info[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }

        }
        return false;
    }
}
