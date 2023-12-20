package com.hergal.studentmnagementapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.hergal.studentmnagementapp.R;
import com.hergal.studentmnagementapp.dao.UserDao;
import com.hergal.studentmnagementapp.model.User;
import com.hergal.studentmnagementapp.ui.authentification.LoginActivity;
import com.hergal.studentmnagementapp.ui.authentification.SessionManager;

public class MainActivity extends AppCompatActivity {

    SessionManager sessionManager ;
    UserDao dao = new UserDao(this);

    Button logout ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        logout = findViewById(R.id.logout);



        if (!dao.checkUserExists("user" )){
            dao.addUser(new User("user" , "1234"));
        }
        sessionManager = new SessionManager(this);

        if (sessionManager.getUsername() != null) {
            String username = sessionManager.getUsername();
            setTitle("Bonjour " + username + "!");
        } else {
            // Utilisateur non connecté, rediriger vers l'activité de connexion
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Fermer cette activité pour éviter de revenir en arrière
        }
        
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sessionManager.clearSession();
                Intent intent = new Intent(MainActivity.this , LoginActivity.class );
                startActivity(intent);
                finish();
            }
        });
    }
}