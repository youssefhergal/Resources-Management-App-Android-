package com.hergal.studentmnagementapp.dao;


import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.hergal.studentmnagementapp.model.Professeur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfesseurDao {
    private static final String BASE_URL = "http://192.168.0.165:8080/api/professeurs";
    private RequestQueue requestQueue;

    public ProfesseurDao(Context context) {
        // Initialize the RequestQueue.
        requestQueue = Volley.newRequestQueue(context);
    }

    public void getAllProfesseurs(final VolleyCallback<List<Professeur>> callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Professeur> professeurList = new ArrayList<>();
                        Log.d("API_RESPONSE", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Parse the JSON response and create Professeur objects
                                Professeur professeur = parseProfesseurFromJson(response.getJSONObject(i));
                                professeurList.add(professeur);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Callback with the list of professeurs
                        callback.onSuccess(professeurList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        callback.onError(error.getMessage());
                    }
                });

        // Add the request to the RequestQueue.
        requestQueue.add(jsonArrayRequest);
    }

    public void addProfesseur(Professeur professeur, final VolleyCallback<String> callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Callback with the success message
                        callback.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        callback.onError(error.getMessage());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                // Convert Professeur object to a Map of parameters
                Map<String, String> params = new HashMap<>();
                params.put("email", professeur.getEmail());
                params.put("nom", professeur.getNom());
                params.put("prenom", professeur.getPrenom());
                params.put("specialite", professeur.getSpecialite());
                return params;
            }
        };

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest);
    }

    private Professeur parseProfesseurFromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        String email = jsonObject.getString("email");
        String nom = jsonObject.getString("nom");
        String prenom = jsonObject.getString("prenom");
        String specialite = jsonObject.getString("specialite");

        return new Professeur(id, email, nom, prenom, specialite);
    }

    public interface VolleyCallback<T> {
        void onSuccess(T result);

        void onError(String errorMessage);
    }
}
