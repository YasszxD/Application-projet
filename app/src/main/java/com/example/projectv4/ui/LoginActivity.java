package com.example.projectv4.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.projectv4.R;

import com.example.projectv4.controller.Controller;
import com.example.projectv4.data.AppContract;
import com.example.projectv4.ui.app.AthletesActivity;
import com.example.projectv4.ui.app.MainActivity;

public class LoginActivity extends AppCompatActivity {

    EditText username = null;
    EditText password = null;
    TextView forget = null;
    Button login = null;
    TextView signup = null;
    String username_string = null;
    String password_string = null;

    public Controller controller = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
        controller = Controller.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkEntries()) {
                    Controller.setContext(LoginActivity.this);
                    if (Controller.checkData(username_string, password_string)) {
                        if(Controller.checkRole(username_string, AppContract.Player.COACH)){
                            Toast.makeText(LoginActivity.this, "Welcome Coach", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(LoginActivity.this, AthletesActivity.class);
                            startActivity(i);
                        }
                        if(Controller.checkRole(username_string, AppContract.Player.ATHLETE)){
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }

                    } else
                        Toast.makeText(LoginActivity.this, "username + password 8altin", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(LoginActivity.this, "entries 8altin", Toast.LENGTH_SHORT).show();

            }
        });
    }

    void init() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        forget = (TextView) findViewById(R.id.forget);
        login = (Button) findViewById(R.id.login);
        signup = (TextView) findViewById(R.id.signup);
    }

    boolean checkEntries() {

        if (username.getText().toString().trim().equals("") || password.getText().toString().trim().equals(""))
            return false;

        username_string = username.getText().toString().trim().toLowerCase();
        password_string = password.getText().toString().trim().toLowerCase();
        return true;
    }
}