package com.hergal.studentmnagementapp.ui.filiere;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.dao.FiliereDao;
import com.hergal.studentmnagementapp.model.Filiere;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Filiere> dataList;
    MyAdapter adapter;
    FiliereDao filiereDao;
    SearchView searchView;
    FloatingActionButton addFiliere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        addFiliere = findViewById(R.id.addFiliere);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(ListActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        filiereDao = new FiliereDao();

        // Charger les filières depuis l'API au démarrage de l'activité
        loadFilieresFromApi();

        addFiliere.setOnClickListener(view -> {
            // Créer un objet Filiere avec les données nécessaires
            Filiere filiere = new Filiere();
            filiere.setNom("Nom de la filière"); // Remplacez par le nom réel de la filière

            // Ajouter la filière à l'API
            addFiliereToApi(filiere);
        });
    }

    private void loadFilieresFromApi() {
        filiereDao.getAllFilieres(new Callback<List<Filiere>>() {
            @Override
            public void onResponse(Call<List<Filiere>> call, Response<List<Filiere>> response) {
                if (response.isSuccessful()) {
                    List<Filiere> filieres = response.body();
                    // Mettre à jour la liste des filières dans l'adaptateur
                    updateFiliereList(filieres);
                } else {
                    // Gérer les erreurs
                    Toast.makeText(ListActivity.this, "Erreur lors du chargement des filières depuis l'API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Filiere>> call, Throwable t) {
                // Gérer les échecs de la requête
                Toast.makeText(ListActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addFiliereToApi(Filiere filiere) {
        filiereDao.addFiliere(filiere, new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Filiale ajoutée avec succès
                    Toast.makeText(ListActivity.this, "Filière ajoutée avec succès", Toast.LENGTH_SHORT).show();

                    // Rafraîchir la liste des filières depuis l'API
                    loadFilieresFromApi();
                } else {
                    // Gérer les erreurs
                    Toast.makeText(ListActivity.this, "Erreur lors de l'ajout de la filière à l'API", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Gérer les échecs de la requête
                Toast.makeText(ListActivity.this, "Erreur réseau : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFiliereList(List<Filiere> filieres) {
        dataList = filieres;
        adapter = new MyAdapter(ListActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text) {
        List<Filiere> dataSearchList = new ArrayList<>();
        for (Filiere data : dataList) {
            if (data.getNom().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()) {
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}
