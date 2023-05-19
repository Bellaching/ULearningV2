package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStarted extends AppCompatActivity implements View.OnClickListener{

    Button getstartedbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);
        getstartedbtn = (Button) findViewById(R.id.getstartedbtn);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.getstartedbtn){
            Intent i = new Intent(GetStarted.this,LogIn.class);
            startActivity(i);
        }
    }
}