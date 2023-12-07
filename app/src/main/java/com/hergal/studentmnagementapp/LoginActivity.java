package com.hergal.studentmnagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hergal.studentmnagementapp.dao.UserDao;

public class LoginActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button loginButton;

    SessionManager sessionManager ;
    UserDao dao = new UserDao(this);




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        sessionManager = new SessionManager(this);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dao.checkUser(username.getText().toString(), password.getText().toString()) || true) {
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    sessionManager.saveUsername(username.getText().toString());
                    Intent intent = new Intent(LoginActivity.this , DashboardActivity.class );
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}