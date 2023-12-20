package com.hergal.studentmnagementapp.ui.filiere;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.ui.home.MainActivity;

import java.util.Calendar;

public class UpdateFiliereActivity extends AppCompatActivity {

    ImageView updateImageFiliere;
    Button updateButton;
    EditText CodeFiliere, IntituleFiliere, DateInput, Description;
    Spinner spinnerFiliere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_filiere);

        updateButton = findViewById(R.id.updateButton);
        CodeFiliere = findViewById(R.id.CodeFiliere);
        IntituleFiliere = findViewById(R.id.intituleFiliere);
        spinnerFiliere = findViewById(R.id.spinnerFiliere);
        DateInput = findViewById(R.id.DateInput);
        Description = findViewById(R.id.DescriptionFiliere);

        // Set up Spinner with options
        String[] filiereOptions = {"Wisd", "Mlaim", "Option 3", "Option 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filiereOptions);
        spinnerFiliere.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            CodeFiliere.setText(bundle.getString("code"));
            IntituleFiliere.setText(bundle.getString("intitule"));
            // Set Filiere spinner selection
            String selectedFiliere = bundle.getString("cordonnateur");
            int position = adapter.getPosition(selectedFiliere);
            spinnerFiliere.setSelection(position);
            DateInput.setText(bundle.getString("date_creation"));
        }

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the update button click
                Intent intent = new Intent(UpdateFiliereActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Add the following code for date input
        DateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    // Method to show DatePickerDialog
    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Handle the date selection
                        String selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                        DateInput.setText(selectedDate);
                    }
                },
                // Set the initial date if needed
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }
}
