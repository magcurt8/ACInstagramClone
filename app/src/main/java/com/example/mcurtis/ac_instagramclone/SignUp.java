package com.example.mcurtis.ac_instagramclone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.SaveCallback;

public class SignUp extends AppCompatActivity implements View.OnClickListener{

    private Button saveButton;
    private EditText boxerInput, weightInput, punchSpeedInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        saveButton = findViewById(R.id.saveButton);
        saveButton.setOnClickListener(SignUp.this);

        boxerInput = findViewById(R.id.boxerInput);
        weightInput = findViewById(R.id.weightInput);
        punchSpeedInput = findViewById(R.id.punchSpeedInput);


    }

    @Override
    public void onClick(View v) {
        final ParseObject kickBoxer = new ParseObject("KickBoxer");
        kickBoxer.put("name", boxerInput.getText().toString());
        kickBoxer.put("weight_class", weightInput.getText().toString());
        kickBoxer.put("punch_speed", Integer.parseInt(punchSpeedInput.getText().toString()));

        kickBoxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e == null) {
                    Toast.makeText(SignUp.this, "Kickboxer " + kickBoxer.get("name") + " was saved successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(SignUp.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
