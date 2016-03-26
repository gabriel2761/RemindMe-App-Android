package com.example.gabriel.remindme.model;

import android.provider.BaseColumns;

public final class DatabaseContract {

    public static final int DATABASE_VERSION = 2;
    public static final String DATABASE_NAME = "timetable.db";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    
    // Suppress Instantiation
    private DatabaseContract() {}

    public static abstract class TimeTable implements BaseColumns {
        public static final String TABLE_NAME = "timetable";
        public static final String HOURS = "hours";
        public static final String MINUTES = "minutes";

        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TimeTable.TABLE_NAME + " (" +
                        TimeTable._ID + " INTEGER PRIMARY KEY," +
                        TimeTable.HOURS + INTEGER_TYPE + COMMA_SEP +
                        TimeTable.MINUTES + INTEGER_TYPE +
                        " )";
        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TimeTable.TABLE_NAME;

    }
}
