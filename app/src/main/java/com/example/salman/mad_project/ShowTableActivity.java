package com.example.salman.mad_project;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

public class ShowTableActivity extends AppCompatActivity implements Custom_asynctask4.Onstart {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_table);
        listView = (ListView) findViewById(R.id.listview);
        Custom_asynctask4 custom_asynctask4 = new Custom_asynctask4(ShowTableActivity.this);
        custom_asynctask4.execute();
    }

    @Override
    public void getstart(ArrayList<Users> list) {
        int layoutId = R.layout.custom_adapter_users;
        custom_adapter_users adapter = new custom_adapter_users(this, layoutId, list);
        listView.setAdapter(adapter);
    }
}

