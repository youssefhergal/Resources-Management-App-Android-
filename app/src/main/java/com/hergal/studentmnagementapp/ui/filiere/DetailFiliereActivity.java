package com.hergal.studentmnagementapp.ui.filiere;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.hergal.studentmnagementapp.R;

public class DetailFiliereActivity extends AppCompatActivity {


    ImageView detailImage;

    TextView code, intitule, detailCode , description , coronnateur , date_creation;
    FloatingActionButton deleteButton, editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_filiere);

        code = findViewById(R.id.CodeFiliere);
        intitule = findViewById(R.id.IntituleFiliere);
        date_creation = findViewById(R.id.DateCreationValue);
        coronnateur = findViewById(R.id.CordonnateurValue);
        editButton = findViewById(R.id.editButton);
        deleteButton = findViewById(R.id.deleteButton);
        description = findViewById(R.id.DescriptionValue);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            code.setText(bundle.getString("code"));
            description.setText(bundle.getString("description"));
            date_creation.setText(bundle.getString("date_creation"));
            intitule.setText(bundle.getString("intitule"));
            coronnateur.setText(bundle.getString("cordonnateur"));
        }


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                return;
            }
        });


        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailFiliereActivity.this, UpdateFiliereActivity.class)
                        .putExtra("code", code.getText().toString())
                        .putExtra("description", description.getText().toString())
                        .putExtra("intitule",  intitule.getText().toString())
                        .putExtra("date_creation", date_creation.getText().toString())
                        .putExtra("cordonnateur",coronnateur.getText().toString());
                startActivity(intent);
            }
        });
    }
}
