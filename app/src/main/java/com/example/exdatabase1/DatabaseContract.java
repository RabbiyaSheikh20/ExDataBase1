package com.example.exdatabase1;
import android.provider.BaseColumns;
public class DatabaseContract {
    public DatabaseContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class Users implements BaseColumns {
        public static final String TABLE_NAME = "users";
        public static final String COL_FULLNAME = "fullname";
        public static final String COL_EMAIL = "email";
    }
}
