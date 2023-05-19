package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Introduction extends AppCompatActivity implements View.OnClickListener{
     Button introonebtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);
        introonebtn = (Button) findViewById(R.id.introonebtn);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.introonebtn){
            Intent i = new Intent(Introduction.this,introtwo.class);
            startActivity(i);
        }
    }
}