package com.example.gabriel.remindme.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Time implements Parcelable {
    private final int mId;
    private final int mHours;
    private final int mMinutes;

    public Time(int id, int hours, int minutes) {
        this.mId = id;
        this.mHours = hours;
        this.mMinutes = minutes;
    }

    protected Time(Parcel in) {
        mId = in.readInt();
        mHours = in.readInt();
        mMinutes = in.readInt();
    }

    public static final Creator<Time> CREATOR = new Creator<Time>() {
        @Override
        public Time createFromParcel(Parcel in) {
            return new Time(in);
        }

        @Override
        public Time[] newArray(int size) {
            return new Time[size];
        }
    };

    public int getId() {
        return mId;
    }

    public int getHours() {
        return mHours;
    }

    public int getMinutes() {
        return mMinutes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeInt(mHours);
        dest.writeInt(mMinutes);
    }
}
