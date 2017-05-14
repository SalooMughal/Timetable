package com.example.salman.mad_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/11/2017.
 */

public class Custom_asynctask4 extends AsyncTask<String, String, String> {
    SharedPreferences sharedPreferences;
    ProgressDialog progressDialog;
    private static Context context;
    OkHttpClient client = new OkHttpClient();
    final ArrayList<Users> list = new ArrayList<Users>();

    public Custom_asynctask4(Context c) {
        context = c;
    }

    Onstart onstart;

    public interface Onstart {
        void getstart(ArrayList<Users> list);
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
        sharedPreferences = context.getSharedPreferences("mypreference", Context.MODE_PRIVATE);
        String a = sharedPreferences.getString("token", null);
        try {
            Thread.sleep(1000);
            Request request = new Request.Builder()
                    .url("http://192.168.43.32/mad_project/api/users?token=" + a)
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
            JSONArray arr = jsonObject.getJSONArray("users");

            for (int i = 0; i < arr.length(); i++) {
                JSONObject o = arr.getJSONObject(i);
                list.add(new Users(o.getString("id"), o.getString("name"), o.getString("email")));
            }
            onstart.getstart(list);


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

}
