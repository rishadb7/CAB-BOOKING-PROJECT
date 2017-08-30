package com.example.rishad.democab;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.rishad.democab.R.id.swipeRefreshLayout;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private CabsAdapter adapter;
    private List<Cab> cabList;

    private TextView name,email;
    private ImageView pic;
    SQLiteDatabase db;
    private GoogleApiClient mGoogleApiClient;
    Context context;

    private ProgressBar progressBar;
    private SwipeRefreshLayout mSwipeRefreshLayout;




    @Override
    protected void onPause()
    {
        super.onPause();
        SharedPreferences prefs = getSharedPreferences("X", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("lastActivity", getClass().getName());
        editor.commit();
    }

    @Override
    public void onRestart()
    {
        super.onRestart();
        SharedPreferences sharedPreferences = getSharedPreferences("key",0);
        name.setText(sharedPreferences.getString("personname",""));
        email.setText(sharedPreferences.getString("email",""));
        Glide.with(getApplicationContext())
                .load(sharedPreferences.getString("photourl",""))
                .bitmapTransform(new CropCircleTransformation(this))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pic);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        progressBar=(ProgressBar)findViewById(R.id.circular_progress_bar);

        mSwipeRefreshLayout=(SwipeRefreshLayout)findViewById(swipeRefreshLayout);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        cabList =new ArrayList<>();
        adapter =new CabsAdapter(this,cabList);



        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);


        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        ObjectAnimator anim = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        anim.setDuration(15000);
        anim.setInterpolator(new DecelerateInterpolator());
        anim.start();


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {


                                cabList.clear();
                                adapter.notifyDataSetChanged();
                                prepareCabs();


            }
        });

        prepareCabs();



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);

        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header=navigationView.getHeaderView(0);

        name = (TextView)header.findViewById(R.id.name);
        email = (TextView)header.findViewById(R.id.email);
        pic = (ImageView)header.findViewById(R.id.pic);

        SharedPreferences sharedPreferences = getSharedPreferences("key",0);
        name.setText(sharedPreferences.getString("personname",""));
        email.setText(sharedPreferences.getString("email",""));
        Glide.with(getApplicationContext())
                .load(sharedPreferences.getString("photourl",""))
                .bitmapTransform(new CropCircleTransformation(this))
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(pic);

    }
    @Override
    protected void onStart() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                    .setMessage("Are you sure?")
                    .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                            System.exit(0);
                        }
                    }).setNegativeButton("no", null).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home, menu);





        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

/*
        if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.getMyLocaction)
        {

            Intent i =new Intent(getApplicationContext(),GetMyLocationActivity.class);
            startActivity(i);

        }
        else if (id == R.id.trips)
        {
            Intent i =new Intent(getApplicationContext(),TripsActivity.class);
            startActivity(i);
        }
        else if (id == R.id.contact)
        {
            Intent i =new Intent(getApplicationContext(),ContactActivity.class);
            startActivity(i);
        }
        else if (id == R.id.aboutUs)
        {
            Intent i =new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(i);
        }
        else if (id == R.id.logout)
        {




            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(Status status) {
                            SharedPreferences sharedPreferences =getSharedPreferences("key",0);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.clear();
                            editor.commit();
                            finish();
                            // ...
                            Toast.makeText(getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                            Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                            startActivity(i);
                        }
                    });



        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }






    private void prepareCabs() {





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://34.223.214.194/projects/CabAdmin/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service =retrofit.create(ApiService.class);


        Call<List<Cab>> call =service.getCabDetails("mongodb786");

        call.enqueue(new Callback<List<Cab>>() {
            @Override
            public void onResponse(Call<List<Cab>> call, Response<List<Cab>> response) {

                List<Cab> list=response.body();
                Cab cab=null;



                for(int i=0;i<list.size();i++)
                {
                    cab =new Cab();

                    String vname=list.get(i).getVehicleName();
                    String vprice=list.get(i).getMinimumRate();
                    String vimage=list.get(i).getImageUrl();
                    String vdiscription=list.get(i).getDiscription();
                    String url="http://34.223.214.194/projects/CabAdmin/admin/image/vehicleimage/";
                    String imagePath=url+vimage;

                    cab.setVehicleName(vname);
                    cab.setMinimumRate(vprice);
                    cab.setImageUrl(imagePath);
                    cab.setDiscription(vdiscription);
                    cabList.add(cab);


                    adapter.notifyDataSetChanged();
                }


                progressBar.setVisibility(View.GONE);
                mSwipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Cab>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Loading", Toast.LENGTH_SHORT).show();
               // progressBar.setVisibility(View.VISIBLE);

               // mSwipeRefreshLayout.setRefreshing(false);
            }
        });





    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

}