package com.example.rishad.democab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by user on 8/8/17.
 */

public class DispatcherActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Class<?> activityClass;
        try
        {
            SharedPreferences prefs = getSharedPreferences("X", MODE_PRIVATE);
            activityClass = Class.forName(prefs.getString("lastActivity", LauchActivity.class.getName()));
        } catch (ClassNotFoundException e)
        {
            activityClass = LauchActivity.class;
        }
        Intent i = new Intent(DispatcherActivity.this, activityClass);
        startActivity(i);
    }
}
