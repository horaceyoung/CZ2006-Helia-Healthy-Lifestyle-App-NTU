package com.zy.helia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ImageView;

import com.zy.helia.Activities.MainActivity;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.Account_Data.AccountContract.AccountEntry;

import com.zy.helia.Activities.AdminMainPage;
import com.zy.helia.Activities.UpdateAvatar;
import com.zy.helia.Activities.eventsToBeApproved;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.Input_Manager.InputManager;

public class MeChangePersonalInfo extends AppCompatActivity {

    private Button updateAvatar;
    private ImageView userAvatar;
    private Button Confirm;
    private EditText Password;
    private EditText Email;
    private String newPassword;
    private String newEmail;
    //private DatabaseHelp db = new DatabaseHelp(this);

    @Override
    public void onBackPressed() {
        Intent goBack = new Intent(MeChangePersonalInfo.this, MainActivity.class);
        goBack.putExtra("id", 2);
        startActivity(goBack);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me__change_personal_info);

        userAvatar = findViewById(R.id.userAvatar);

        switch (LoginActivity.getAvatarChoice()){
            case 1:
                userAvatar.setImageResource(R.drawable.m01);
                break;
            case 2:
                userAvatar.setImageResource(R.drawable.m02);
                break;
            case 3:
                userAvatar.setImageResource(R.drawable.f01);
                break;
            case 4:
                userAvatar.setImageResource(R.drawable.f02);
                break;
        }


        updateAvatar=findViewById(R.id.changeAvatar);
        updateAvatar.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent toChangeAvatar = new Intent(MeChangePersonalInfo.this, UpdateAvatar.class);
                    startActivity(toChangeAvatar);
                }
            });

        Confirm = findViewById(R.id.ConfirmChangePersonalInfo);
        Confirm.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Password = findViewById(R.id.inputPassword);
                Email  = findViewById(R.id.inputEmail);
                newPassword  =  Password.getText().toString();
                newEmail = Email.getText().toString();

                if(InputManager.ValidateEmailInput(newEmail))
                    LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_EMAIL,newEmail);

                if(InputManager.ValidatePasswordInput(newPassword))
                    LoginActivity.UpdateUserInfo(AccountEntry.COLUMN_PASSWORD,newPassword);

                Intent goBack = new Intent(MeChangePersonalInfo.this, MainActivity.class);
                goBack.putExtra("id", 2);
                startActivity(goBack);
            }
    });


        }

    }


