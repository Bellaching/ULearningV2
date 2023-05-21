package com.example.ulearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Scoretracking extends AppCompatActivity implements View.OnClickListener {
    private int additionScore;
    private int subtractionScore;

    private static final String SCORE_PREFS = "ScorePrefs";

    private TextView additionScoreTextView;
    private TextView subtractionScoreTextView;
    private Button addAgainButton;
    private Button subAgainButton;
    private Button divAgainButton;
    private Button mulAgainButton;
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoretracking);

        additionScoreTextView = findViewById(R.id.additionscore);
        subtractionScoreTextView = findViewById(R.id.subscore);
        addAgainButton = findViewById(R.id.addAgainButton);
        subAgainButton = findViewById(R.id.subAgainButton);
        divAgainButton = findViewById(R.id.divAgainButton);
        mulAgainButton = findViewById(R.id.mulAgainButton);
        homeButton = findViewById(R.id.homeback);

        addAgainButton.setOnClickListener(this);
        subAgainButton.setOnClickListener(this);
        divAgainButton.setOnClickListener(this);
        mulAgainButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);

        // Retrieve the scores for each quiz from SharedPreferences
        additionScore = getScoreFromSharedPreferences("addition_score");
        subtractionScore = getScoreFromSharedPreferences("subtraction_score");

        // Display the scores for each quiz
        additionScoreTextView.setText("Addition Quiz: " + additionScore);
        subtractionScoreTextView.setText("Subtraction Quiz: " + subtractionScore);

        // Check if the user passed or failed each quiz and update the UI accordingly
        updateQuizResults();
    }

    private int getScoreFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(SCORE_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    private void updateQuizResults() {
        if (additionScore > 7) {
            additionScoreTextView.append("\nCongratulations, you passed!");
            addAgainButton.setVisibility(View.GONE);
        } else {
            additionScoreTextView.append("\nI'm sorry, but you failed");
            addAgainButton.setVisibility(View.VISIBLE);
        }

        if (subtractionScore > 7) {
            subtractionScoreTextView.append("\nCongratulations, you passed!");
            subAgainButton.setVisibility(View.GONE);
        } else {
            subtractionScoreTextView.append("\nI'm sorry, but you failed");
            subAgainButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.addAgainButton) {
            // Restart the addition quiz
            Intent intent = new Intent(Scoretracking.this, additionquiz.class);
            startActivity(intent);
        } else if (view.getId() == R.id.subAgainButton) {
            // Restart the subtraction quiz
            Intent intent = new Intent(Scoretracking.this, subquiz.class);
            startActivity(intent);
        } else if (view.getId() == R.id.divAgainButton) {
            // Restart the division quiz
            Intent intent = new Intent(Scoretracking.this, subquiz.class);
            startActivity(intent);
        } else if (view.getId() == R.id.mulAgainButton) {
            // Restart the multiplication quiz
            Intent intent = new Intent(Scoretracking.this, subquiz.class);
            startActivity(intent);
        } else if (view.getId() == R.id.homeback) {
            // Go back to the home screen
            Intent intent = new Intent(Scoretracking.this, Home.class);
            startActivity(intent);
        }
    }
}
