package com.example.calc;

import android.app.Activity;
import android.os.Bundle;


public class CalcActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new CalcFragment())
                    .commit();
        }
    }
}
