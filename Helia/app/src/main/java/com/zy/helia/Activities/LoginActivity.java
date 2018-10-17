package com.zy.helia.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zy.helia.Account_Data.AccountContract.AccountEntry;
import com.zy.helia.Account_Data.AccountDBHelper;
import com.zy.helia.R;

public class LoginActivity extends AppCompatActivity {
    private final String TAG = "LoginActivity";
    private Button mRegisterBtn;
    private Button mLoginBtn;
    private EditText mUsername;
    private EditText mPassword;
    private static String mCurrentUsername;
    private static String mCurrentPassword;
    private static int mCurrentAvatarChoice;
    private static String mCurrentEmail;
    private static AccountDBHelper mAccountHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mRegisterBtn = findViewById(R.id.registerButton);
        mLoginBtn = findViewById(R.id.loginButton);
        mUsername = findViewById(R.id.usernameLogin);
        mPassword = findViewById(R.id.passwordLogin);

        mAccountHelper = new AccountDBHelper(this);
        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jumpRegisterIntent = new Intent(getBaseContext(), RegistrationActivity.class);
                startActivity(jumpRegisterIntent);
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                validateUser(username, password);
            }
        });
    }

    private void validateUser(String username, String password){
        Log.d(TAG, "The login button is clicked");
        SQLiteDatabase accountDB = mAccountHelper.getReadableDatabase();

        String[] accountProjection = {
                AccountEntry._ID,
                AccountEntry.COLUMN_USERNAME,
                AccountEntry.COLUMN_PASSWORD,
        };

        Cursor cursor = accountDB.query(
                AccountEntry.TABLE_NAME,
                accountProjection,
                null,
                null,
                null,
                null,
                null
        );

        int usernameIndex = cursor.getColumnIndex(AccountEntry.COLUMN_USERNAME);
        int passwordIndex = cursor.getColumnIndex(AccountEntry.COLUMN_PASSWORD);
        int avatarChoiceIndex = cursor.getColumnIndex(AccountEntry.COLUMN_AVATAR);
        int emailIndex = cursor.getColumnIndex(AccountEntry.COLUMN_EMAIL);
        while (cursor.moveToNext()) {
            String currentUsername = cursor.getString(usernameIndex);
            String currentPassword = cursor.getString(passwordIndex);
            //int currentAvatarChoice = cursor.getInt(avatarChoiceIndex);
            //String currentEmail = cursor.getString(emailIndex);
            Log.d(TAG, "current username is " + currentUsername + "current password is "+currentPassword);
            if (username.equals(currentUsername) && password.equals(currentPassword)) {
                mCurrentUsername = currentUsername;
                mCurrentPassword = currentPassword;
                //mCurrentAvatarChoice = currentAvatarChoice;
                //mCurrentEmail = currentEmail;
                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                Intent loginSuccessIntent = new Intent(this, MainActivity.class);
                startActivity(loginSuccessIntent);
            }
            else{
                Toast.makeText(this, "Username not found or username and password does not match.", Toast.LENGTH_SHORT).show();
            }
        }



    }

    public static void UpdateUserInfo(String updateType, String updateValue){
        SQLiteDatabase accountDB = mAccountHelper.getReadableDatabase();

        String[] accountProjection = {
                AccountEntry._ID,
                AccountEntry.COLUMN_USERNAME,
                AccountEntry.COLUMN_PASSWORD,
                AccountEntry.COLUMN_EMAIL,
                AccountEntry.COLUMN_AVATAR,
        };

        Cursor cursor = accountDB.query(
                AccountEntry.TABLE_NAME,
                accountProjection,
                null,
                null,
                null,
                null,
                null
        );
        int usernameIndex = cursor.getColumnIndex(AccountEntry.COLUMN_USERNAME);
        int idIndex = cursor.getColumnIndex(AccountEntry._ID);
        while(cursor.moveToNext()){
            if (cursor.getString(usernameIndex).equals(mCurrentUsername)){
                int id = cursor.getInt(idIndex);
                ContentValues update = new ContentValues();
                update.put(updateType, updateValue);
                accountDB.update(AccountEntry.TABLE_NAME, update, "_id=" + String.valueOf(id), null);
            }
        }

    }

    public static String getUsername(){
        return mCurrentUsername;
    }

    public static String getPassword(){
        return mCurrentPassword;
    }

    public static int gerAvatarChoice(){return mCurrentAvatarChoice;}
}