package com.oriji.products.utils;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.oriji.products.R;
import com.oriji.products.activities.CoreOrijiActivity;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * Displaying custom action bar ShowHomeEnabled button with label & Title
 * Courtesy of Carl Anderson see StackOverflow.com Android Chatroom (Room 15)
 */

public final class ActionbarHelper {
    private ActionbarHelper() { }

    /**
     *
     * @param activity the context to set the action bar settings on
     * @param titleString the title of the action bar in question
     */
    public static void setupActionBar(final Activity activity, final String titleString) {
        ActionBar actionBar = activity.getActionBar();
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(LayoutInflater.from(activity).inflate(R.layout.custom_up_arrow, null), params);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowTitleEnabled(false);
        TextView title = (TextView) actionBar.getCustomView().findViewById(R.id.action_bar_title);
        title.setText(titleString);

        ImageView homeButton = (ImageView) actionBar.getCustomView().findViewById(R.id.action_bar_home_icon);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent homeIntent = new Intent(activity, CoreOrijiActivity.class);
                homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                activity.startActivity(homeIntent);
            }
        });
    }
}