package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class multiresult extends AppCompatActivity implements View.OnClickListener{
    private TextView result_text;
    Button goback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiresult);

        result_text = findViewById(R.id.result_text);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);

        double percentage = (score / (double) totalQuestions) * 100;
        String resultMessage = "You answered " + score + " out of " + totalQuestions + " correctly.\n";
        resultMessage += "Percentage: " + String.format("%.2f", percentage) + "%";

        result_text.setText(resultMessage);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.goback){
            Intent i = new Intent(multiresult.this, multiplication.class);
            startActivity(i);
        }

    }
}
