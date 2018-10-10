package com.zy.helia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.zy.helia.Activities.AdminMainPage;
import com.zy.helia.Activities.UpdateAvatar;
import com.zy.helia.Activities.eventsToBeApproved;

public class MeChangePersonalInfo extends AppCompatActivity {

    private Button updateAvatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__change_personal_info);
        updateAvatar=(Button)findViewById(R.id.changeAvatar);
        updateAvatar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent toChangeAvatar = new Intent(MeChangePersonalInfo.this, UpdateAvatar.class);
                    startActivity(toChangeAvatar);
                }
            });

        }

    }


