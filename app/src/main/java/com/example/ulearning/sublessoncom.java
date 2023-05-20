package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class sublessoncom extends AppCompatActivity implements View.OnClickListener{

    Button subcongbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sublessoncom);
        subcongbtn = (Button) findViewById(R.id.subcongbtn);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.subcongbtn){
            Intent i = new Intent(sublessoncom.this, subexercise.class);
            startActivity(i);
        }
    }
}