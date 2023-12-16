package com.hergal.studentmnagementapp.ui.filiere;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.hergal.studentmnagementapp.ui.home.MainActivity;
import com.hergal.studentmnagementapp.R;


public class AjouterFiliereActivity extends AppCompatActivity{
    ImageView saveImage;
    Button saveButton;
    EditText saveDesc, saveTitle, saveCode;
    String title, desc, code;
    String imageUrl;
    Uri uri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_filiere);

        saveButton = findViewById(R.id.saveButton);
        saveDesc = findViewById(R.id.saveDesc);
        saveImage = findViewById(R.id.saveImage);
        saveCode = findViewById(R.id.saveCode);
        saveTitle = findViewById(R.id.saveTitle);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            saveImage.setImageURI(uri);
                        } else {
                            Toast.makeText(AjouterFiliereActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


        saveImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                saveData();
                Intent intent = new Intent(AjouterFiliereActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
