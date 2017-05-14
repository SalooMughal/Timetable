package com.example.salman.mad_project;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        LoginFragment blankFragment = new LoginFragment();
        fragmentTransaction.replace(android.R.id.content, blankFragment).commit();
    }
}
