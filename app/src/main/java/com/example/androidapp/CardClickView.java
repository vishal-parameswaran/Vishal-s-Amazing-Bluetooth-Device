package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;

public class CardClickView extends AppCompatActivity {
    private SQLiteDatabase mSQLiteDB;
    private Cursor mCursor;
    public TextView mTempView;
    public TextView mNameView;
    public TextView mDateView;
    public RecyclerView mLastTemps;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<lastTemperaturesList> lastTempList;
    private TempAdapter mAdapter;
    private int mCurrentPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_click_view);
        Intent intents = getIntent();
        mCurrentPosition = intents.getIntExtra("position",-1);
        if (mCurrentPosition== -1){
            this.finish();
        }
        mTempView = findViewById(R.id.TempView);
        mNameView = findViewById(R.id.NameView);
        mDateView = findViewById(R.id.DateView);
        mLastTemps = findViewById(R.id.innerRecycler);
        buildView();
    }
    public void buildView(){
        mLastTemps.setHasFixedSize(true);
        DataDBHelper dbHelper = new DataDBHelper(this);
        mSQLiteDB = dbHelper.getWritableDatabase();
        mLayoutManager = new LinearLayoutManager(this);
        getItem();
        mLastTemps.setLayoutManager(mLayoutManager);
        mLastTemps.setAdapter(mAdapter);
    }
    public void getItem(){

        Cursor mCursor = mSQLiteDB.query(DataContract.DBEntry.TABLE_NAME,null,null,null,null,null,DataContract.DBEntry.COLUMN_TIME + " DESC");
        if(!mCursor.moveToPosition(mCurrentPosition)){
            Log.i("CC","HI");
            this.finish();
        }
        Log.i("CC",String.valueOf(mCursor.getInt(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_TEMP))));
        mTempView.setText(String.valueOf(mCursor.getInt(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_TEMP))));
        mNameView.setText(mCursor.getString(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_NAME)));
        mDateView.setText(mCursor.getString(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_TIME)));
        String lastTemperaturesString = mCursor.getString(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_LAST_10));
        if (lastTemperaturesString != null) {
            String lastTemperatureStringList[] = lastTemperaturesString.split("|");
            for (String pair : lastTemperatureStringList) {
                String splitPair[] = pair.split(":");
                lastTempList.add(new lastTemperaturesList(splitPair[0], Integer.parseInt(splitPair[1])));
            }
        }
        mAdapter = new TempAdapter(this, lastTempList);
    }
}