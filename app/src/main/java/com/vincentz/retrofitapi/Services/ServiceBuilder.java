package com.vincentz.retrofitapi.Services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "http://192.168.43.232:8080/DynamicWebAPIProjectv3/api/";
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create());
    private static Retrofit retrofit = builder.build();
    public static <S> S buildService(Class<S> serviceType)
    {
        return retrofit.create(serviceType);
    }
}
