package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class divition extends AppCompatActivity implements View.OnClickListener{

    ImageButton divlessonbtn, divquizbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divition);

        divlessonbtn = (ImageButton) findViewById(R.id.divlessonbtn);
        divquizbtn= (ImageButton) findViewById(R.id.divquizbtn);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.divlessonbtn)
        {
            Intent i = new Intent(divition.this,divlesson.class);
            startActivity(i);
        }
        else if (view.getId() == R.id.divquizbtn){
            Intent i = new Intent(divition.this,divquiz.class);
            startActivity(i);
        }



    }
}