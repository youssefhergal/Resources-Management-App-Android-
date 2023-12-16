package com.hergal.studentmnagementapp.dao;

import com.hergal.studentmnagementapp.model.Filiere;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api.php")
    Call<List<Filiere>> getAllFilieres();


    Call<Void> addFiliere(Filiere filiere);
}
