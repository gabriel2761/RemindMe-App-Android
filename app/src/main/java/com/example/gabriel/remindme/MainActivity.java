package com.example.gabriel.remindme;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.gabriel.remindme.dialogs.TimesetDialog;
import com.example.gabriel.remindme.model.TimeDbHelper;

public class MainActivity extends AppCompatActivity
        implements TimesetDialog.OnTimeSelectedListener {

    private final TimeAdapter mTimeAdapter = new TimeAdapter(this, getSupportFragmentManager());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView timeList = (RecyclerView) findViewById(R.id.recycler_view);
        timeList.setHasFixedSize(true);
        timeList.setLayoutManager(new LinearLayoutManager(this));

        timeList.setAdapter(mTimeAdapter);
        mTimeAdapter.refreshTimes();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimesetDialog dialog = new TimesetDialog();
                dialog.show(getSupportFragmentManager(), Constant.TIME_DIALOG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void OnTimeSelected(int hourOfDay, int minute) {
        TimeDbHelper dbHelper = new TimeDbHelper(this);
        dbHelper.addTimeEntry(hourOfDay, minute);
        mTimeAdapter.refreshTimes();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == mTimeAdapter.getRequestActivityResult()) {
            mTimeAdapter.refreshTimes();
        }
    }
}
