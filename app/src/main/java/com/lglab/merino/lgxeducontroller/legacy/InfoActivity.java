package com.lglab.merino.lgxeducontroller.legacy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lglab.merino.lgxeducontroller.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        getSupportFragmentManager().beginTransaction().add(new InfoActivityFragment(), "").commit();
    }

}
