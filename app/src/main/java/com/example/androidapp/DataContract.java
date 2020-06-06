package com.example.androidapp;
import android.provider.BaseColumns;
public class DataContract {
    public static final class DBEntry implements BaseColumns{
        private DBEntry(){}
        public static final String TABLE_NAME = "DATA_BEAN";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_ID = "id";
        public static final String COLUMN_TEMP = "current_temp";
        public static final String COLUMN_TIME = "time";
        public static final String COLUMN_LAST_10 = "last_temp";

    }
}
