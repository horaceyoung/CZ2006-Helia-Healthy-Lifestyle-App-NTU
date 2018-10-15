package com.zy.helia.Account_Data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zy.helia.Account_Data.AccountContract.AccountEntry;
public class AccountDBHelper extends SQLiteOpenHelper{
    public static final String LOG_TAG = AccountDBHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "accounts.db";

    private static final int DATABASE_VERSION = 1;

    public AccountDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ACCOUNTS_TABLE =  "CREATE TABLE " + AccountEntry.TABLE_NAME + " ("
                + AccountEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AccountEntry.COLUMN_AVATAR + " INTEGER NOT NULL, "
                + AccountEntry.COLUMN_USERNAME + " TEXT NOT NULL, "
                + AccountEntry.COLUMN_PASSWORD + " TEXT NOT NULL, "
                + AccountEntry.COLUMN_EMAIL + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_ACCOUNTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

}
