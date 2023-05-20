package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class multilessoncom extends AppCompatActivity implements View.OnClickListener{

    Button multicongbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multilessoncom);
        multicongbtn = (Button) findViewById(R.id.multicongbtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.multicongbtn){
            Intent i = new Intent(multilessoncom.this, multiexercise.class);
            startActivity(i);
        }
    }
}