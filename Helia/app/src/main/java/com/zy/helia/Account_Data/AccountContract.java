package com.zy.helia.Account_Data;
import android.provider.BaseColumns;

public class AccountContract {
    private  AccountContract(){}

    public static final class AccountEntry implements BaseColumns{
<<<<<<< HEAD
        public final static String TABLE_NAME = "User";
        public final static String _ID = "User_ID";
=======
        public final static String TABLE_NAME = "accounts";
        public final static String _ID = BaseColumns._ID;
>>>>>>> 19b05c8a144fc2d0750e27af8476b9e5fe6e5e7d
        public final static String COLUMN_USERNAME = "Username";
        public final static String COLUMN_PASSWORD = "Password";
        public final static String COLUMN_EMAIL = "Email";
        public final static String COLUMN_AVATAR = "Avatar";
    }
}
