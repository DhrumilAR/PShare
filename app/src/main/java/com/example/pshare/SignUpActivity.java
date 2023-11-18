package com.example.pshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth auth; //firebase authorization
    private EditText semail, spassword; //entering email and password values
    private Button sButton; //signupbutton
    private TextView lrt; //loginredirecttext


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        auth = FirebaseAuth.getInstance();
        semail = findViewById(R.id.signup_email);
        spassword = findViewById(R.id.signup_password);
        sButton = findViewById(R.id.SignUpButton);

        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = semail.getText().toString().trim();
                String pass = spassword.getText().toString().trim();

                if(user.isEmpty()){
                    semail.setError("Email cannot be empty");
                }
                else if (pass.isEmpty()) {
                    spassword.setError("Please enter the Password");
                }
                else {
                    auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(SignUpActivity.this, "Your SignUp was successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                            } else {
                                Toast.makeText(SignUpActivity.this,"Signup Failed"+task.getException().getMessage() , Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        lrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}