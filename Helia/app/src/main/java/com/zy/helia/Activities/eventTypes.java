package com.zy.helia.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zy.helia.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class eventTypes extends AppCompatActivity {


    public Button typeA,typeB,typeC,typeD,typeE,typeF;

    public void initA() {
        typeA = (Button) findViewById(R.id.typeABut);
        typeA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeA = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeA);
            }
        });

    }

    public void initB() {
        typeB = (Button) findViewById(R.id.typeBBut);
        typeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeB = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeB);
            }
        });

    }

    public void initC() {
        typeC = (Button) findViewById(R.id.typeCBut);
        typeC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeC = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeC);
            }
        });

    }

    public void initD() {
        typeD = (Button) findViewById(R.id.typeDBut);
        typeD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeD = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeD);
            }
        });

    }

    public void initE() {
        typeE = (Button) findViewById(R.id.typeEBut);
        typeE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeE = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeE);
            }
        });

    }

    public void initF() {
        typeF = (Button) findViewById(R.id.typeFBut);
        typeF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent typeF = new Intent(eventTypes.this, CreateEvent.class);
                startActivity(typeF);
            }
        });

    }



        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_types);
            initA();
            initB();
            initC();
            initD();
            initE();
            initF();
        }


}
