package com.example.salman.mad_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/11/2017.
 */

public class Custom_asynctask_createTimetable extends AsyncTask<String, String, String> {
    private static Context context;
    ProgressDialog progressDialog;
    OkHttpClient client = new OkHttpClient();

    public Custom_asynctask_createTimetable(Context c) {
        context = c;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = ProgressDialog.show(context,
                "Login",

                "Wait......");

    }

    @Override
    protected String doInBackground(String... params) {
        try {
            Thread.sleep(1000);
            RequestBody formBody = new FormBody.Builder()
                    .add("day", params[0])
                    .add("time_slot", params[1])
                    .add("room_no", params[2])
                    .add("course_name", params[3])
                    .add("status", params[4])
                    .build();

            Request request = new Request.Builder()
                    .url("http://192.168.43.32/mad_project/time")
                    .post(formBody)
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
        if (s.equals("Created")) {
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Wrong Input", Toast.LENGTH_SHORT).show();
        }

    }

}
