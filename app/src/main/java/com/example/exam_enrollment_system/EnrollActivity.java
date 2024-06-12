package com.example.exam_enrollment_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class EnrollActivity extends AppCompatActivity {





    Button apply,back_btn;



    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enroll);

        apply = findViewById(R.id.apply_now);
        back_btn = findViewById(R.id.back);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EnrollActivity.this,FormActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(EnrollActivity.this,DashboardActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}