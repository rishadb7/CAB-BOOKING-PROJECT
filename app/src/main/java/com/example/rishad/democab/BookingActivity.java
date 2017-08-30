package com.example.rishad.democab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookingActivity extends AppCompatActivity {

    private Button bookNow2;
    private TextView vehicleName,charge,minCharge,hour,description,name;
    private ImageButton plus,minus;
    private ImageView vehicleImage;
    private int count = 3;
    private int min = 1;
    private Bundle extras;
    private String vName,vPri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        plus = (ImageButton)findViewById(R.id.imageButton2);
        minus = (ImageButton)findViewById(R.id.imageButton3);
        vehicleImage=(ImageView)findViewById(R.id.vehicleImage);
        vehicleName=(TextView)findViewById(R.id.vehicleName);
        charge=(TextView)findViewById(R.id.charge);
        minCharge=(TextView)findViewById(R.id.minCharge);
        hour=(TextView)findViewById(R.id.hour);
        description=(TextView)findViewById(R.id.description);
        hour.setText("" + count);

        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/UbuntuLight.ttf");
        vehicleName.setTypeface(tf);
        charge.setTypeface(tf);
        minCharge.setTypeface(tf);
        hour.setTypeface(tf);
        description.setTypeface(tf);


        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookNow2 =(Button)findViewById(R.id.bookNow2);




        extras = getIntent().getExtras();
        Bitmap bmp = (Bitmap) extras.getParcelable("vehicleImage");
        vName = extras.getString("vehicleName");
        vPri=extras.getString("vehicleMiRate");
        vehicleName.setText(vName);
        vehicleImage.setImageBitmap(bmp );
        charge.setText(vPri);
        description.setText(extras.getString("discription"));


        plus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                  count = count + 1;
                    hour.setText("" + count);
                   // Toast.makeText(BookingActivity.this,"sa",Toast.LENGTH_LONG).show();
                }
                catch(NumberFormatException nfe)
                {
                    System.out.println("Could not parse " + nfe);
                }

            }

        });
        minus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (count == 1)
                {
                    Toast.makeText(BookingActivity.this,"Limit exceeded",Toast.LENGTH_LONG).show();
                }
                else
                    {
                    count = count - 1;
                    hour.setText("" + count);
                }
            }
        });

        bookNow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "booking confirm", Toast.LENGTH_SHORT).show();


                SharedPreferences sharedPreferences = getSharedPreferences("key",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("vehicleName", vName);
                editor.putString("minRate", vPri);
                editor.putString("count", hour.getText().toString());
                editor.commit();
                Intent i =new Intent(BookingActivity.this,LoginActivity.class);


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
