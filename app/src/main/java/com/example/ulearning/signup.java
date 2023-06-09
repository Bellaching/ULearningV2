package com.example.ulearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import org.mindrot.jbcrypt.BCrypt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class signup extends AppCompatActivity {
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ulearning-ddf76-default-rtdb.firebaseio.com/");
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseRef;

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

        firebaseAuth = FirebaseAuth.getInstance();

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

                        // Encrypt the password using bcrypt
                        String hashedPassword = BCrypt.hashpw(passwordtxt, BCrypt.gensalt());

                        databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.hasChild(usernametxt)) {
                                    Toast.makeText(signup.this, "Username is already taken", Toast.LENGTH_SHORT).show();
                                } else {
                                    databaseReference.child("user").child(usernametxt).child("name").setValue(nametxt);
                                    databaseReference.child("user").child(usernametxt).child("age").setValue(agetxt);
                                    databaseReference.child("user").child(usernametxt).child("email").setValue(emailtxt);
                                    databaseReference.child("user").child(usernametxt).child("password").setValue(hashedPassword);

                                    databaseRef = FirebaseDatabase.getInstance().getReference();
                                    databaseRef.child("scores").child(usernametxt).child("score").child("addition_score").setValue(-0);
                                    databaseRef.child("scores").child(usernametxt).child("score").child("division_score").setValue(-0);
                                    databaseRef.child("scores").child(usernametxt).child("score").child("multiplication_score").setValue(-0);
                                    databaseRef.child("scores").child(usernametxt).child("score").child("subtraction_score").setValue(-0);

                                    Toast.makeText(signup.this, "User registered successfully.", Toast.LENGTH_SHORT).show();


                                    Intent intent = new Intent(signup.this, LogIn.class);
                                    //intent.putExtra("username", usernametxt);
                                    startActivity(intent);

                                    finish();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle database error
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

    private String getCurrentUsername() {
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            return currentUser.getDisplayName();
        }
        return null;
    }
}
