package com.example.exam_enrollment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Admin_loginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    String emailPattern = "[a-zA-Z0-9]+@[a-z]+\\.+[c&o&m]+";

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();

        email = findViewById(R.id.admin_mail);
        password = findViewById(R.id.admin_pass);
        login = findViewById(R.id.admin_btn);


        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perforLogin();
            }
        });
    }

    private void perforLogin() {
        String email1 = email.getText().toString();
        String password1 = password.getText().toString();


        if (!email1.matches(emailPattern)) {
            email.setError("ENTER COREECT EMAIL");
        } else if (password1.isEmpty() || password1.length() < 6) {
            password.setError("Enter proper password");
        } else {
            progressDialog.setMessage("Please wait while Login");
            progressDialog.setTitle("Login");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email1, password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        progressDialog.dismiss();
                        //sendUserToDashNextActivity();
                        Toast.makeText(Admin_loginActivity.this, "Login Successfull", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Admin_loginActivity.this, Admin_VerifyActivity.class));
                        finish();
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(Admin_loginActivity.this, "Invalid Field", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}