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
import com.google.firebase.auth.FirebaseUser;

public class Login_Act extends AppCompatActivity {
    EditText email , password;
    Button login;
    TextView signup;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=findViewById(R.id.login_email);
        password=findViewById(R.id.login_password);
        login=findViewById(R.id.hopayalla);
        signup=findViewById(R.id.signup_act);
        auth=FirebaseAuth.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               loginuser();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SignUp_Act.class));
            }
        });
    }
    public void loginuser(){
        String Email=email.getText().toString();
        String Pass=password.getText().toString();
        if (Email.equals("") && Pass.equals("")) {
            Toast.makeText(Login_Act.this, "Invalid Email And Password!!", Toast.LENGTH_SHORT).show();
        }else
        auth.signInWithEmailAndPassword(Email,Pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(Login_Act.this, "Login successful", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(),MoviesList_Act.class);
                startActivity(intent);
                finish();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Login_Act.this, "Failed To Login: "+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser=auth.getCurrentUser();
        if (firebaseUser != null){
            Intent intent=new Intent(Login_Act.this , MoviesList_Act.class);
            startActivity(intent);
            finish();
        }
    }
}
