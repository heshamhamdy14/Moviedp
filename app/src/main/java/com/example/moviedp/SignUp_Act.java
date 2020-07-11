package com.example.moviedp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp_Act extends AppCompatActivity {
    EditText email , password;
    Button signup;
    TextView login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_);

        email=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        signup=findViewById(R.id.signup_btn);
        login=findViewById(R.id.login_act);
        auth=FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login_Act.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register_user();
            }
        });
    }

    public void register_user(){
        String Email=email.getText().toString();
        String Pass=password.getText().toString();
        if (Email.equals("") && Pass.equals("")) {
            Toast.makeText(SignUp_Act.this, "Invalid Email And Password!!", Toast.LENGTH_SHORT).show();
        }else
            auth.createUserWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(SignUp_Act.this, "Successfully Registration", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUp_Act.this, MoviesList_Act.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUp_Act.this, "Failed To Register: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
