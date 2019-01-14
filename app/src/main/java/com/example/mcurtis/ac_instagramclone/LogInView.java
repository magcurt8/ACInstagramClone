package com.example.mcurtis.ac_instagramclone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);
        logInButton = findViewById(R.id.logInButton);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(userNameInput.getText().toString(), passwordInput.getText().toString(), new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        if (user != null && e == null) {
                            FancyToast.makeText
                                    (LogInView.this, user.get("username") + " logged in",
                                            FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        } else {
                            FancyToast.makeText
                                    (LogInView.this, e.getMessage(),
                                            FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        }
                    }
                });

            }
        });

        signUpText = findViewById(R.id.signUpText);
        signUpText.setOnClickListener(LogInView.this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(LogInView.this, SignUpView.class);
        startActivity(intent);
    }
}
