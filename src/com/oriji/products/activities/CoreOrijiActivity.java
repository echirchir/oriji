package com.oriji.products.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import com.oriji.products.R;
import com.oriji.products.receivers.InternetServiceReceiver;
import com.oriji.products.utils.InternetIntentService;
import com.oriji.products.utils.OrijiApiImpl;
import de.greenrobot.event.EventBus;

/**
 * @author Elisha Chirchir Email: elisha.java@gmail.com
 * @version 1.0
 * @since Jan, 2015
 *
 * The Oriji android application is owned by the Oriji Systems Ltd
 * All Rights Reserved.
 *
 * This class is the main activity of the Oriji application.
 */
public class CoreOrijiActivity extends Activity{

    protected AutoCompleteTextView queryString;
    protected OrijiApiImpl impl;
    private InternetServiceReceiver receiver = new InternetServiceReceiver();
    private final Handler internetTimer = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core_layout);

        queryString = (AutoCompleteTextView)findViewById(R.id.autocomplete_keywords);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

        String[] keywords = getResources().getStringArray(R.array.keywords);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, keywords);

        queryString.setAdapter(adapter);

        impl = new OrijiApiImpl();

        findViewById(R.id.search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String keyword = queryString.getText().toString();

                if(keyword.trim().length() == 0 || keyword.trim().equals(" ")){
                    queryString.setError("Enter comma separated keywords");

                }else {

                    impl.getProductsFromServer(keyword);

                    Intent intent = new Intent(CoreOrijiActivity.this, ProductsResulstActivity.class);

                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        registerReceiver(receiver, new IntentFilter(InternetIntentService.NOTIFICATION));
        internetTimer.postDelayed(internetCheck, 30);
    }

    @Override
    public void onPause(){
        super.onPause();
        unregisterReceiver(receiver);
        internetTimer.removeCallbacksAndMessages(null);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    /**
     * Since Oriji App requires an internet connection,
     * This runnable runs every few seconds to check
     * whether a user is connected or not and if not,
     * Notifies them of the need to connect or tunr oN wifi.
     */
    private final  Runnable internetCheck = new Runnable()
    {
        @Override
        public void run() {
            internetTimer.removeCallbacksAndMessages(null);

            Intent internetCheckIntent = new Intent(getApplicationContext(), InternetIntentService.class);
            startService(internetCheckIntent);

            internetTimer.postDelayed(internetCheck, 30);
        }
    };
}
