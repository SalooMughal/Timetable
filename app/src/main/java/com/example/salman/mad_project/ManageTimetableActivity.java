package com.example.salman.mad_project;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ManageTimetableActivity extends AppCompatActivity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_timetable);
        // Spinner element
        final Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spinner3 = (Spinner) findViewById(R.id.spinner3);
        final Spinner spinner4 = (Spinner) findViewById(R.id.spinner4);
        final Spinner spinner5 = (Spinner) findViewById(R.id.spinner5);
        Button button = (Button) findViewById(R.id.btnSubmit);

        // Spinner click listener
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner3.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner4.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner5.setOnItemSelectedListener(new CustomOnItemSelectedListener());
//         Spinner Drop down elements
        List<String> day = new ArrayList<String>();
        day.add("Tue");
        day.add("Wed");
        day.add("Thur");
        day.add("Fri");
        day.add("Sat");
        List<String> time = new ArrayList<String>();
        time.add("8-9:30");
        time.add("9:30-11");
        time.add("11-12:30");
        time.add("12:30-2");
        time.add("2-3:30");
        time.add("3:30-0");
        List<String> room = new ArrayList<String>();
        room.add("CS001");
        room.add("CS002");
        room.add("CS003");
        room.add("CS004");
        room.add("CS005");
        List<String> subject = new ArrayList<String>();
        subject.add("PF");
        subject.add("ITC");
        subject.add("CAL");
        subject.add("ISL");
        subject.add("PAL");
        List<String> status = new ArrayList<String>();
        status.add("Held");
        status.add("Notheld");
        status.add("Late");


        // Creating adapter for spinner
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, day);
        ArrayAdapter<String> timeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, time);
        ArrayAdapter<String> roomAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, room);
        ArrayAdapter<String> subjectAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, subject);
        ArrayAdapter<String> statusAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, status);
//         Drop down layout style - list view with radio button
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        roomAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        subjectAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner1.setAdapter(dayAdapter);
        spinner2.setAdapter(timeAdapter);
        spinner3.setAdapter(roomAdapter);
        spinner4.setAdapter(subjectAdapter);
        spinner5.setAdapter(statusAdapter);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                String id = intent.getStringExtra("id");
                String tday = spinner1.getSelectedItem().toString();
                String ttime = spinner2.getSelectedItem().toString();
                String troom = spinner3.getSelectedItem().toString();
                String tsubject = spinner4.getSelectedItem().toString();
                String tstatus = spinner5.getSelectedItem().toString();
                Custom_asynctask_updateTimetable custom_asynctask_updateTimetable = new Custom_asynctask_updateTimetable(ManageTimetableActivity.this);
                custom_asynctask_updateTimetable.execute(id, tday, ttime, troom, tsubject, tstatus);

            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ManageTimetable Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
//    int i=0;
//    String[] values=new String[8];
//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String item = parent.getItemAtPosition(position).toString();
//        Toast.makeText(this,item,Toast.LENGTH_SHORT).show();
//        if(i>4)
//        {
//            values[position]=values[i];
//            i--;
//        }else {
//            values[i]=item;
//        }
//        i++;
//        Toast.makeText(this,values[i],Toast.LENGTH_SHORT).show();
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}
