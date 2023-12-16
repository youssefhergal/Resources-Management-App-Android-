package com.hergal.studentmnagementapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.hergal.studentmnagementapp.ui.filiere.ListActivity;
import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.StudentActivity;

public class DashboardActivity extends AppCompatActivity {

    CardView etudiantcard;
    CardView modulecard;
    CardView profcard;
    CardView filierecard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashbord_layout);

        etudiantcard = findViewById(R.id.etudiantCard);
        filierecard = findViewById(R.id.filiereCard);

        System.out.println("test");

        etudiantcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, StudentActivity.class);
                startActivity(intent);
            }
        });

        filierecard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ListActivity.class);
                startActivity(intent);

            }
        });
    }
}