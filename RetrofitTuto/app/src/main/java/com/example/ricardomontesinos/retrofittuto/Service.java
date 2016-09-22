package com.example.ricardomontesinos.retrofittuto;

import com.example.ricardomontesinos.retrofittuto.models.Catalog;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    public static final String BASE_URL = "https://www.udacity.com/public-api/v0/";

    @GET("courses")
    Call<Catalog> listCatalog();

}
