package com.zy.helia.Activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zy.helia.Account_Data.AccountContract.AccountEntry;
import com.zy.helia.Account_Data.AccountDBHelper;
import com.zy.helia.Event_Data.DatabaseHelp;
import com.zy.helia.Input_Manager.InputManager;
import com.zy.helia.R;

public class RegistrationActivity extends AppCompatActivity {
    private EditText mUsername;
    private EditText mPassword;
    private EditText mEmail;
    private Button mRegiterSubmitBtn;
    private Button mCancelBtn;
    private AccountDBHelper mDbHelper;
    private DatabaseHelp dbHelper = new DatabaseHelp(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        mUsername = findViewById(R.id.usernameRegi);
        mPassword = findViewById(R.id.passwordRegi);
        mEmail = findViewById(R.id.EmailRegi);

        mRegiterSubmitBtn = findViewById(R.id.registerButton);
        mCancelBtn = findViewById(R.id.cancelButton);

        mDbHelper = new AccountDBHelper(this);

        mRegiterSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsername.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String email = mEmail.getText().toString().trim();
                createNewAccount(username, password, email);

            }
        });

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private boolean checkUsername(String Username){          //returns 0 if Username doesnt exist, returns 1 if it does
        if(!InputManager.ValidateUserNameInput(Username)&&Username.length()<=18 && Username.length()>=6){
            Toast.makeText(this, "Error: the Username is not valid, it should contain alphabets and numbers only", Toast.LENGTH_LONG).show();
            return false;
        }

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String query = "Select * from User Where Username = '"+ Username +"'";
        Cursor c = db.rawQuery(query,null);


        if (c.getCount() > 0) {
            Log.d("Register counts", String.valueOf(c.getCount()));
            c.close();
            db.close();
            Toast.makeText(this, "Error: the Username already Exits, pleasae try another one", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            c.close();
            db.close();
            return true;
        }
    } //end

    private boolean checkPassword(String password){
        if(!(InputManager.ValidatePasswordInput(password) && password.length() >= 6)){
            Toast.makeText(this, "Error: the password is not valid, it should contain non-space characters with length longer than 6, and it should not contain spaces", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private boolean checkEmail(String Email){          //returns 0 if Email doesnt exist, returns 1 if it does
        if(!InputManager.ValidateEmailInput(Email) && Email.length()<=0){
            Toast.makeText(this, "Error: the email is not valid", Toast.LENGTH_LONG).show();
            return false;
        }
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        String query = "Select * from User Where Email = '"+ Email +"'";
        Cursor c = db.rawQuery(query,null);
        if (c.getCount() > 0) {
            c.close();
            db.close();
            Toast.makeText(this, "Error: the email has already been used, pleasae try another one", Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            c.close();
            db.close();
            return true;
        }
    } //end

    private boolean checkAccountInfoEligibility(String username, String password, String email){
        //To be completed later (for now don't check is duplicate usernames exist)
        if (checkUsername(username) && checkPassword(password) && checkEmail(email)){
            return true;
        }
        return false;
    }

    private void createNewAccount(String username, String password, String email){
        if(!checkAccountInfoEligibility(username, password, email))
            return;
        // Create database helper


        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues accountValues = new ContentValues();
        accountValues.put(AccountEntry.COLUMN_USERNAME, username);
        accountValues.put(AccountEntry.COLUMN_PASSWORD, password);
        accountValues.put(AccountEntry.COLUMN_EMAIL, email);
        accountValues.put(AccountEntry.COLUMN_AVATAR, 1);

        // Insert a new row for pet in the database, returning the ID of that new row.
        long newRowId = db.insert(AccountEntry.TABLE_NAME, null, accountValues);
        // Show a toast message depending on whether or not the insertion was successful
        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with Account Creating", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
            Toast.makeText(this, "Account Successfully Created", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }
}