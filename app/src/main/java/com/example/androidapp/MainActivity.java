package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mSQLiteDB;
    private String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });
        buildRecyclerView();

        
    }
    public void openAddActivity(){
        Intent intent = new Intent(this,AddNew.class);
        startActivity(intent);

    }
    public Cursor getAllItems(){
        return mSQLiteDB.query(
                DataContract.DBEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DataContract.DBEntry.COLUMN_TIME + " DESC"
        );
    }
    @Override
    public void onResume() {
        super.onResume();
        mAdapter = new DataAdapter(this,getAllItems());
        mRecyclerView.setAdapter(mAdapter);
    }
    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        DataDBHelper dbHelper = new DataDBHelper(this);
        mSQLiteDB = dbHelper.getWritableDatabase();
        mAdapter = new DataAdapter(this, getAllItems());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new DataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.i(TAG,String.valueOf(position));
                Intent intents = new Intent(MainActivity.this,CardClickView.class);
                startActivity(intents);
            }
        });
    }
}