package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class subtractionlesson extends AppCompatActivity {

    private TextView explanationTextView;
    private Button nextButton, subfinishButton;
    private String[] explanations;
    private int currentPage = 0;
    private int itemsPerPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtractionlesson);

        explanationTextView = findViewById(R.id.explanationTextView);
        nextButton = findViewById(R.id.nextButton);
        subfinishButton = findViewById(R.id.subfinishButton);
        explanations = getResources().getStringArray(R.array.subtraction_explanations);
        subfinishButton.setVisibility(View.GONE);

        displayExplanation(currentPage);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextPage();
            }
        });

        subfinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(subtractionlesson.this, sublessoncom.class);
                startActivity(intent);
            }
        });
    }

    private void displayExplanation(int pageIndex) {
        int startIndex = pageIndex * itemsPerPage;
        int endIndex = Math.min(startIndex + itemsPerPage, explanations.length);

        StringBuilder explanationBuilder = new StringBuilder();
        for (int i = startIndex; i < endIndex; i++) {
            explanationBuilder.append(explanations[i]).append("\n");
        }

        explanationTextView.setText(explanationBuilder.toString());
    }

    private void nextPage() {
        if (currentPage < getLastPageIndex()) {
            currentPage++;
            displayExplanation(currentPage);
        } else if (currentPage == getLastPageIndex()) {
            subfinishButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.GONE);
        }
    }

    private int getLastPageIndex() {
        return (explanations.length - 1) / itemsPerPage;
    }
}
