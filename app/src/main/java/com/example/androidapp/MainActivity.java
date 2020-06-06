package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DataAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SQLiteDatabase mSQLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<DataBean> dataBean = new ArrayList<>();
        dataBean.add(new DataBean("91°C","Vishal"));
        dataBean.add(new DataBean("93°C","Arjun"));
        dataBean.add(new DataBean("60°C","Sruthik"));
        dataBean.add(new DataBean("60°C","Sruthik"));
        dataBean.add(new DataBean("60°C","Shelly"));
        dataBean.add(new DataBean("60°C","Anand"));
        dataBean.add(new DataBean("60°C","Anand"));
        dataBean.add(new DataBean("60°C","Anand"));
        dataBean.add(new DataBean("60°C","Anand"));

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this,2);
        DataDBHelper dbHelper = new DataDBHelper(this);
        mSQLiteDB = dbHelper.getWritableDatabase();
        mAdapter = new DataAdapter(this, getAllItems());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddActivity();
            }
        });
    }
    public void openAddActivity(){
        Intent intent = new Intent(this,AddNew.class);
        intent.pu
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
}