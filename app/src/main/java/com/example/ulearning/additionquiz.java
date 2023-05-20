package com.example.ulearning;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class additionquiz extends AppCompatActivity {
    private int currentQuestion = 0; // The current question number
    private int score = 0; // The score of the user

    private TextView questionTextView;
    private RadioGroup optionsRadioGroup;
    private Button submitButton;
    private Button finishButton;

    private Random random;
    private int[] numbers = new int[2];
    private int correctAnswer;
    private int correctOptionIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionquiz);

        questionTextView = findViewById(R.id.questionTextView);
        optionsRadioGroup = findViewById(R.id.optionsRadioGroup);
        submitButton = findViewById(R.id.submitButton);
        finishButton = findViewById(R.id.finishButton);
        finishButton.setVisibility(View.GONE); // Hide the finish button initially

        random = new Random();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedOptionId = optionsRadioGroup.getCheckedRadioButtonId();
                if (selectedOptionId != -1) {
                    RadioButton selectedOption = findViewById(selectedOptionId);
                    int selectedOptionIndex = optionsRadioGroup.indexOfChild(selectedOption);
                    submitAnswer(selectedOptionIndex);
                }
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishQuiz();
            }
        });

        startQuiz();
    }

    // Function to start the quiz
    private void startQuiz() {
        currentQuestion = 0;
        score = 0;
        generateQuestion();
    }

    // Function to generate a question
    private void generateQuestion() {
        numbers[0] = random.nextInt(101);
        numbers[1] = random.nextInt(101);

        correctAnswer = numbers[0] + numbers[1];
        questionTextView.setText(numbers[0] + " + " + numbers[1]);

        generateOptions();
    }

    // Function to generate answer options
    // Function to generate answer options
    private void generateOptions() {
        RadioButton option1 = findViewById(R.id.option1RadioButton);
        RadioButton option2 = findViewById(R.id.option2RadioButton);
        RadioButton option3 = findViewById(R.id.option3RadioButton);
        RadioButton option4 = findViewById(R.id.option4RadioButton);

        correctOptionIndex = random.nextInt(4); // Randomly assign the correct answer to an option

        switch (correctOptionIndex) {
            case 0:
                option1.setText(String.valueOf(correctAnswer));
                option2.setText(String.valueOf(random.nextInt(101)));
                option3.setText(String.valueOf(random.nextInt(101)));
                option4.setText(String.valueOf(random.nextInt(101)));
                break;
            case 1:
                option1.setText(String.valueOf(random.nextInt(101)));
                option2.setText(String.valueOf(correctAnswer));
                option3.setText(String.valueOf(random.nextInt(101)));
                option4.setText(String.valueOf(random.nextInt(101)));
                break;
            case 2:
                option1.setText(String.valueOf(random.nextInt(101)));
                option2.setText(String.valueOf(random.nextInt(101)));
                option3.setText(String.valueOf(correctAnswer));
                option4.setText(String.valueOf(random.nextInt(101)));
                break;
            case 3:
                option1.setText(String.valueOf(random.nextInt(101)));
                option2.setText(String.valueOf(random.nextInt(101)));
                option3.setText(String.valueOf(random.nextInt(101)));
                option4.setText(String.valueOf(correctAnswer));
                break;
        }

        // Clear the selected answer
        optionsRadioGroup.clearCheck();
    }


    // Function to handle the answer submission
    private void submitAnswer(int selectedOption) {
        if (selectedOption == correctOptionIndex) {
            // Correct answer
            score++;
        }

        // Move to the next question or finish the quiz
        currentQuestion++;
        if (currentQuestion < 10) {
            generateQuestion();
        } else {
            finishButton.setVisibility(View.VISIBLE); // Show the finish button
        }
    }

    // Function to finish the quiz
    private void finishQuiz() {
        Intent intent = new Intent(additionquiz.this, additionquizresult.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}
