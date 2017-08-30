package com.example.rishad.democab;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by rishad on 6/8/17.
 */

public interface ApiService {

    @GET("getlocation.php")
    Call<List> getMyJSON(@Query("apiKey") String apiKey,@Query("rideremail")String riderEmail);


    @GET("getvehicle.php")
    Call<List<Cab>> getCabDetails(@Query("apiKey") String apiKey);


    @POST("booking.php")
    @FormUrlEncoded
    Call<Post> savePost(@Field("apiKey") String apiKey,
                        @Field("vehicleName") String vehicleNames,
                        @Field("pickUpPoint") String pickPoints,
                        @Field("date") String dates,
                        @Field("time") String times,
                        @Field("riderPhone") String phoneNumber,
                        @Field("email") String email
                        );

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://34.223.214.194/projects/CabAdmin/api/")

            .addConverterFactory(GsonConverterFactory.create())
            .build();






}
