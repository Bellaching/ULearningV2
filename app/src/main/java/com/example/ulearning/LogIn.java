package com.example.ulearning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import org.mindrot.jbcrypt.BCrypt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.mindrot.jbcrypt.BCrypt;

public class LogIn extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://ulearning-ddf76-default-rtdb.firebaseio.com/");
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public static String usernametxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final EditText username = findViewById(R.id.username);
        final EditText password = findViewById(R.id.password);
        final Button loginbtn = findViewById(R.id.loginbtn);
        final TextView signuptxt = findViewById(R.id.signuptxt);
        final CheckBox rememberMeCheckbox = findViewById(R.id.rememberMeCheckbox);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        boolean rememberMe = sharedPreferences.getBoolean("rememberMe", false);
        rememberMeCheckbox.setChecked(rememberMe);

        if (rememberMe) {
            String savedUsername = sharedPreferences.getString("username", "");
            String savedPassword = sharedPreferences.getString("password", "");
            username.setText(savedUsername);
            password.setText(savedPassword);
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usernametxt = username.getText().toString();
                final String passwordtxt = password.getText().toString();

                if (usernametxt.isEmpty() || passwordtxt.isEmpty()) {
                    Toast.makeText(LogIn.this, "Please enter your username or password", Toast.LENGTH_SHORT).show();
                } else {
                    databaseReference.child("user").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.hasChild(usernametxt)) {
                                final String hashedPassword = snapshot.child(usernametxt).child("password").getValue(String.class);

                                // Compare the entered password with the stored hashed password using BCrypt
                                if (BCrypt.checkpw(passwordtxt, hashedPassword)) {
                                    Toast.makeText(LogIn.this, "Successfully Logged In", Toast.LENGTH_SHORT).show();

                                    if (rememberMeCheckbox.isChecked()) {
                                        editor.putBoolean("rememberMe", true);
                                        editor.putString("username", usernametxt);
                                        editor.putString("password", passwordtxt);
                                        editor.apply();
                                    } else {
                                        editor.clear();
                                        editor.apply();
                                    }

                                    startActivity(new Intent(LogIn.this, Home.class));
                                    finish();
                                } else {
                                    Toast.makeText(LogIn.this, "Wrong password. Try again", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(LogIn.this, "Wrong username", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });


        signuptxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LogIn.this, signup.class));
            }
        });
    }
}
