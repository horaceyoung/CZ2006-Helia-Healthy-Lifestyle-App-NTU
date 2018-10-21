package com.learning.helia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class CreateEvent extends AppCompatActivity {

    public Button selectTypeBut;

    public void initType(){
        selectTypeBut=(Button)findViewById(R.id.selectTypeBut);
        selectTypeBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent typeSelection = new Intent(CreateEvent.this, eventTypes.class);
                startActivity(typeSelection);
            }
        });


    }

    public Button submitBut;

    public void initSubmit(){
        submitBut=(Button)findViewById(R.id.submitBut);
        submitBut.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent submit = new Intent(CreateEvent.this, createEventConfirm.class);
                startActivity(submit);
            }
        });


    }


    public Button test;

    public void inittest(){
        test=(Button)findViewById(R.id.cancelBut);
        test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent test = new Intent(CreateEvent.this, AdminMainPage.class);
                startActivity(test);
            }
        });


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        initType();
        initSubmit();
        inittest();
    }
}
