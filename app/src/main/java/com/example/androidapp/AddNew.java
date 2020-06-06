package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNew extends AppCompatActivity {
    private EditText mEditTextName;
    private EditText mEditTextTemp;
    private SQLiteDatabase msqlDB;
    private DataAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);
        DataDBHelper dbHelper = new DataDBHelper(this);
        msqlDB = dbHelper.getWritableDatabase();
        mEditTextName =  findViewById(R.id.editTextName);
        mEditTextTemp =  findViewById(R.id.editTextTemp);
        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }
    private void saveToDB(){
        if (mEditTextName.getText().toString().trim().length() ==0 || mEditTextTemp.getText().toString().trim().length() == 0){
            return;
        }
        String name = mEditTextName.getText().toString();
        String temp = mEditTextTemp.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put(DataContract.DBEntry.COLUMN_NAME,name);
        cv.put(DataContract.DBEntry.COLUMN_TEMP,temp);
        msqlDB.insert(DataContract.DBEntry.TABLE_NAME,null,cv);
        //mAdapter.swapCursor(getAllItems());
        mEditTextName.getText().clear();
        mEditTextTemp.getText().clear();

    }
    public Cursor getAllItems(){
        return msqlDB.query(
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