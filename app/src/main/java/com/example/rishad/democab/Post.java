package com.example.rishad.democab;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by rishad on 20/8/17.
 */

public class Post {

    @SerializedName("apiKey")
    @Expose
    private String apiKey;
    @SerializedName("vehicleName")
    @Expose
    private String vehicleNames;

    @SerializedName("pickUpPoint")
    @Expose
    private String pickupPonis;

    @SerializedName("date")
    @Expose
    private String dates;

    @SerializedName("time")
    @Expose
    private String times;



    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getVehicleNames(){
        return vehicleNames;
    }

    public void setVehicleNames(String vehicleNames){
        this.vehicleNames=vehicleNames;
    }

    public String getPickupPonis()
    {
        return pickupPonis;
    }

    public void setPickupPonis(String pickupPonis)
    {
        this.pickupPonis=pickupPonis;
    }

    public String getDates()
    {
        return dates;
    }

    public void setDates(String dates)
    {
        this.dates=dates;
    }

    public String getTimes()
    {
        return times;
    }

    public void setTimes(String times)
    {
        this.times=times;
    }
}
