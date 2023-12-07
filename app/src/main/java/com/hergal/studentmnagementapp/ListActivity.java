package com.hergal.studentmnagementapp;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hergal.studentmnagementapp.model.Filiere;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Filiere> dataList;
    MyAdapter adapter;
    Filiere androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

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
        dataList = new ArrayList<>();

        androidData = new Filiere(10,"Web Inteligence & Data Science", "WISD" , "balabla balabalabalabalabalabal");
        dataList.add(androidData);

        androidData = new Filiere(1,"Machine Learning Avancées & Inteligence Multimédia", "Mlaim" , "balabla balabalabalabalabalabal");
        dataList.add(androidData);

        androidData = new Filiere(110,"Qualité Logiciel", "MQL" , "balabla balabalabalabalabalabal");
        dataList.add(androidData);

        androidData = new Filiere(150,"Big Data Analytics & Smart System", "WISD" , "balabla balabalabalabalabalabal");
        dataList.add(androidData);


        adapter = new MyAdapter(ListActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<Filiere> dataSearchList = new ArrayList<>();
        for (Filiere data : dataList){
            if (data.getNom().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}