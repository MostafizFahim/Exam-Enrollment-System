package com.example.exam_enrollment_system;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FormActivity extends AppCompatActivity {
    Button submit;
    EditText datePickerLayout, candidateName, fathername, motherName, address, contactNumber, email, pickGender;
    private FirebaseAuth mAuth;

    //FirebaseDatabase firebaseDatabase;
    DatabaseReference studentDbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        submit = findViewById(R.id.submit_form_btn);
        candidateName = findViewById(R.id.candidate_name);
        fathername = findViewById(R.id.father_name);
        motherName = findViewById(R.id.mother_name);
        datePickerLayout = findViewById(R.id.Date_Picker);
        pickGender = findViewById(R.id.gender_picker);
        address = findViewById(R.id.address);
        contactNumber = findViewById(R.id.contact_number);
        email = findViewById(R.id.email_address);

        studentDbRef = FirebaseDatabase.getInstance().getReference().child("Info");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                insertStudentData();
                passUserData();
            }
        });
    }
    private void insertStudentData() {
        //firebaseDatabase = FirebaseDatabase.getInstance();
        //studentDbRef = FirebaseDatabase.getInstance().getReference().child("Info");

        String CandidateName = candidateName.getText().toString();
        String Father = fathername.getText().toString();
        String Mother = motherName.getText().toString();
        String Date = datePickerLayout.getText().toString();
        String Gender = pickGender.getText().toString();
        String Address = address.getText().toString();
        String Contact = contactNumber.getText().toString();
        String Email = email.getText().toString();

        if(TextUtils.isEmpty(CandidateName) || TextUtils.isEmpty(Father) || TextUtils.isEmpty(Mother)|| TextUtils.isEmpty(Date)
                || TextUtils.isEmpty(Gender)|| TextUtils.isEmpty(Address)|| TextUtils.isEmpty(Contact)|| TextUtils.isEmpty(Email)){
            Toast.makeText(FormActivity.this, "Empty credentials!", Toast.LENGTH_SHORT).show();


        }
        else{
            UserHelperClass userhelperclass = new UserHelperClass(CandidateName, Father, Mother,Date,Gender,Address,Contact,Email);
            studentDbRef.push().setValue(userhelperclass);
            //studentDbRef.child(CandidateName).setValue(userhelperclass);

            Toast.makeText(FormActivity.this, "Member registered successfully", Toast.LENGTH_SHORT).show();

            candidateName.setText("");
            fathername.setText("");
            motherName.setText("");
            datePickerLayout.setText("");
            pickGender.setText("");
            address.setText("");
            contactNumber.setText("");
            email.setText("");
            startActivity(new Intent(FormActivity.this , Form2Activity.class));
            finish();

        }




    }

    public void passUserData(){
        String userName = candidateName.getText().toString().trim();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Info");
        Query checkUserDatabase = reference.orderByChild("Info").equalTo(userName);

        checkUserDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    String nameFromDB = snapshot.child(userName).child("name").getValue(String.class);
                    String fatherNameFromDB = snapshot.child(userName).child("fName").getValue(String.class);
                    String motherNameFromDB = snapshot.child(userName).child("mName").getValue(String.class);
                    String dateFromDB = snapshot.child(userName).child("date").getValue(String.class);
                    String genderFromDB = snapshot.child(userName).child("gender").getValue(String.class);
                    String addressFromDB = snapshot.child(userName).child("address").getValue(String.class);
                    String contactFromDB = snapshot.child(userName).child("contact").getValue(String.class);
                    String emailFromDB = snapshot.child(userName).child("email").getValue(String.class);

                    Intent intent = new Intent(FormActivity.this,Edit_ProfileActivity.class);
                    intent.putExtra("name",nameFromDB);
                    intent.putExtra("fName",fatherNameFromDB);
                    intent.putExtra("mName",motherNameFromDB);
                    intent.putExtra("date",dateFromDB);
                    intent.putExtra("gender",genderFromDB);
                    intent.putExtra("address",addressFromDB);
                    intent.putExtra("contact",contactFromDB);
                    intent.putExtra("email",emailFromDB);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}






