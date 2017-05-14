package com.example.salman.mad_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/11/2017.
 */

public class Custom_asynctask3 extends AsyncTask<String, String, String> {
    private static Context context;
    ProgressDialog progressDialog;
    OkHttpClient client = new OkHttpClient();
    final ArrayList<Timetable> list = new ArrayList<Timetable>();

    public Custom_asynctask3(Context c) {
        context = c;
    }

    Onstart onstart;

    public interface Onstart {
        void getstart(ArrayList<Timetable> list);

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        onstart = (Onstart) context;
        progressDialog = ProgressDialog.show(context,
                "",

                "Wait......");
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(1000);
            Request request = new Request.Builder()
                    .url("http://192.168.43.32/mad_project/time")
                    .get()
                    .build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        progressDialog.dismiss();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray arr = jsonObject.getJSONArray("timetable");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Timetable(o.getString("id"), o.getString("day"), o.getString("time_slot"), o.getString("room_no"), o.getString("course_name"), o.getString("status")));
            }
            onstart.getstart(list);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
