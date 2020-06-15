package com.example.androidapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TempAdapter extends RecyclerView.Adapter<TempAdapter.TempViewHolder> {
    private Context mContext;
    private ArrayList<lastTemperaturesList> mCursor;
    public TempAdapter(Context context, ArrayList<lastTemperaturesList> cursor){
        mContext = context;
        mCursor = cursor;
    }

    public static class TempViewHolder extends RecyclerView.ViewHolder{
        public TextView mTempView;
        public TextView mDateView;
        public TextView mIndex;
        public TempViewHolder(@NonNull View itemView) {
            super(itemView);
            mTempView = itemView.findViewById(R.id.Temperature);
            mIndex = itemView.findViewById(R.id.id);
            mDateView = itemView.findViewById(R.id.Date);
        }
    }
    @Override
    public TempViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.temperature_card_click,parent,false);
        return new TempAdapter.TempViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TempViewHolder holder, int position) {
        lastTemperaturesList currentList = mCursor.get(position);
        String index = (position+1) + ".";
        holder.mIndex.setText(index);
        holder.mTempView.setText(String.valueOf(currentList.getTemperature()));
        holder.mDateView.setText(currentList.getDateItem());

    }

    @Override
    public int getItemCount() {
        if (mCursor == null){
            return 0;
        }else {
            return mCursor.size();
        }
    }

}
