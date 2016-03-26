package com.example.gabriel.remindme;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private final ArrayList<String> mData = new ArrayList<>();

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.time_textview);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addTime(int hourOfDay, int minute) {

        mData.add(String.format("%s %s", hourOfDay, minute));

        notifyDataSetChanged();
    }


}
