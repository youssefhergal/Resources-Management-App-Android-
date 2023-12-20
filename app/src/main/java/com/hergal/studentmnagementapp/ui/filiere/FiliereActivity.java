package com.hergal.studentmnagementapp.ui.filiere;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import com.google.android.filament.View;
import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.dao.FiliereDao;
import com.hergal.studentmnagementapp.model.Filiere;
import com.hergal.studentmnagementapp.ui.home.DashboardActivity;
import com.hergal.studentmnagementapp.ui.student.StudentActivity;

import java.util.ArrayList;
import java.util.List;

public class FiliereActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Filiere> filiereList;
    FiliereAdapter adapter;
    FiliereDao filiereDao;
    SearchView searchView;
    FloatingActionButton addFiliere;
    TextView errorMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_filiere);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);
        FloatingActionButton addFiliere = findViewById(R.id.addFiliere);
        errorMessageTextView = findViewById(R.id.errorMessageTextView);

        // Initialize the filiereList
        filiereList = new ArrayList<>();
        filiereDao = new FiliereDao(this);

        // Uncomment the following line to load filieres from the API
        loadFilieresFromApi();

        addFiliere.setOnClickListener(new android.view.View.OnClickListener() {


            @Override
            public void onClick(android.view.View v) {
                showAddFiliereDialog();
            }

        });



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




        GridLayoutManager gridLayoutManager = new GridLayoutManager(FiliereActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);

        // Comment the following lines since we will load data from the API
        // Filiere f1 = new Filiere(10, "Wisd", "Web Intelligence & Science données", "blablabalaabaajajajakakkkaakkakkakk", "Yahyaoui Ali","2023");
        // Filiere f2 = new Filiere(10, "Mql", "Web Intelligence & Science données", "blablabalaabaajajajakakkkaakkakkakk", "Yahyaoui Ali","2015");
        // Filiere f3 = new Filiere(10, "Bdsas", "Web Intelligence & Science données", "blablabalaabaajajajakakkkaakkakkakk", "Yahyaoui Ali","2023");

        // Uncomment the following line to use the data loaded from the API
        // filiereList.add(f1);
        // filiereList.add(f2);
        // filiereList.add(f3);

        if (adapter == null) {
            adapter = new FiliereAdapter(FiliereActivity.this, filiereList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setSearchList(filiereList);
        }
    }


    private void showAddFiliereDialog() {
        Intent intent = new Intent(FiliereActivity.this, AjouterFiliereActivity.class);
        startActivity(intent);
    }

    private void loadFilieresFromApi() {
        filiereDao.getAllFilieres(new FiliereDao.VolleyCallback<List<Filiere>>() {
            @Override
            public void onSuccess(List<Filiere> result) {
                // Update the list of filieres in the adapter
                updateFiliereList(result);
            }
            @Override
            public void onError(String errorMessage) {
                // Handle errors
                Toast.makeText(FiliereActivity.this, "Error loading filieres from API", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFiliereList(List<Filiere> filieres) {
        filiereList = filieres;
        if (adapter == null) {
            adapter = new FiliereAdapter(FiliereActivity.this, filiereList);
            recyclerView.setAdapter(adapter);
        } else {
            adapter.setSearchList(filiereList);
        }
    }

    private void searchList(String text) {
        List<Filiere> dataSearchList = new ArrayList<>();
        for (Filiere data : filiereList) {
            if (data.getCode().toLowerCase().contains(text.toLowerCase())) {
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
