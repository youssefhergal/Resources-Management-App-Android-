package com.hergal.studentmnagementapp.ui.student;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hergal.studentmnagementapp.R;

public class UpdateProfileActivity extends AppCompatActivity {

    EditText editNom, editPrenom, editEmail, editDate;
    Spinner editFiliere;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_edit_profile);

        editNom = findViewById(R.id.editLastName);
        editPrenom = findViewById(R.id.editFirstName);
        editEmail = findViewById(R.id.editEmail);
        editFiliere = findViewById(R.id.spinnerFiliere);
        editDate = findViewById(R.id.editDate);
        saveButton = findViewById(R.id.saveButton);

        // Récupérer les données de l'intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String nom = extras.getString("Nom");
            String prenom = extras.getString("Prenom");
            String email = extras.getString("Email");
            String date = extras.getString("DateInscription");
            String filiere = extras.getString("Filiere");

            editNom.setText(nom);
            editDate.setText(date);
            editPrenom.setText(prenom);
            editEmail.setText(email);

            // Adapter pour le Spinner avec une liste fictive de filières
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this,
                    R.array.filiere_options,  // Assurez-vous que vous avez une liste de filières dans vos ressources
                    android.R.layout.simple_spinner_item
            );
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            editFiliere.setAdapter(adapter);

            // Trouver l'indice de la filière dans la liste
            int position = adapter.getPosition(filiere);

            // Sélectionner la filière dans le Spinner
            if (position != -1) {
                editFiliere.setSelection(position);
            }
        }

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Mettez en œuvre la logique de sauvegarde ici
                // Vous pouvez récupérer les valeurs actuelles des champs et les sauvegarder dans la base de données, par exemple.

                Toast.makeText(UpdateProfileActivity.this, "Profil mis à jour avec succès", Toast.LENGTH_SHORT).show();
                finish(); // Fermez l'activité après la sauvegarde
            }
        });
    }
}
