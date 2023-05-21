package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class subquiz extends AppCompatActivity {
    private int currentQuestion = 0; // The current question number
    private int score = 0; // The score of the user

    private TextView questionTextView;
    private TextView questionBlankTextView;
    private EditText answerEditText;
    private Button submitButton;
    private Button finishButton;

    private Random random;
    private int[] numbers = new int[2];
    private int correctAnswer;
    private boolean isBlankQuestion; // Flag to indicate if it's a fill-in-the-blank question

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subquiz);

        questionTextView = findViewById(R.id.questionTextView);
        questionBlankTextView = findViewById(R.id.questionBlankTextView);
        answerEditText = findViewById(R.id.answerEditText);
        submitButton = findViewById(R.id.submitButton);
        finishButton = findViewById(R.id.finishButton);
        finishButton.setVisibility(View.GONE); // Hide the finish button initially

        random = new Random();

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = answerEditText.getText().toString().trim();
                if (!answer.isEmpty()) {
                    submitAnswer(answer);
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
        int randomQuestionType = random.nextInt(2); // Randomly select the question type

        if (randomQuestionType == 0) {
            // Fill-in-the-blank question
            isBlankQuestion = true;
            generateBlankQuestion();
        } else {
            // User-entered answer question
            isBlankQuestion = false;
            generateAnswerQuestion();
        }

        answerEditText.setText(""); // Clear the previous answer

        // Enable the submit button
        submitButton.setEnabled(true);

        // Show the finish button if it's the last question
        if (currentQuestion == 9) {
            finishButton.setVisibility(View.VISIBLE);
        }
    }

    // Function to generate a fill-in-the-blank question
    private void generateBlankQuestion() {
        numbers[0] = random.nextInt(101);
        numbers[1] = random.nextInt(101);

        correctAnswer = numbers[0] - numbers[1];
        questionTextView.setText(numbers[0] + " - __ = " + numbers[1]);

        // Show the question blank
        questionBlankTextView.setVisibility(View.VISIBLE);


        // Show the answer edit text and enable it
        answerEditText.setVisibility(View.VISIBLE);
        answerEditText.setEnabled(true);
    }

    // Function to generate a user-entered answer question
    private void generateAnswerQuestion() {
        numbers[0] = random.nextInt(101);
        numbers[1] = random.nextInt(101);

        correctAnswer = numbers[0] - numbers[1];
        questionTextView.setText(numbers[0] + " - " + numbers[1] + " =");

        // Hide the question blank
        questionBlankTextView.setVisibility(View.GONE);

        // Show the answer edit text and enable it
        answerEditText.setVisibility(View.VISIBLE);
        answerEditText.setEnabled(true);
    }

    // Function to handle the answer submission
    private void submitAnswer(String answer) {
        int userAnswer = Integer.parseInt(answer);
        if (userAnswer == correctAnswer) {
            // Correct answer
            score++;
        }

        // Move to the next question or finish the quiz
        currentQuestion++;
        if (currentQuestion < 10) {
            generateQuestion();
        } else {
            // Disable the answer edit text after the last question
            answerEditText.setEnabled(false);

            // Disable the submit button after the last question
            submitButton.setEnabled(false);
        }
    }

    // Function to finish the quiz
    private void finishQuiz() {
        Intent intent = new Intent(subquiz.this, subquizresult.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}
