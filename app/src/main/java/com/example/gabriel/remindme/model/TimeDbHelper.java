package com.example.gabriel.remindme.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

public class TimeDbHelper extends SQLiteOpenHelper {

    public TimeDbHelper(Context context) {
        super(context, DatabaseContract.DATABASE_NAME, null, DatabaseContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DatabaseContract.TimeTable.SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DatabaseContract.TimeTable.SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void addTimeEntry(int hours, int minutes) {
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.TimeTable.HOURS, hours);
        values.put(DatabaseContract.TimeTable.MINUTES, minutes);

        SQLiteDatabase db = getWritableDatabase();
        long success = db.insert(
                DatabaseContract.TimeTable.TABLE_NAME, null, values);
    }

    public ArrayList<String> getTimeEntries() {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                DatabaseContract.TimeTable._ID,
                DatabaseContract.TimeTable.HOURS,
                DatabaseContract.TimeTable.MINUTES
        };

        Cursor c = db.query(
                DatabaseContract.TimeTable.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        ArrayList<String> values = new ArrayList<>();

        if (c.moveToFirst()) {
            do {
                int hours = c.getColumnIndex(DatabaseContract.TimeTable.HOURS);
                int minutes = c.getColumnIndex(DatabaseContract.TimeTable.MINUTES);
                values.add(String.format("%s %s",  c.getInt(hours), c.getInt(minutes)));
            } while (c.moveToNext());
        }

        c.close();

        return values;
    }
}
