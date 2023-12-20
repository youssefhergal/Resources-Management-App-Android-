package com.hergal.studentmnagementapp.ui.filiere;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.dao.FiliereDao;
import com.hergal.studentmnagementapp.dao.ProfesseurDao;
import com.hergal.studentmnagementapp.model.Filiere;
import com.hergal.studentmnagementapp.model.Professeur;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AjouterFiliereActivity extends AppCompatActivity {

    Button addButton;
    EditText CodeFiliere, IntituleFiliere, Description, DateCreation;
    Spinner Cordonnateur;
    ProfesseurDao professeurDao;
    FiliereDao filiereDao ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_filiere);

        addButton = findViewById(R.id.addButton);
        CodeFiliere = findViewById(R.id.CodeFiliere);
        IntituleFiliere = findViewById(R.id.intituleFiliere);
        Description = findViewById(R.id.DescriptionFiliere);
        Cordonnateur = findViewById(R.id.spinnerFiliere);
        DateCreation = findViewById(R.id.DateInput);

        professeurDao = new ProfesseurDao(this);
        filiereDao = new FiliereDao(this);



                addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Récupérer les données du formulaire
                        String code = CodeFiliere.getText().toString();
                        String intitule = IntituleFiliere.getText().toString();
                        String description = Description.getText().toString();
                        String cordonnateur = Cordonnateur.getSelectedItem().toString();
                        String dateCreation = DateCreation.getText().toString();

                        // Créer une instance de Filiere avec les données du formulaire
                        Filiere newFiliere = new Filiere(0,code,intitule,description,cordonnateur,dateCreation);

                        filiereDao.addFiliereToApi(newFiliere, new FiliereDao.VolleyCallback<Filiere>() {
                            @Override
                            public void onSuccess(Filiere result) {
                                // Gérer le succès de l'ajout de la filière
                                Toast.makeText(AjouterFiliereActivity.this, "Filière ajoutée avec succès", Toast.LENGTH_SHORT).show();

                                // Ajouter une logique supplémentaire si nécessaire, par exemple, retour à l'activité principale
                                finish(); // Termine l'activité actuelle
                            }

                            @Override
                            public void onError(String errorMessage) {
                                // Gérer les erreurs d'ajout de la filière
                                Toast.makeText(AjouterFiliereActivity.this, "Erreur lors de l'ajout de la filière : " + errorMessage, Toast.LENGTH_SHORT).show();
                            }

                        });

            }
        });

        DateCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // Récupérer et afficher la liste des professeurs dans le Spinner
        loadProfesseurs();
    }

    private void loadProfesseurs() {
        professeurDao.getAllProfesseurs(new ProfesseurDao.VolleyCallback<List<Professeur>>() {
            @Override
            public void onSuccess(List<Professeur> result) {
                // Mettre à jour le Spinner avec la liste des professeurs
                ArrayAdapter<Professeur> adapter = new ArrayAdapter<Professeur>(
                        AjouterFiliereActivity.this,
                        android.R.layout.simple_spinner_dropdown_item,
                        android.R.id.text1, result) {
                    @Override
                    public View getView(int position, View convertView, android.view.ViewGroup parent) {
                        View v = super.getView(position, convertView, parent);
                        if (v instanceof android.widget.TextView) {
                            ((android.widget.TextView) v).setText(result.get(position).getNom() + " " + result.get(position).getPrenom());
                        }
                        return v;
                    }
                };
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                Cordonnateur.setAdapter(adapter);
            }

            @Override
            public void onError(String errorMessage) {
                // Gérer les erreurs de récupération des professeurs
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Gérer la sélection de la date
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        DateCreation.setText(selectedDate);
                    }
                },
                // Définir la date initiale si nécessaire
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}
