package com.example.ulearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    DatabaseReference  databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ulearning-ddf76-default-rtdb.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        final EditText name = findViewById(R.id.name);
        final EditText age = findViewById(R.id.age);
        final EditText email = findViewById(R.id.email);
        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final EditText conpassword = findViewById(R.id.conpassword);

        final Button registerbtn = findViewById(R.id.registerbtn);
        final TextView alreadyhvaccount = findViewById(R.id.alreadyhvaccount);

        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String nametxt = name.getText().toString();
                final String agetxt = age.getText().toString();
                final String emailtxt = email.getText().toString();
                final String usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();
                final String conpasswordtxt = conpassword.getText().toString();

                if (nametxt.isEmpty() || agetxt.isEmpty() || emailtxt.isEmpty() || usernametxt.isEmpty() || passwordtxt.isEmpty() || conpasswordtxt.isEmpty()) {
                    Toast.makeText(signup.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else if (!passwordtxt.equals(conpasswordtxt)) {
                    Toast.makeText(signup.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        if (passwordtxt.length() != 8) {
                            throw new IllegalArgumentException("Password must be 8 characters long");
                        }

                        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(usernametxt)) {
                                    Toast.makeText(signup.this, "Email is already registered", Toast.LENGTH_SHORT).show();
                                } else {
                                    databaseReference.child("user").child(usernametxt).child("name").setValue(nametxt);
                                    databaseReference.child("user").child(usernametxt).child("age").setValue(agetxt);
                                    databaseReference.child("user").child(usernametxt).child("email").setValue(emailtxt);
                                    databaseReference.child("user").child(usernametxt).child("password").setValue(passwordtxt);

                                    Toast.makeText(signup.this, "User registered successfully.", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    } catch (IllegalArgumentException e) {
                        Toast.makeText(signup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        alreadyhvaccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
