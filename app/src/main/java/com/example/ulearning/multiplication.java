package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class multiplication extends AppCompatActivity implements View.OnClickListener{

    ImageButton multilessonbtn,mulquizbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplication);
        multilessonbtn = (ImageButton) findViewById(R.id.multilessonbtn);
        mulquizbtn = (ImageButton) findViewById(R.id.mulquizbtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.multilessonbtn){
            Intent i = new Intent(multiplication.this,multilesson.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.mulquizbtn){
            Intent i = new Intent(multiplication.this,mulquiz.class);
            startActivity(i);
        }

    }
}