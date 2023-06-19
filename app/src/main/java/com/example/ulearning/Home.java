package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Home extends AppCompatActivity implements View.OnClickListener{

    ImageButton addbtn,subbtn,scoretracker,divbtn,mulbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        addbtn = (ImageButton) findViewById(R.id.addbtn);
        subbtn = (ImageButton) findViewById(R.id.subbtn);
        divbtn = (ImageButton) findViewById(R.id.divbtn);
        mulbtn = (ImageButton) findViewById(R.id.mulbtn);
        scoretracker = (ImageButton) findViewById(R.id. scoretracker);



    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addbtn){
            Intent i = new Intent(Home.this, Addition.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.subbtn){
            Intent i = new Intent(Home.this, subtraction.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.divbtn){
            Intent i = new Intent(Home.this, divition.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.mulbtn){
            Intent i = new Intent(Home.this, multiplication.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.scoretracker){
            Intent i = new Intent(Home.this, Scoretracking.class);
            startActivity(i);
        }
    }




}