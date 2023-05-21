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
    private int divisionScore;
    private int multiplicationScore;

    private static final String SCORE_PREFS = "ScorePrefs";

    private TextView additionScoreTextView;
    private TextView subtractionScoreTextView;
    private TextView divScoreTextView;
    private TextView mulscoreTextView;
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
        divScoreTextView = findViewById(R.id.divscore);
        mulscoreTextView = findViewById(R.id.mulscore);
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

        additionScore = getScoreFromSharedPreferences("addition_score");
        subtractionScore = getScoreFromSharedPreferences("subtraction_score");
        divisionScore = getScoreFromSharedPreferences("division_score");
        multiplicationScore = getScoreFromSharedPreferences("multiplication_score");

        additionScoreTextView.setText("Addition Quiz: " + additionScore);
        subtractionScoreTextView.setText("Subtraction Quiz: " + subtractionScore);
        divScoreTextView.setText("Division Quiz: " + divisionScore);
        mulscoreTextView.setText("Division Quiz: " + multiplicationScore);

        updateQuizResults();
    }

    private int getScoreFromSharedPreferences(String key) {
        SharedPreferences sharedPreferences = getSharedPreferences(SCORE_PREFS, MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }

    private void updateQuizResults() {
        if (additionScore == 0) {
            // Quiz not taken yet
            additionScoreTextView.append("\nYou have not taken this quiz yet.");
            addAgainButton.setVisibility(View.GONE);}
        else if (additionScore > 7) {
            additionScoreTextView.append("\nCongratulations, you passed!");
            addAgainButton.setVisibility(View.GONE);
        } else {
            additionScoreTextView.append("\nI'm sorry, but you failed");
            addAgainButton.setVisibility(View.VISIBLE);
        }
        if (additionScore == 0) {
            // Quiz not taken yet
            subtractionScoreTextView.append("\nYou have not taken this quiz yet.");
            subAgainButton.setVisibility(View.GONE);}

        else if (subtractionScore > 7) {
            subtractionScoreTextView.append("\nCongratulations, you passed!");
            subAgainButton.setVisibility(View.GONE);
        } else {
            subtractionScoreTextView.append("\nI'm sorry, but you failed");
            subAgainButton.setVisibility(View.VISIBLE);
        }
        if (additionScore == 0) {
            // Quiz not taken yet
            subtractionScoreTextView.append("\nYou have not taken this quiz yet.");
            subAgainButton.setVisibility(View.GONE);}

        else if (divisionScore > 7) {
            divScoreTextView.append("\nCongratulations, you passed!");
            divAgainButton.setVisibility(View.GONE);
        } else {
            divScoreTextView.append("\nI'm sorry, but you failed");
            divAgainButton.setVisibility(View.VISIBLE);
        }
        if (additionScore == 0) {
            // Quiz not taken yet
            subtractionScoreTextView.append("\nYou have not taken this quiz yet.");
            subAgainButton.setVisibility(View.GONE);}

        else if (multiplicationScore > 7) {
            mulscoreTextView.append("\nCongratulations, you passed!");
            mulAgainButton.setVisibility(View.GONE);
        } else {
            mulscoreTextView.append("\nI'm sorry, but you failed");
            mulAgainButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.addAgainButton:
                intent = new Intent(Scoretracking.this, additionquiz.class);
                startActivity(intent);


                break;
            case R.id.subAgainButton:
                intent = new Intent(Scoretracking.this, subquiz.class);
                startActivity(intent);
                break;
            case R.id.divAgainButton:
                intent = new Intent(Scoretracking.this, divquiz.class);
                startActivity(intent);
                break;
            case R.id.mulAgainButton:
                intent = new Intent(Scoretracking.this, mulquiz.class);
                startActivity(intent);
                break;
            case R.id.homeback:
                intent = new Intent(Scoretracking.this, Home.class);
                startActivity(intent);
                break;
        }
    }
}
