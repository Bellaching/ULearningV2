package com.example.ulearning;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Scoretracking extends AppCompatActivity {
    private int currentScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoretracking);

        TextView scoreTextView = findViewById(R.id.scoreTextView);

        // Retrieve the current score from the intent
        currentScore = getIntent().getIntExtra("Your score on addition quiz", 0);
        if(currentScore > 7){
            scoreTextView.setText("Addition Quiz: " + currentScore +"\nCongratulation you passed!");

        } else {
            scoreTextView.setText("Addition Quiz: " + currentScore +"\n Im sorry but you failed");
        }


    }
}
