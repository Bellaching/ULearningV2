package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AddResultActivity extends AppCompatActivity {
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_result);

        resultText = findViewById(R.id.result_text);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 0);

        double percentage = (score / (double) totalQuestions) * 100;
        String resultMessage = "You answered " + score + " out of " + totalQuestions + " correctly.\n";
        resultMessage += "Percentage: " + String.format("%.2f", percentage) + "%";

        resultText.setText(resultMessage);
    }
}
