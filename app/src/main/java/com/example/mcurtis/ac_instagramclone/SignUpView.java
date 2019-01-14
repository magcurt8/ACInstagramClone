package com.example.mcurtis.ac_instagramclone;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class SignUpView extends AppCompatActivity implements View.OnClickListener {

    private EditText emailInput, userNameInput, passwordInput;
    private Button signUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");
        emailInput = findViewById(R.id.emailInput);
        userNameInput = findViewById(R.id.userNameInput);
        passwordInput = findViewById(R.id.passwordInput);

        signUpButton = findViewById(R.id.signUpButton);
        signUpButton.setOnClickListener(this);

        if (ParseUser.getCurrentUser() != null) {

            ParseUser.getCurrentUser().logOut();
        }

    }

    @Override
    public void onClick(View view) {
        final ParseUser appUser = new ParseUser();
        appUser.setEmail(emailInput.getText().toString());
        appUser.setUsername(userNameInput.getText().toString());
        appUser.setPassword(passwordInput.getText().toString());

        appUser.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    FancyToast.makeText
                            (SignUpView.this, appUser.get("username") + " added",
                                    FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    navigateToHomeScreen();
                } else {
                    FancyToast.makeText
                            (SignUpView.this, e.getMessage(),
                                    FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                }
            }
        });
    }

    private void navigateToHomeScreen() {

        Intent intent = new Intent(SignUpView.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }


}
