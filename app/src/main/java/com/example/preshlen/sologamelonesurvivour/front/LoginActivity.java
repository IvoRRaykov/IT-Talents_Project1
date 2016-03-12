package com.example.preshlen.sologamelonesurvivour.front;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.User;
import com.example.preshlen.sologamelonesurvivour.model.UserManager;


public class LoginActivity extends AppCompatActivity {

    private static Button signIn;
    private static Button register;

    private static EditText email;
    private static EditText password;

    UserManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.e("da", "create");
        manager = UserManager.getInstance(LoginActivity.this);

        signIn = (Button) findViewById(R.id.signInButton);
        email = (EditText) findViewById(R.id.emailField);
        password = (EditText) findViewById(R.id.passwordField);
        register = (Button) findViewById(R.id.regButton);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("da", "onclick");
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                if (emailText.isEmpty()) {
                    Log.e("da", "emptyemail");
                    email.setError("This field is required.");
                    return;
                }
                if (passwordText.isEmpty()) {
                    Log.e("da", "emptypass");

                    password.setError("This field is required.");
                    return;
                }
                Log.e("da", "before managerlogin");
                User user = manager.login(emailText, passwordText);
                if (user == null) {
                    Toast.makeText(LoginActivity.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(LoginActivity.this, "Login sucssessfull!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }


}