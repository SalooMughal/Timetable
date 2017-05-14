package com.example.salman.mad_project;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by SALMAN on 5/11/2017.
 */

public class Custom_asynctask2 extends AsyncTask<String, String, String> {
    private static Context context;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;
    OkHttpClient client = new OkHttpClient();

    public Custom_asynctask2(Context c) {
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
        sharedPreferences = context.getSharedPreferences("mypreference", Context.MODE_PRIVATE);
        try {
            Thread.sleep(2000);
            RequestBody formBody = new FormBody.Builder()
                    .add("name", params[0])
                    .add("email", params[1])
                    .add("password", params[2])
                    .build();
            Request request = new Request.Builder()
                    .url("http://192.168.43.32/mad_project/userregister")
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

    String name = null;
    String email = null;
    String password = null;

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s.equals("register")) {
            progressDialog.dismiss();
            Toast.makeText(context, "User Registered", Toast.LENGTH_SHORT).show();
            FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
            LoginFragment blankFragment = new LoginFragment();
            fragmentTransaction.replace(android.R.id.content, blankFragment).commit();
        } else {
            progressDialog.dismiss();
            Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        }
    }

}
