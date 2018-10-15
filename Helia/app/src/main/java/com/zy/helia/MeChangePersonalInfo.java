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
    private Button Confirm = findViewById(R.id.ConfirmChangePersonalInfo);
    private EditText Password = findViewById(R.id.inputPassword);
    private EditText Email  = findViewById(R.id.inputEmail);
    private DatabaseHelp db = new DatabaseHelp(this);


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

        Confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String newPassword  =  Password.getText().toString();
                String newEmail = Email.getText().toString();
                String username = LoginActivity.getUsername();
                LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_EMAIL,newEmail);
                LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_PASSWORD,newPassword);
            }
        });


        }

    }


