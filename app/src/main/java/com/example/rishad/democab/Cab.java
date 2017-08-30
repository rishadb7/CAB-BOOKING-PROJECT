package com.example.rishad.democab;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rishad on 29/7/17.
 */

public class Cab {


    private String id;
    @SerializedName("vehiclename")
    private String vehicleName;

    private String vehicleNumber;
    private String feature;
    @SerializedName("minrate")
    private String minimumRate;
    private String hours;
    private String kilometer;
    @SerializedName("description")
    private String discription;
    @SerializedName("image")
    private String imageUrl;




    public Cab(){}




    public Cab(String vehicleName, String minimumRate)
    {
        this.vehicleName=vehicleName;
        this.minimumRate=minimumRate;
    }


    public Cab(String vehicleName,String minimumRate,String imageUrl)
    {
        this.vehicleName=vehicleName;
        this.minimumRate=minimumRate;
        this.imageUrl=imageUrl;
    }


  public Cab(String vehicleName,String vehicleNumber,String feature,String minimumRate,String hours,String kilometer,String discription,String imageUrl)
  {
      this.vehicleName=vehicleName;
      this.vehicleNumber=vehicleNumber;
      this.feature=feature;
      this.minimumRate=minimumRate;
      this.hours=hours;
      this.kilometer=kilometer;
      this.discription=discription;
      this.imageUrl=imageUrl;
  }

  public String getVehicleName()
  {
      return vehicleName;

  }
  public void setVehicleName(String vehicleName)
  {
      this.vehicleName=vehicleName;
  }

  public String getId(){ return id;}
  public  void setId(String id){ this.id=id;}



  public String getVehicleNumber()
  {
      return vehicleNumber;
  }

  public void setVehicleNumber(String vehicleNumber)
  {
      this.vehicleNumber=vehicleNumber;
  }

  public String getFeature()
  {
      return feature;
  }

  public void setFeature(String feature)
  {
      this.feature=feature;
  }

  public String getMinimumRate()
  {
      return minimumRate;
  }

  public void setMinimumRate(String minimumRate)
  {
      this.minimumRate=minimumRate;
  }

  public String getHours()
  {
      return hours;
  }
  public void setHours(String hours)
  {
      this.hours=hours;
  }

  public String getKilometer()
  {
      return kilometer;
  }

  public void setKilometer(String kilometer)
  {
      this.kilometer=kilometer;
  }

  public String getDiscription()
  {
      return discription;
  }

  public void setDiscription(String discription)
  {
      this.discription=discription;
  }

  public String getImageUrl()
  {
      return imageUrl;
  }

  public void setImageUrl(String imageUrl)
  {
      this.imageUrl=imageUrl;
  }
}
