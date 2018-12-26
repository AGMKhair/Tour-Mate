package com.xcoders.tourmate;

import com.xcoders.tourmate.weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api_service{



    @GET()
    Call<WeatherResponse> getweather(@Url String url);
}
