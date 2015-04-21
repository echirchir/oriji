package com.oriji.products.utils;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * The intent service that runs in the background checking
 * for internet connection and publishing results to the UI
 */

public class InternetIntentService extends IntentService {

    public static final String NOTIFICATION = "com.aerispos.smaklocator.internet";
    public static final String CONNECTION = "connection";
    public static final String RESULT = "result";

    public InternetIntentService()
    {
        super("InternetIntentService");
    }


    @Override
    protected void onHandleIntent(Intent intent)
    {
        InternetConnectionDetector detector = new InternetConnectionDetector(getApplicationContext());

        boolean isConnected = detector.isConnectedToInternet();

        publishResult(isConnected, Activity.RESULT_OK);

    }

    /**
     *
     * @param isConnected whether connection is available or not
     * @param resultCode result code of the connection
     */
    public void publishResult(boolean isConnected, int resultCode)
    {
        Intent intent = new Intent(NOTIFICATION);
        intent.putExtra(CONNECTION, isConnected);
        intent.putExtra(RESULT, resultCode);
        sendBroadcast(intent);
    }
}
