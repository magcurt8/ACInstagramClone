package com.example.mcurtis.ac_instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

import com.shashank.sony.fancytoastlib.FancyToast;

public class LogInView extends AppCompatActivity implements View.OnClickListener {
    private EditText userNameInput, passwordInput;
    private Button logInButton;
    private TextView signUpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        setTitle("Log In");
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);

        logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(this);

        signUpText = findViewById(R.id.signUpText);
        signUpText.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.logInButton:
                ParseUser.logInInBackground(userNameInput.getText().toString(),
                        passwordInput.getText().toString(),
                        new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {

                                if (user != null && e == null) {
                                    FancyToast.makeText(LogInView.this,
                                            user.getUsername() + " is Logged in successfully",
                                            Toast.LENGTH_SHORT, FancyToast.SUCCESS,
                                            true).show();
                                    navigateToHomeScreen();
                                }
                            }
                        });

                break;

            case R.id.signUpText:
                Intent intent = new Intent(LogInView.this, SignUpView.class);
                startActivity(intent);
                break;
        }
    }

    private void navigateToHomeScreen() {
        Intent intent = new Intent(LogInView.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
