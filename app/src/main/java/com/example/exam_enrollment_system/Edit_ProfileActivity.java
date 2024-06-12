package com.example.exam_enrollment_system;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_ProfileActivity extends AppCompatActivity {
    Button submit;

    EditText editDatePickerLayout, editCandidateName, editFathername, editMotherName, editAddress, editContactNumber, editEmail, editPickGender;
    String datePickerLayout, candidateName, fathername, motherName, address, contactNumber, email, pickGender;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        reference = FirebaseDatabase.getInstance().getReference("Info");

        submit = findViewById(R.id.submit_form_btn);
        editCandidateName = findViewById(R.id.candidate_name);
        editFathername = findViewById(R.id.father_name);
        editMotherName = findViewById(R.id.mother_name);
        editDatePickerLayout = findViewById(R.id.Date_Picker);
        editPickGender = findViewById(R.id.gender_picker);
        editAddress = findViewById(R.id.address);
        editContactNumber = findViewById(R.id.contact_number);
        editEmail = findViewById(R.id.email_address);

        showData();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNameChanged()|| isFNameChanged()||isMNameChanged()||isDateChanged()||isGenderChanged()
                || isAddressChanged()||isContactChanged()||isEmailChanged()){
                    Toast.makeText(Edit_ProfileActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Edit_ProfileActivity.this, "No changes", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }

    public boolean isNameChanged(){
        if(!candidateName.equals(editCandidateName.getText().toString())){
            reference.child(candidateName).child("name").setValue(editCandidateName.getText().toString());
            candidateName = editCandidateName.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isFNameChanged(){
        if(!fathername.equals(editFathername.getText().toString())){
            reference.child(fathername).child("fName").setValue(editFathername.getText().toString());
            fathername = editFathername.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isMNameChanged(){
        if(!motherName.equals(editMotherName.getText().toString())){
            reference.child(motherName).child("mName").setValue(editMotherName.getText().toString());
            motherName = editMotherName.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isDateChanged(){
        if(!datePickerLayout.equals(editDatePickerLayout.getText().toString())){
            reference.child(datePickerLayout).child("date").setValue(editDatePickerLayout.getText().toString());
            datePickerLayout = editDatePickerLayout.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isGenderChanged(){
        if(!pickGender.equals(editPickGender.getText().toString())){
            reference.child(pickGender).child("gender").setValue(editPickGender.getText().toString());
            pickGender = editPickGender.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isAddressChanged(){
        if(!address.equals(editAddress.getText().toString())){
            reference.child(address).child("address").setValue(editAddress.getText().toString());
            address = editAddress.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isContactChanged(){
        if(!contactNumber.equals(editContactNumber.getText().toString())){
            reference.child(contactNumber).child("contact").setValue(editContactNumber.getText().toString());
            contactNumber = editContactNumber.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public boolean isEmailChanged(){
        if(!email.equals(editEmail.getText().toString())){
            reference.child(email).child("email").setValue(editEmail.getText().toString());
            email = editEmail.getText().toString();
            return true;
        }else{
            return false;
        }
    }

    public void showData(){
        Intent intent  = getIntent();
        candidateName = intent.getStringExtra("name");
        fathername = intent.getStringExtra("fName");
        motherName = intent.getStringExtra("mName");
        datePickerLayout = intent.getStringExtra("date");
        pickGender = intent.getStringExtra("gender");
        address = intent.getStringExtra("address");
        contactNumber = intent.getStringExtra("contact");
        email = intent.getStringExtra("email");

        editCandidateName.setText(candidateName);
        editFathername.setText(fathername);
        editMotherName.setText(motherName);
        editDatePickerLayout.setText(datePickerLayout);
        editPickGender.setText(pickGender);
        editAddress.setText(address);
        editContactNumber.setText(contactNumber);
        editEmail.setText(email);


    }

}