package com.hergal.studentmnagementapp.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.clans.fab.FloatingActionButton;
import androidx.appcompat.widget.SearchView;

import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.model.Etudiant;

import java.util.ArrayList;
import java.util.List;




public class StudentActivity extends AppCompatActivity {

    //FloatingActionButton fab;
    RecyclerView recyclerViewStudent;
    List<Etudiant> dataList;
    StudentAdapter adapter;

    Etudiant etudiant;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_liste);

        recyclerViewStudent = findViewById(R.id.recyclerViewStudent);
        searchView = findViewById(R.id.search);
        searchView.clearFocus();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(StudentActivity.this, 1);
        recyclerViewStudent.setLayoutManager(gridLayoutManager);

        dataList = new ArrayList<>();

        etudiant =  new Etudiant(10,"youssef", "hergal","youssefher247@gmail.com",12,"25/04/2019","Wisd");
        dataList.add(etudiant);
        etudiant =  new Etudiant(10,"salim", "slimani","K1513755",10,"10/12/2021","Mql");
        dataList.add(etudiant);

        adapter = new StudentAdapter(StudentActivity.this, dataList);
        recyclerViewStudent.setAdapter(adapter);



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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, AjouterStudentActivity.class);
                startActivity(intent);
            }
        });



    }

    public void searchList(String text){
        ArrayList<Etudiant> searchList = new ArrayList<>();
        for (Etudiant dataClass: dataList){
            if (dataClass.getNom().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}
