package com.example.rishad.democab;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

public class PickupActivity extends AppCompatActivity {


    private Button nextButton;
    private EditText point;
    private EditText date;
    private EditText time;
    private EditText phone;
    private int d,m,y;
    private int hour,min;
    private String count;
  //  private TextView tv;
    SQLiteDatabase db;

    public Bundle getBundle2 ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        count = getIntent().getStringExtra("count");

        nextButton = (Button)findViewById(R.id.nextButton);
        point = (EditText)findViewById(R.id.pickUpPoint);
        date = (EditText)findViewById(R.id.date);
        time = (EditText)findViewById(R.id.time);
        phone = (EditText)findViewById(R.id.mobileNo);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/UbuntuLight.ttf");
        point.setTypeface(tf);
        date.setTypeface(tf);
        time.setTypeface(tf);
        phone.setTypeface(tf);

        nextButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {if (point.length() == 0)
            {
                point.setError("Field can't empty");
            }
            else if (date.length() == 0)
            {
                date.setError("Field can't empty");
            }
            else if (time.length() == 0)
            {
                time.setError("Field can't empty");
            }
            else if (phone.length() == 0)
            {
                phone.setError("Field can't empty");
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Wait...", Toast.LENGTH_SHORT).show();



                Intent i =new Intent(PickupActivity.this,BookingConfirmActivity.class);


                SharedPreferences sharedPreferences = getSharedPreferences("key",0);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("pickUpPoint", point.getText().toString());
                editor.putString("date", date.getText().toString());
                editor.putString("time",time.getText().toString() );
                editor.putString("riderPhone",phone.getText().toString() );
                editor.commit();


                startActivity(i);
            }
            }
        });


        date.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        final Dialog dialog = new Dialog(PickupActivity.this);
                                        dialog.setContentView(R.layout.dateddialog);
                                        dialog.setTitle("Date");

                                        final DatePicker datePicker = (DatePicker) dialog.findViewById(R.id.datePicker2);
                                        Button btnsetdate = (Button) dialog.findViewById(R.id.button5);

                                        dialog.show();
                                        btnsetdate.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                d = datePicker.getDayOfMonth();
                                                m = datePicker.getMonth() + 1;
                                                y = datePicker.getYear();

                                                date.setText(d + "/" + m + "/" + y);
                                                dialog.dismiss();
                                            }
                                        });

                                    }
                                });




                time.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        final Dialog dialog = new Dialog(PickupActivity.this);
                        dialog.setContentView(R.layout.timepick);
                        dialog.setTitle("Time");

                        final TimePicker timePicker = (TimePicker) dialog.findViewById(R.id.timePicker);
                        Button btnsettime = (Button)dialog.findViewById(R.id.button2);

                        dialog.show();
                        btnsettime.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                hour = timePicker.getCurrentHour();
                                min = timePicker.getCurrentMinute();
                                time.setText(hour + ":" + min);
                                dialog.dismiss();
                            }



                        });
                    }
                });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                    Intent intent = new Intent(this, HomeActivity.class);
               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;

            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }



}
