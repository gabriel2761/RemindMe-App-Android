package com.example.gabriel.remindme;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gabriel.remindme.dialogs.TimeEditDialog;
import com.example.gabriel.remindme.model.TimeDbHelper;

import java.util.ArrayList;

public class TimeAdapter extends RecyclerView.Adapter<TimeAdapter.ViewHolder> {

    private final Context mContext;
    private final FragmentManager mSupportFragmentManager;
    private final ArrayList<String> mData = new ArrayList<>();

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
            TimeEditDialog dialog = new TimeEditDialog();
            dialog.show(mSupportFragmentManager, Constant.EDIT_DIALOG);
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
        holder.mTextView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void addTime(int hourOfDay, int minute) {
        mData.add(String.format("%s %s", hourOfDay, minute));
    }

    public void refreshTimes() {
        TimeDbHelper dbHelper = new TimeDbHelper(mContext);
        mData.clear();
        mData.addAll(dbHelper.getTimeEntries());
        notifyDataSetChanged();
    }

}
