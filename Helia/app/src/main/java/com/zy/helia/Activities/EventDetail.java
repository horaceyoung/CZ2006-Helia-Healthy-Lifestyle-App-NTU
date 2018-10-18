package com.zy.helia.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.zy.helia.R;


public class EventDetail extends AppCompatActivity {

    private TextView EventName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        EventName = (TextView)findViewById(R.id.EventName);

        Intent i = getIntent();
        String name = i.getStringExtra("EventName");

        EventName.setText(name);
    }
}
