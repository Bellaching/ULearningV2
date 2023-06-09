package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Menu extends AppCompatActivity implements View.OnClickListener{

    Button startbtn, Logoutbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        startbtn = (Button) findViewById(R.id.startbtn);
        Logoutbtn = (Button) findViewById(R.id.Logoutbtn);

        Logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Menu.this, LogIn.class));
                Toast.makeText(Menu.this, "Your account has been logged out", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.startbtn){
            Intent i = new Intent(Menu.this, Home.class);
            startActivity(i);
        }
    }
    @Override
    public void onBackPressed() {
        finishAffinity(); // Close the app


    }

}