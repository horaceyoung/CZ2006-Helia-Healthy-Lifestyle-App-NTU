package com.zy.helia.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zy.helia.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class eventsApproved extends AppCompatActivity {

    public Button eventToBe1;

    public void initEventToBe1(){
        eventToBe1=(Button)findViewById(R.id.toBe1But);
        eventToBe1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent eventToBe1 = new Intent(eventsApproved.this, eventDetailApproved.class);
                startActivity(eventToBe1);
            }
        });

    }


    public Button eventToBe2;

    public void initEventToBe2(){
        eventToBe2=(Button)findViewById(R.id.toBe2But);
        eventToBe2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent eventToBe2 = new Intent(eventsApproved.this, eventDetailApproved.class);
                startActivity(eventToBe2);
            }
        });

    }


    public Button eventToBe3;

    public void initEventToBe3(){
        eventToBe3=(Button)findViewById(R.id.toBe3But);
        eventToBe3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent eventToBe3 = new Intent(eventsApproved.this, eventDetailApproved.class);
                startActivity(eventToBe3);
            }
        });

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_approved);
        initEventToBe1();
        initEventToBe2();
        initEventToBe3();
    }
}
