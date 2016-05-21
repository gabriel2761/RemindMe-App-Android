package com.example.gabriel.remindme;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabriel.remindme.model.Time;
import com.example.gabriel.remindme.model.TimeDbHelper;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private final Context mContext;
    private final FragmentManager mSupportFragmentManager;
    private final ArrayList<Time> mData = new ArrayList<>();
    private final int REQUEST_EDIT_ACTIVITY = 1;

    public TimeAdapter(Context context, FragmentManager supportFragmentManager) {
        this.mContext = context;
        this.mSupportFragmentManager = supportFragmentManager;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.time_textview);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext, EditActivity.class);
            intent.putExtra(Constant.EDIT_ACTIVITY_TIME_PARCELABLE,
                    mData.get(getAdapterPosition()));
            Activity activity = (Activity) mContext;
            activity.startActivityForResult(intent, REQUEST_EDIT_ACTIVITY);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.time_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String time = mData.get(position).getHours() + " " + mData.get(position).getMinutes();
        holder.mTextView.setText(time);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void refreshTimes() {
        TimeDbHelper dbHelper = new TimeDbHelper(mContext);
        mData.clear();
        mData.addAll(dbHelper.getTimeEntries());
        notifyDataSetChanged();
    }

    public int getRequestActivityResult() {
        return REQUEST_EDIT_ACTIVITY;
    }
}
