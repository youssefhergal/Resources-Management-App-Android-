package com.hergal.studentmnagementapp.ui.filiere;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;
import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.ui.home.UpdateActivity;

public class DetailActivity extends AppCompatActivity {


    ImageView detailImage;

    TextView detailDesc, detailTitle, detailCode;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailCode = findViewById(R.id.detailCode);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            detailDesc.setText(bundle.getString("Desc"));
            detailTitle.setText(bundle.getString("Title"));
            detailCode.setText(bundle.getString("Code"));
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
                Intent intent = new Intent(DetailActivity.this, UpdateActivity.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Code",  detailCode.getText().toString())
                        .putExtra("Image", imageUrl);
                startActivity(intent);
            }
        });
    }
}
