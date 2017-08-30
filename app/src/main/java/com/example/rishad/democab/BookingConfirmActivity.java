package com.example.rishad.democab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BookingConfirmActivity extends AppCompatActivity {

    private Button continueBtn;
    private TextView  Vehiclename,hour1,hour2,price,pickUp,pickUp2;

   // private String vName,vRate,vehicleNames,minimumRate,counts,date,time,pickUpoint,phoneNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirm);

        Vehiclename=(TextView)findViewById(R.id.Vehiclename);
        hour1=(TextView)findViewById(R.id.hour1);
        hour2=(TextView)findViewById(R.id.hour2);
        price=(TextView)findViewById(R.id.price);
        pickUp=(TextView)findViewById(R.id.pickUp);
        pickUp2=(TextView)findViewById(R.id.pickUp2);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/UbuntuLight.ttf");
        Vehiclename.setTypeface(tf);

        SharedPreferences sharedPreferences = getSharedPreferences("key",0);
        Vehiclename.setText(sharedPreferences.getString("vehicleName",""));
        price.setText(sharedPreferences.getString("minRate",""));


        hour1.setText(sharedPreferences.getString("count",""));
        pickUp2.setText(sharedPreferences.getString("pickUpPoint",""));

        continueBtn=(Button)findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(BookingConfirmActivity.this,"booking confirming",Toast.LENGTH_LONG).show();
                Intent i = new Intent(BookingConfirmActivity.this,PaymentActivity.class);
                startActivity(i);
            }
        });
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

