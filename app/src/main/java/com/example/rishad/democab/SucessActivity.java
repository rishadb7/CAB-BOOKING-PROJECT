package com.example.rishad.democab;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class SucessActivity extends AppCompatActivity
{
    private static int TIME_OUT = 5000;

    private TextView booked;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        booked=(TextView)findViewById(R.id.Booked);
        Typeface tf =Typeface.createFromAsset(getAssets(),"fonts/UbuntuLightItalic.ttf");
        booked.setTypeface(tf);
        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(SucessActivity.this,"thank you",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(SucessActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
