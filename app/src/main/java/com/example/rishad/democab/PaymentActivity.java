package com.example.rishad.democab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {


    private Button confirmRide;
    private RadioButton credit,netBanking,cashOnDelivery,giftCards,myWallets;
    private Bundle getBundle;
    private String vehicleNames,minimumRate,date,time,pickUpoint,phoneNumber,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);


        credit =(RadioButton)findViewById(R.id.credit);
      //  netBanking=(RadioButton)findViewById(R.id.netBanking);
        cashOnDelivery=(RadioButton)findViewById(R.id.cashOnDelivery);
     //   giftCards=(RadioButton)findViewById(R.id.giftCard);
     //   myWallets=(RadioButton)findViewById(R.id.myWallet);

        Typeface tf =Typeface.createFromAsset(getAssets(),"fonts/UbuntuLight.ttf");
        credit.setTypeface(tf);
    //    netBanking.setTypeface(tf);
        cashOnDelivery.setTypeface(tf);
//        giftCards.setTypeface(tf);
  //      myWallets.setTypeface(tf);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("key",0);

        vehicleNames=sharedPreferences.getString("vehicleName","");
        minimumRate=sharedPreferences.getString("minRate","");
        date=sharedPreferences.getString("date","");
        time=sharedPreferences.getString("time","");
        pickUpoint=sharedPreferences.getString("pickUpPoint","");
        phoneNumber=sharedPreferences.getString("riderPhone","");
        email=sharedPreferences.getString("email","");






        confirmRide =(Button)findViewById(R.id.confirmRide);
        confirmRide.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               // Toast.makeText(getApplicationContext(), "Confirm ride", Toast.LENGTH_LONG).show();

                sendPost();
              //  Toast.makeText(getApplicationContext(), vehicleNames+minimumRate+date+time+pickUpoint, Toast.LENGTH_LONG).show();
                Intent i = new Intent(PaymentActivity.this,SucessActivity.class);
                startActivity(i);
            }
        });


    }

    public void sendPost()
    {
        String apiKey2="mongodb786";
        ApiService calls = ApiService.retrofit.create(ApiService.class);
        Call<Post> call =calls.savePost(apiKey2,vehicleNames,pickUpoint,date,time,phoneNumber,email);
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                Toast.makeText(getApplicationContext(), "Payment success and inserted", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();

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
