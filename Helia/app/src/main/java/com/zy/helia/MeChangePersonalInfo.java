package com.zy.helia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.Account_Data.AccountContract.AccountEntry;

import com.zy.helia.Activities.AdminMainPage;
import com.zy.helia.Activities.UpdateAvatar;
import com.zy.helia.Activities.eventsToBeApproved;
import com.zy.helia.Event_Data.DatabaseHelp;

public class MeChangePersonalInfo extends AppCompatActivity {

    private Button updateAvatar;

    //private DatabaseHelp db = new DatabaseHelp(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__change_personal_info);
        updateAvatar=findViewById(R.id.changeAvatar);
        updateAvatar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent toChangeAvatar = new Intent(MeChangePersonalInfo.this, UpdateAvatar.class);
                    startActivity(toChangeAvatar);
                }
            });
        Button Confirm = findViewById(R.id.ConfirmChangePersonalInfo);
        Confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                EditText Password = findViewById(R.id.inputPassword);
                EditText Email  = findViewById(R.id.inputEmail);
                String newPassword  =  Password.getText().toString();
                String newEmail = Email.getText().toString();
                LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_EMAIL,newEmail);
                LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_PASSWORD,newPassword);
                LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_AVATAR,"1");
            }
    });


        }

    }


