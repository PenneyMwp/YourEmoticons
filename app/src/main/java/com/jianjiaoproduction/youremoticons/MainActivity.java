package com.jianjiaoproduction.youremoticons;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by zhibzhang on 5/10/2016.
 */
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainlayout);
        RelativeLayout baseLayout = (RelativeLayout)findViewById(R.id.baseLayout);

        baseLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(android.provider.Settings.ACTION_INPUT_METHOD_SETTINGS);
                startActivityForResult(intent, 0);
            }
        });
    }
}
