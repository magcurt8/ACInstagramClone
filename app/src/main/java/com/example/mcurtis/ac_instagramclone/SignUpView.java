package com.example.mcurtis.ac_instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        passwordInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {

                if (keyCode == KeyEvent.KEYCODE_ENTER &&
                        event.getAction() == KeyEvent.ACTION_DOWN) {
                    onClick(signUpButton);
                }
                return false;
            }
        });

        if (ParseUser.getCurrentUser() != null) {
            navigateToHomeScreen();
        }
    }

    @Override
    public void onClick(View view) {

        if (emailInput.getText().toString().equals("") ||
                userNameInput.getText().toString().equals("") ||
                passwordInput.getText().toString().equals("")) {

            FancyToast.makeText(SignUpView.this,
                    "Email, Username, Password is required!",
                    Toast.LENGTH_SHORT, FancyToast.INFO,
                    true).show();

        } else {
            final ParseUser appUser = new ParseUser();
            appUser.setEmail(emailInput.getText().toString());
            appUser.setUsername(userNameInput.getText().toString());
            appUser.setPassword(passwordInput.getText().toString());

            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Signing up " + userNameInput.getText().toString());
            progressDialog.show();

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
                    progressDialog.dismiss();
                }
            });
        }
    }

    public void rootLayoutTapped(View view) {
        try {
            InputMethodManager inputMethodManager =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void navigateToHomeScreen() {

        Intent intent = new Intent(SignUpView.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
