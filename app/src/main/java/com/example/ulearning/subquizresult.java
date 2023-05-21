package com.example.ulearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class subquizresult extends AppCompatActivity {
    private int score;
    private static final String SCORE_PREFS = "ScorePrefs";
    private static final String KEY_SCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subquizresult);

        TextView subscoreTextView = findViewById(R.id.subscoreTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button scoreButton = findViewById(R.id.scoreButton);

        // Retrieve the score from the intent
        score = getIntent().getIntExtra("score", 0);

        // Save the score in SharedPreferences
        saveScore(score);

        subscoreTextView.setText("Score: " + score);

        // Set the result message based on the score
        if (score >= 7) {
            resultTextView.setText("Congratulations, \nyou passed!");
        } else {
            resultTextView.setText("Sorry, you did not pass. \nTry again!");
        }

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Scoretracking activity
                startActivity(new Intent(subquizresult.this, Scoretracking.class));
            }
        });
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences(SCORE_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("subtraction_score", score);
        editor.apply();
    }


}
