package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Addition extends AppCompatActivity implements View.OnClickListener{

    ImageButton addlessonbtn, addquizbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition);

        addlessonbtn = (ImageButton) findViewById(R.id.addlessonbtn);
        addquizbtn = (ImageButton) findViewById(R.id.addquizbtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.addlessonbtn){
            Intent i = new Intent(Addition.this,additionlesson.class);
            startActivity(i);
        }
    }
}