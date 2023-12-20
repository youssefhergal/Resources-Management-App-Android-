package com.hergal.studentmnagementapp.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hergal.studentmnagementapp.R;

public class StudentProfileActivity extends AppCompatActivity {

    TextView firstName, lastName, id, email, filiere, date;
    ImageView image;
    Button editProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_profile);

        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        email = findViewById(R.id.EmailValue);
        id = findViewById(R.id.IdValue);
        filiere = findViewById(R.id.FiliereValue);
        date = findViewById(R.id.DateValue);
        image = findViewById(R.id.profileImg);

        editProfile = findViewById(R.id.editButton);

        showAllUserData();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passUserData();
            }
        });
    }

    public void showAllUserData() {
        Intent intent = getIntent();
        String nameUser = intent.getStringExtra("Nom");
        String emailUser = intent.getStringExtra("Email");
        String usernameUser = intent.getStringExtra("Prenom");
        String userId = intent.getStringExtra("Id");
        String filiereUser = intent.getStringExtra("Filiere");
        String dateInscription = intent.getStringExtra("DateInscription");

        firstName.setText(nameUser);
        lastName.setText(usernameUser);
        email.setText(emailUser);
        id.setText(userId);
        filiere.setText(filiereUser);
        date.setText(dateInscription);

        // Chargez l'image à partir de la ressource ou de l'URL, selon vos besoins
        // image.setImageResource(R.drawable.image_resource);
        // ou
        // Glide.with(this).load("url_de_l_image").into(image);
    }

    public void passUserData() {
        String userUsername = lastName.getText().toString().trim();

        // Créez un nouvel objet Intent pour lancer l'activité UpdateStudentActivity
        Intent intent = new Intent(StudentProfileActivity.this, UpdateProfileActivity.class);

        // Ajoutez les données que vous souhaitez transmettre à l'Intent
        intent.putExtra("Nom", firstName.getText().toString());
        intent.putExtra("Prenom", userUsername);
        intent.putExtra("Email", email.getText().toString());
        intent.putExtra("Id", id.getText().toString());
        intent.putExtra("Filiere", filiere.getText().toString());
        intent.putExtra("DateInscription", date.getText().toString());

        startActivity(intent);
    }

}
