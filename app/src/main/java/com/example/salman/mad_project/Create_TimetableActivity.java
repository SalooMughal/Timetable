package com.example.salman.mad_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class Create_TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__timetable);
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
        time.add("3:30-5");
        List<String> room = new ArrayList<String>();
        room.add("CS0001");
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

                String tday = spinner1.getSelectedItem().toString();
                String ttime = spinner2.getSelectedItem().toString();
                String troom = spinner3.getSelectedItem().toString();
                String tsubject = spinner4.getSelectedItem().toString();
                String tstatus = spinner5.getSelectedItem().toString();
                Custom_asynctask_createTimetable custom_asynctask_createTimetable = new Custom_asynctask_createTimetable(Create_TimetableActivity.this);
                custom_asynctask_createTimetable.execute(tday, ttime, troom, tsubject, tstatus);

            }
        });

    }
}
