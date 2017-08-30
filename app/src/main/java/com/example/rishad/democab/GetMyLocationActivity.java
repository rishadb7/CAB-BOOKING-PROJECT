package com.example.rishad.democab;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetMyLocationActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ArrayList<Location> locationList;
    List<Location> Locations;
    private String cityname;
    private static final String TAG = GetMyLocationActivity.class.getSimpleName();
    final Handler handler = new Handler();
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_my_location);


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {
                            getLocation();
                            Toast.makeText(getApplicationContext(), "update map", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                        }
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 10000);





    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    public void getLocation()
    {
        ApiService calls =ApiService.retrofit.create(ApiService.class);
        String apiKey="mongodb786";
        SharedPreferences sharedPreferences = getSharedPreferences("key",0);
        String email=sharedPreferences.getString("email","");
        Call<List> call =calls.getMyJSON(apiKey,email);
        call.enqueue(new Callback<List>() {
            @Override
            public void onResponse(Call<List> call, Response<List> response) {


               /* if(response.body()==null)
                {
                    ArrayList<LatLng> points = new ArrayList<LatLng>();
                    PolylineOptions polyLineOptions = new PolylineOptions();
                    double lat= Double.parseDouble("0.000")  ;
                    double lon=Double.parseDouble("0.0000") ;

                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                    try
                    {
                        List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
                        cityname = addresses.get(0).getAddressLine(0);


                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                    LatLng point = new LatLng(lat, lon);

                    points.add(new LatLng(lat,lon));

                    polyLineOptions.width(7 * 1);
                    polyLineOptions.geodesic(true);
                    polyLineOptions.color(Color.BLUE);
                    polyLineOptions.addAll(points);
                    Polyline polyline = mMap.addPolyline(polyLineOptions);
                    polyline.setGeodesic(true);

                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(point).title(cityname));
                    mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

                    timer.cancel();



                }*/



                try{
                    JSONArray jArray = new JSONArray(response.body().toString());


                    if(jArray.length()==0)
                    {
                        ArrayList<LatLng> points = new ArrayList<LatLng>();
                        PolylineOptions polyLineOptions = new PolylineOptions();
                        double lat= Double.parseDouble("0.000")  ;
                        double lon=Double.parseDouble("0.0000") ;

                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                        try
                        {
                            List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
                            cityname = addresses.get(0).getAddressLine(0);


                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }

                        LatLng point = new LatLng(lat, lon);

                        points.add(new LatLng(lat,lon));

                        polyLineOptions.width(7 * 1);
                        polyLineOptions.geodesic(true);
                        polyLineOptions.color(Color.BLUE);
                        polyLineOptions.addAll(points);
                        Polyline polyline = mMap.addPolyline(polyLineOptions);
                        polyline.setGeodesic(true);

                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(point).title(cityname));
                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

                        timer.cancel();



                    }


                    JSONObject jObject = null;






                    ArrayList<LatLng> points = new ArrayList<LatLng>();
                    PolylineOptions polyLineOptions = new PolylineOptions();

                    int i;
                    for ( i=0;i<jArray.length();i++)
                    {




                        jObject=jArray.getJSONObject(i);


                        String latitude =jObject.getString("latitude");
                        String longitude =jObject.getString("longitude");
                        //        Toast.makeText(getApplicationContext(),latitude,Toast.LENGTH_SHORT).show();

                        double lat= Double.parseDouble(latitude)  ;
                        double lon=Double.parseDouble(longitude) ;

                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

                        try
                        {
                            List<Address> addresses = geocoder.getFromLocation(lat,lon,1);
                            cityname = addresses.get(0).getAddressLine(0);


                        } catch (IOException e)
                        {
                            e.printStackTrace();
                        }


                        LatLng point = new LatLng(lat, lon);

                        points.add(new LatLng(lat,lon));

                        polyLineOptions.width(7 * 1);
                        polyLineOptions.geodesic(true);
                        polyLineOptions.color(Color.BLUE);
                        polyLineOptions.addAll(points);
                        Polyline polyline = mMap.addPolyline(polyLineOptions);
                        polyline.setGeodesic(true);


                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(point).title(cityname));
                        mMap.animateCamera( CameraUpdateFactory.zoomTo( 17.0f ) );
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(point));

                        // mMap.setMinZoomPreference(12.0f);
                        // mMap.setMaxZoomPreference(30.0f);

                    }



                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<List> call, Throwable t) {

                Toast.makeText(getApplicationContext(),"fail response",Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroy()
    {
        timer.cancel();
        System.exit(0);
        super.onDestroy();
    }



}
