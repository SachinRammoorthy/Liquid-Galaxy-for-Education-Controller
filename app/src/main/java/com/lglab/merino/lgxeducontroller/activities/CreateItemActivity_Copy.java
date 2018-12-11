package com.lglab.merino.lgxeducontroller.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lglab.merino.lgxeducontroller.R;


public class CreateItemActivity_Copy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_item);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, CreateItemFragment_Copy.newInstance())
                    .commit();
        }
    }
}