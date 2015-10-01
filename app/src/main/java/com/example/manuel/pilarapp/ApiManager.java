package com.example.manuel.pilarapp;

import com.example.manuel.pilarapp.Objects.Acto;
import com.example.manuel.pilarapp.Objects.Request;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Manuel on 19/09/2015.
 */
public class ApiManager {

    private static final String API_URL = "http://zaragoza.es/api";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .setLogLevel(RestAdapter.LogLevel.FULL)
            .build();

    private static final APIActos API_SERVICE = REST_ADAPTER.create(APIActos.class);

    public static APIActos getApiService() {
        return API_SERVICE;
    }

    public interface APIActos{

        @Headers("Accept: application/json")
        @GET("/recurso/cultura-ocio/evento-zaragoza?rf=html&results_only=false&srsname=wgs84&rows=1000&q=programa%3D%3Dfiestas%20del%20pilar")
        public void getRequest(Callback<Request> callback);

        @Headers("Accept: application/json")
        @GET("/recurso/cultura-ocio/evento-zaragoza?rf=html&results_only=false&srsname=wgs84&rows=1000")
        public void getRequest(@Query("q") String fecha, Callback<Request> callback);

        @Headers("Accept: application/json")
        @GET("/recurso/cultura-ocio/evento-zaragoza/{id}")
        public void getActo(@Path("id") int id, Callback<Acto> callback);
    }
}