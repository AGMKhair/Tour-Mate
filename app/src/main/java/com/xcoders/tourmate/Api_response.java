package com.xcoders.tourmate;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api_response {

    private static Retrofit retrofit;
    private static  String BASE_URL1="http://api.openweathermap.org/data/2.5/";


    public static Retrofit getCurrentWeather() {
        retrofit=new Retrofit.Builder().baseUrl(BASE_URL1).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
