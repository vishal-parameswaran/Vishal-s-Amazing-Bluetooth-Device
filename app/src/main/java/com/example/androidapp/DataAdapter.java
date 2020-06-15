package com.example.androidapp;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
    public DataAdapter(Context context, Cursor cursor){
        mContext = context;
        mCursor = cursor;

    }
    public static class DataViewHolder extends RecyclerView.ViewHolder{
        public TextView mTempView;
        public TextView mNameView;

        public DataViewHolder( View itemView) {
            super(itemView);
            mTempView = itemView.findViewById(R.id.temp);
            mNameView = itemView.findViewById(R.id.name);
        }
    }

    @Override
    public DataViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_layout,parent,false);
        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DataViewHolder holder, final int position) {
        if (!mCursor.moveToPosition(position)){
            return ;
        }
        holder.mTempView.setText(String.valueOf(mCursor.getInt(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_TEMP))));
        holder.mNameView.setText(mCursor.getString(mCursor.getColumnIndex(DataContract.DBEntry.COLUMN_NAME)));
        final int value = position;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(mContext,CardClickView.class);
                intents.putExtra("position",position);
                mContext.startActivity(intents);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

}
