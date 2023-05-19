package com.example.ulearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdditionLessonCom extends AppCompatActivity implements View.OnClickListener{

    Button addcongbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addition_lesson_com);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addcongbtn){
            Intent i = new Intent(AdditionLessonCom.this, addexercise.class);
            startActivity(i);
        }
    }
}