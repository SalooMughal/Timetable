package com.example.salman.mad_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Timetable_viewActivity extends AppCompatActivity implements Custom_asynctask3.Onstart {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        listView = (ListView) findViewById(R.id.listview);
        Custom_asynctask3 custom_asynctask3= new Custom_asynctask3(Timetable_viewActivity.this);
        custom_asynctask3.execute();



    }

    @Override
    public void getstart(final ArrayList<Timetable> list) {
        int layoutId = R.layout.custom_adapter;
        custom_adapter adapter = new custom_adapter(this, layoutId, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Timetable timetable=list.get(position);

                Intent intent=new Intent(getApplicationContext(),ManageTimetableActivity.class);
                intent.putExtra("id",timetable.getId());
                startActivity(intent);
            }
        });



    }

}
