package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class divition extends AppCompatActivity implements View.OnClickListener{

    ImageButton sublessonbtn, subquizbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divition);

        sublessonbtn = (ImageButton) findViewById(R.id.sublessonbtn);
        subquizbtn= (ImageButton) findViewById(R.id.subquizbtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.sublessonbtn){
            Intent i = new Intent(divition.this,divlesson.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.subquizbtn){
            Intent i = new Intent(divition.this,additionlesson.class);
            startActivity(i);
        }



    }
}