package com.example.exam_enrollment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    Button enroll,status,edit,logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);



        enroll = findViewById(R.id.enroll_for_exam);
        status=findViewById(R.id.view_application_status);
        edit=findViewById(R.id.edit_profile);
        logout = findViewById(R.id.logout);

        enroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DashboardActivity.this,EnrollActivity.class);
                startActivity(intent);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DashboardActivity.this,LoginActivity.class);
                startActivity(intent);

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DashboardActivity.this,Edit_ProfileActivity.class);
                startActivity(intent);

            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(DashboardActivity.this,Application_StatusActivity.class);
                startActivity(intent);

            }
        });




    }
}