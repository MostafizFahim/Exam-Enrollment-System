package com.example.exam_enrollment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class ForgetPasswordActivity extends AppCompatActivity {
    EditText txtEmail;
    Button forgetBtn,back_btn;
    FirebaseAuth mAuth;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.emailField);
        forgetBtn = findViewById(R.id.fButton);
        back_btn = findViewById(R.id.back);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(ForgetPasswordActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });


    }
    private void validateData(){
    email = txtEmail.getText().toString();
    if(email.isEmpty())
    {
        txtEmail.setError("Required");
    }
    else {
        forgetPass();
    }
    }


    private void forgetPass(){

        mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgetPasswordActivity.this, "Check your Email!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(ForgetPasswordActivity.this,LoginActivity.class));
                    finish();
                }else {
                    Toast.makeText(ForgetPasswordActivity.this, "Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
