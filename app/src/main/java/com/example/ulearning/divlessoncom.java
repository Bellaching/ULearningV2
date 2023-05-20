package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class divlessoncom extends AppCompatActivity  implements View.OnClickListener{

    Button divcongbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divlessoncom);
        divcongbtn = (Button) findViewById(R.id.divcongbtn);
    }


    @Override
    public void onClick(View view) {


        if (view.getId() == R.id.divcongbtn){
            Intent i = new Intent(divlessoncom.this, divexercise.class);
            startActivity(i);
        }

    }
}