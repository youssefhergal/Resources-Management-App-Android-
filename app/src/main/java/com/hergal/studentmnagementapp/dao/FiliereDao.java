package com.hergal.studentmnagementapp.dao;

import com.hergal.studentmnagementapp.dao.ApiService;
import com.hergal.studentmnagementapp.model.Filiere;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FiliereDao {

    private ApiService apiService;

    public FiliereDao() {
        // Définissez l'URL de votre API
        String apiUrl = "http://localhost:8082";

        // Initialisez Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Créez une instance de ApiService
        apiService = retrofit.create(ApiService.class);
    }

    // Méthode pour récupérer toutes les filières de l'API distante
    public void getAllFilieres(Callback<List<Filiere>> callback) {
        Call<List<Filiere>> call = apiService.getAllFilieres();
        call.enqueue(callback);
    }

    public void addFiliere(Filiere filiere, Callback<Void> callback) {
        Call<Void> call = apiService.addFiliere(filiere);
        call.enqueue(callback);
    }

}
