package com.example.buttercalc;

import android.app.Activity;
import android.os.Bundle;


public class ButterCalcActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new ButterCalcFragment())
                    .commit();
        }
    }

}
