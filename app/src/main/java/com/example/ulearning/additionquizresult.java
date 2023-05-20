package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class additionquizresult extends AppCompatActivity {
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionquizresult);

        TextView scoreTextView = findViewById(R.id.scoreTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button scoreButton = findViewById(R.id.scoreButton);

        // Retrieve the score from the intent
        score = getIntent().getIntExtra("score", 0);

        scoreTextView.setText("Score: " + score);

        // Set the result message based on the score
        if (score >= 7) {
            resultTextView.setText("Congratulations, you passed!");
        } else {
            resultTextView.setText("Sorry, you did not pass. Try again!");
        }

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the ScoreActivity and pass the score
                Intent intent = new Intent(additionquizresult.this, Scoretracking.class);
                intent.putExtra("Your score on addition quiz", score);
                startActivity(intent);
            }
        });
    }
}
