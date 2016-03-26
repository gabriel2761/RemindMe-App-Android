package com.example.gabriel.remindme;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.format.DateFormat;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.gabriel.remindme.model.TimeDbHelper;

import java.util.Calendar;

/**
 * Created by gab on 26/03/2016.
 */
public class TimesetDialog extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        TimeDbHelper dbHelper = new TimeDbHelper(getContext());
        dbHelper.addTimeEntry(hourOfDay, minute);
    }
}
