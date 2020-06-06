package com.example.androidapp;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.androidapp.DataContract.*;
import androidx.annotation.Nullable;

public class DataDBHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "bluetoothapp.db";
    public static final int DATABASE_VERSION = 1;

    public DataDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_DATA_TABLE = "CREATE TABLE " +
                DBEntry.TABLE_NAME + " ( " +
                DBEntry.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DBEntry.COLUMN_NAME + " TEXT NOT NULL, " +
                DBEntry.COLUMN_TEMP + " INTEGER NOT NULL, " +
                DBEntry.COLUMN_TIME + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                DBEntry.COLUMN_LAST_10 + " TEXT" +
                ");";
        db.execSQL(SQL_CREATE_DATA_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DBEntry.TABLE_NAME);
        onCreate(db);
    }
}
