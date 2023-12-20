package com.hergal.studentmnagementapp.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import java.util.Calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.hergal.studentmnagementapp.R;

public class AjouterStudentActivity extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    EditText FirstName, LastName, Email,DateInput;
    Spinner spinnerFiliere ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_activity_ajout);

        FirstName = findViewById(R.id.FirstName);
        LastName = findViewById(R.id.LastName);
        Email = findViewById(R.id.Email);
        spinnerFiliere = findViewById(R.id.spinnerFiliere);
        DateInput = findViewById(R.id.DateInput);
        saveButton = findViewById(R.id.saveButton);


        String[] filiereOptions = {"Option 1", "Option 2", "Option 3", "Option 4"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, filiereOptions);
        spinnerFiliere.setAdapter(adapter);


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // saveData();
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
