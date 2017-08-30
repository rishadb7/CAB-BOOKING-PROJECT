package com.example.rishad.democab;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by rishad on 29/7/17.
 */

public class CabsAdapter extends RecyclerView.Adapter<CabsAdapter.CabViewHolder> {

    private Context mContext;
    private List<Cab> cabList;
    private int check;

   // private Intent i;
    private String description;

    public class CabViewHolder extends RecyclerView.ViewHolder {
        public TextView name, price;
        public ImageView thumbnail;
      //  public ImageButton bookNow;
        Typeface tf_regular;
        public View v;
        // private Item currentItem;


        public CabViewHolder(View view) {
            super(view);
            v = view;
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "book now", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(mContext, BookingActivity.class);

                    thumbnail.buildDrawingCache();
                    Bitmap image = thumbnail.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("vehicleImage", image);
                    String vName = name.getText().toString();
                    String vPrice = price.getText().toString();
                    extras.putString("vehicleName", vName);
                    extras.putString("vehicleMiRate", vPrice);
                    extras.putString("discription",description);
                    i.putExtras(extras);


                    mContext.startActivity(i);
                }
            });

            name = (TextView) view.findViewById(R.id.title);
            price = (TextView) view.findViewById(R.id.price);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        //    bookNow = (ImageButton) view.findViewById(bookNow);

           /* name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "book  now", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, BookingActivity.class);

                    thumbnail.buildDrawingCache();
                    Bitmap image = thumbnail.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("vehicleImage", image);
                    String vName = name.getText().toString();
                    String vPrice = price.getText().toString();
                    extras.putString("vehicleName", vName);
                    extras.putString("vehicleMiRate", vPrice);
                    extras.putString("discription",description);
                    i.putExtras(extras);
                    mContext.startActivity(i);
                }
            });*/

           /* price.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "book  now", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, BookingActivity.class);

                    thumbnail.buildDrawingCache();
                    Bitmap image = thumbnail.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("vehicleImage", image);
                    String vName = name.getText().toString();
                    String vPrice = price.getText().toString();
                    extras.putString("vehicleName", vName);
                    extras.putString("vehicleMiRate", vPrice);
                    extras.putString("discription",description);
                    i.putExtras(extras);
                    mContext.startActivity(i);
                }
            });*/





            thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "book  now", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, BookingActivity.class);

                    thumbnail.buildDrawingCache();
                    Bitmap image = thumbnail.getDrawingCache();
                    Bundle extras = new Bundle();
                    extras.putParcelable("vehicleImage", image);
                    String vName = name.getText().toString();
                    String vPrice = price.getText().toString();
                    extras.putString("vehicleName", vName);
                    extras.putString("vehicleMiRate", vPrice);
                    extras.putString("discription",description);
                    i.putExtras(extras);
                    mContext.startActivity(i);
                }
            });
        

        }
    }


    public CabsAdapter(Context mContext, List<Cab> cabList) {
        this.mContext = mContext;
        this.cabList = cabList;


    }

    @Override
    public CabViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cab_card, parent, false);

        return new CabViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CabViewHolder holder, int position) {
        Cab cab = cabList.get(position);
        holder.name.setText(cab.getVehicleName());
        holder.price.setText(cab.getMinimumRate() + "");
        description=cab.getDiscription();

        Glide.with(mContext).load(cab.getImageUrl()).into(holder.thumbnail);



    }


    @Override
    public int getItemCount() {
        return cabList.size();
    }
}