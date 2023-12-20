package com.hergal.studentmnagementapp.dao;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.hergal.studentmnagementapp.model.Filiere;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FiliereDao {
    private static final String BASE_URL = "http://192.168.0.165:8080/api/filieres";
    private RequestQueue requestQueue;
    public FiliereDao(Context context) {
        // Initialize the RequestQueue.
        requestQueue = Volley.newRequestQueue(context);
    }
    public void getAllFilieres(final VolleyCallback<List<Filiere>> callback) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, BASE_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<Filiere> filiereList = new ArrayList<>();
                        Log.d("API_RESPONSE", response.toString());

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                // Parse the JSON response and create Filiere objects
                                Filiere filiere = parseFiliereFromJson(response.getJSONObject(i));
                                filiereList.add(filiere);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // Callback with the list of filières
                        callback.onSuccess(filiereList);
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

    public void addFiliereToApi(Filiere newFiliere, final VolleyCallback<Filiere> callback) {
        // Construire l'URL pour l'ajout de filière
        String addUrl = BASE_URL+"/create";

        JSONObject jsonBody = new JSONObject();
        try {
            // Ajouter les propriétés de la nouvelle filière au corps de la requête JSON
            jsonBody.put("code", newFiliere.getCode());
            jsonBody.put("intitule", newFiliere.getIntitule());
            jsonBody.put("description", newFiliere.getDescription());
            jsonBody.put("cordonnateur", newFiliere.getCordonateur());
            jsonBody.put("dateCreation", newFiliere.getDate_ouverture());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Créer la requête POST Volley
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                addUrl,
                jsonBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            // Parse the JSON response to create a Filiere object
                            Filiere addedFiliere = parseFiliereFromJson(response);
                            // Callback with the added filière
                            callback.onSuccess(addedFiliere);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            // Callback with an error message
                            callback.onError("Error parsing JSON response");
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle errors here
                        callback.onError(error.getMessage());
                    }
                });

        // Ajouter la requête à la file d'attente de requêtes
        requestQueue.add(jsonObjectRequest);
    }

    // Helper method to parse a Filiere from JSON
    private Filiere parseFiliereFromJson(JSONObject jsonObject) throws JSONException {
        int id = jsonObject.getInt("id");
        String code = jsonObject.getString("code");
        String intitule = jsonObject.getString("intitule");
        String description = jsonObject.getString("description");
        String cordonnateur = jsonObject.isNull("cordonnateur") ? null : jsonObject.getString("cordonnateur");
        String dateCreation = jsonObject.isNull("dateCreation") ? null : jsonObject.getString("dateCreation").substring(0,10);

        return new Filiere(id, code, intitule, description, cordonnateur, dateCreation);
    }


    // Callback interface for handling the response
    public interface VolleyCallback<T> {
        void onSuccess(T result);

        void onError(String errorMessage);
    }
}
