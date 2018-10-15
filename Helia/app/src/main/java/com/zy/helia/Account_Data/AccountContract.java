package com.zy.helia.Account_Data;
import android.provider.BaseColumns;

public class AccountContract {
    private  AccountContract(){}

    public static final class AccountEntry implements BaseColumns{
        public final static String TABLE_NAME = "accounts";
        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_USERNAME = "username";
        public final static String COLUMN_PASSWORD = "password";
        public final static String COLUMN_EMAIL = "email";
        public final static String COLUMN_AVATAR = "avatar";
    }
}
