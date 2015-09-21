package com.example.manuel.pilarapp;

import com.example.manuel.pilarapp.Objects.Request;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.RestAdapter;
import retrofit.http.Headers;

/**
 * Created by Manuel on 19/09/2015.
 */
public class ApiManager {

    private static final String API_URL = "http://zaragoza.es/api";

    private static final RestAdapter REST_ADAPTER = new RestAdapter.Builder()
            .setEndpoint(API_URL)
            .build();

    private static final APIService API_SERVICE = REST_ADAPTER.create(APIService.class);

    public static APIService getApiService() {
        return API_SERVICE;
    }

    public interface APIService {
        @Headers("Accept: application/json")
        @GET("/recurso/cultura-ocio/evento-zaragoza?results_only=false&rows=1")
        public void getRequest(Callback<Request> callback);

    }

}