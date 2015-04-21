package com.oriji.products.receivers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;
import com.oriji.products.R;
import com.oriji.products.utils.InternetConnectionDetector;
import com.oriji.products.utils.InternetIntentService;


/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This is an internet service receiver that checks to make sure
 * the user's device is connected to the internet before making
 * HTTP requests to the server to fetch products.
 */

public class InternetServiceReceiver extends BroadcastReceiver {

    protected boolean conn;
    private AlertDialog zoomDialog;
    private InternetConnectionDetector detector;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        detector = new InternetConnectionDetector(context);
        if(zoomDialog == null)
        {
            zoomDialog = new AlertDialog.Builder(new ContextThemeWrapper(context , android.R.style.Theme_Holo)).create();
            zoomDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }

        Bundle bundle = intent.getExtras();

        if(bundle != null)
        {
            conn = bundle.getBoolean(InternetIntentService.CONNECTION);
            int resultCode = bundle.getInt(InternetIntentService.RESULT);

            if(!conn && resultCode == Activity.RESULT_OK)
            {
                displayInternetDialog(context, context.getResources().getString(R.string.internet_warning));
            }
            else
            {
                zoomDialog.dismiss();
            }
        }
    }

    /**
     *
     * @param activity the context for UI inflation
     * @param message the message a user is shown
     */
    public void displayInternetDialog(Context activity, String message)
    {
        LayoutInflater factory = LayoutInflater.from(activity);
        final View view = factory.inflate(R.layout.internet_notify_dialog, null);
        TextView text = (TextView)view.findViewById(R.id.notify_text);

        WindowManager.LayoutParams layoutParams = zoomDialog.getWindow().getAttributes();
        layoutParams.gravity = Gravity.BOTTOM;
        layoutParams.y = 0;
        zoomDialog.setCancelable(true);

        text.setText(message);
        zoomDialog.setView(view);

        if(!zoomDialog.isShowing() && !detector.isConnectedToInternet())
        {
            zoomDialog.show();
        }else if (detector.isConnectedToInternet() && zoomDialog.isShowing())
        {
            zoomDialog.dismiss();
        }
    }
}
