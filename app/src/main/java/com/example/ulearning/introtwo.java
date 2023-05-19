package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class introtwo extends AppCompatActivity implements View.OnClickListener{

    Button introtwobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introtwo);
        introtwobtn = (Button) findViewById(R.id.introtwobtn);


    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.introtwobtn){
            Intent i = new Intent(introtwo.this,GetStarted.class);
            startActivity(i);
        }
    }
}