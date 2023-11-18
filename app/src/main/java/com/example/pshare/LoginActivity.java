package com.example.pshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth; //firebase authentication
    private EditText lemail, lpassword; //login email and password
    private TextView srt; //signup redirect text
    private Button lbutton; //loginbutton

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        lemail = findViewById(R.id.login_email);
        lpassword = findViewById(R.id.login_password);
        lbutton = findViewById(R.id.LoginButton);
        srt = findViewById(R.id.SignUpRedirectText);

        lbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String existinguser = lemail.getText().toString().trim();
                String eupass = lpassword.getText().toString().trim();

                if (!existinguser.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(existinguser).matches()) {
                    if (!eupass.isEmpty()) {
                        auth.signInWithEmailAndPassword(existinguser, eupass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Attempt Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        lpassword.setError("Password cannot be empty");
                    }
                } else if (existinguser.isEmpty()) {
                    lemail.setError("Email Id cannot be empty");
                }else {
                    lemail.setError("Please Enter Valid Email");
                }
            }
        });
        srt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });
    }
}