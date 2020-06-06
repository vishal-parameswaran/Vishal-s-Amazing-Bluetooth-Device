package com.example.androidapp;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public DataAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;

    }
    public static class DataViewHolder extends RecyclerView.ViewHolder{
        public TextView mTempView;
        public TextView mNameView;

        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            mTempView = itemView.findViewById(R.id.temp);
            mNameView = itemView.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        DataViewHolder dvh = new DataViewHolder(v);
        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        if (!mCursor.move(position)){
            return ;
        }
        holder.mTempView.setText(String.valueOf(mCursor.getInt(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_TEMP))));
        holder.mNameView.setText(mCursor.getString(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_NAME)));
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(mCursor!= null){
            mCursor.close();
        }
        mCursor = newCursor;
        if (newCursor != null){
            notifyDataSetChanged();
        }
    }
}
