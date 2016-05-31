package com.jianjiaoproduction.youremoticons;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Make the keyboard configurable. Provide interfaces for user
 * to customize the keys.
 * Created by zhibin-zhang on 5/20/2016.
 */
public class SettingActivity extends Activity{
    final private String TAG = "SETTING_DEBUG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kb_setting_layout);
    }

    /*
     * Handler method for onClick events
     */
    public void settingHandler(View v){

        switch(v.getId()){
            case R.id.tx_key1:
                Log.v(TAG, "key_1 stroke");
                break;
            case R.id.tx_key2:
                Log.v(TAG, "key_2 stroke");
                break;
            case R.id.tx_key3:
                Log.v(TAG, "key_3 stroke");
                break;
            case R.id.tx_key4:
                Log.v(TAG, "key_4 stroke");
                break;
            case R.id.tx_key5:
                Log.v(TAG, "key_5 stroke");
                break;
            case R.id.tx_key6:
                Log.v(TAG, "key_6 stroke");
                break;
            default:
                break;
        }
    }
}
