package com.example.preshlen.sologamelonesurvivour.front;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preshlen.sologamelonesurvivour.R;
import com.example.preshlen.sologamelonesurvivour.model.User;
import com.example.preshlen.sologamelonesurvivour.model.managers.UserManager;

public class RegisterActivity extends AppCompatActivity {

    private UserManager userManager;

    private static Button register;

    private static EditText username;
    private static EditText password;
    private static EditText confirmPassword;
    private static EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        userManager = UserManager.getInstance(this);

        register = (Button) findViewById(R.id.registerButton);

        username        = (EditText) findViewById(R.id.usernameField);
        password        = (EditText) findViewById(R.id.passwordField);
        confirmPassword = (EditText) findViewById(R.id.confirmPasswordField);
        email           = (EditText) findViewById(R.id.emailField);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameTxt = username.getText().toString();
                String passwordTxt = password.getText().toString();
                String emailTxt = email.getText().toString();

                boolean usernameCheck = false;
                boolean emailCheck = false;
                boolean passwordCheck = false;
                boolean passwordMatch = false;

                //TODO check if username already exists in database!
                if (userManager.existUsername(usernameTxt)) {
                    username.setError("Username already exists");
                    return;
                } else {
                    usernameCheck = true;
                }
                //TODO check if email already exists in database!
                if (userManager.existEmail(emailTxt)) {
                    email.setError("Email already exists");
                    return;
                } else {
                    emailCheck = true;
                }

                if (password.getText().toString().isEmpty()) {
                    password.setError("This field is required.");
                    return;
                } else {
                    if (!checkPasswordStrength(password.getText().toString())) {
                        password.setError("Password is too weak\n At least 8 symbols \n At least 1 lowercase and uppercase \n At least 1 number");
                        return;
                    } else
                        passwordCheck = true;

                    if (checkPasswordStrength(password.getText().toString()) && !password.getText().toString().equals(confirmPassword.getText().toString())) {
                        confirmPassword.setError("Passwords don't match");
                        return;
                    } else
                        passwordMatch = true;
                }

                if (usernameCheck && emailCheck && passwordCheck && passwordMatch) {
                    //TODO register in database and log in
                    User user = new User(emailTxt, passwordTxt, usernameTxt);
                    long id = userManager.register(user);
                    if (id != -1)
                        Toast.makeText(getApplicationContext(), "register successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    public boolean checkPasswordStrength(String password) {
        char[] test = password.toCharArray();

        boolean lowercase = false;
        boolean uppercase = false;
        boolean number = false;

        if(test.length < 8)
            return false;

        for (char aTest : test) {
            if (aTest >= 65 && aTest <= 90)
                lowercase = true;
            if (aTest >= 97 && aTest <= 122)
                uppercase = true;
            if (aTest >= 48 && aTest <= 57)
                number = true;
        }

        return !(!lowercase || !uppercase || !number);

    }


}