package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class subtraction extends AppCompatActivity implements View.OnClickListener{

    ImageButton sublessonbtn, subquizbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);

        sublessonbtn = (ImageButton) findViewById(R.id.sublessonbtn);
        subquizbtn = (ImageButton) findViewById(R.id.subquizbtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sublessonbtn){
            Intent i = new Intent(subtraction.this,additionlesson.class);
            startActivity(i);
        }

        else if (view.getId() == R.id.subquizbtn){
            Intent i = new Intent(subtraction.this, subquiz.class);
            startActivity(i);
        }
    }
}