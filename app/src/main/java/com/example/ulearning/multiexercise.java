package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class multiexercise extends AppCompatActivity {
    private RadioGroup answerChoices;
    private Button submitButton;
    private int currentQuestion = 0;
    private String[] multiplicationQuestions = {
            "If you have 5 baskets, and each basket contains 4 apples, how many apples do you have in total?",
            "If you have 3 packs of pencils, and each pack contains 6 pencils, how many pencils do you have in total?",
            "If you have 2 trays, and each tray contains 8 cupcakes, how many cupcakes do you have in total?",
            "If you have 4 jars, and each jar contains 3 candies, how many candies do you have in total?",
            "If you have 6 boxes, and each box contains 2 toys, how many toys do you have in total?"
    };

    private String[][] multiplicationChoices = {
            {"20 apples", "10 apples", "5 apples", "8 apples"},
            {"12 pencils", "18 pencils", "9 pencils", "6 pencils"},
            {"16 cupcakes", "24 cupcakes", "8 cupcakes", "10 cupcakes"},
            {"7 candies", "12 candies", "6 candies", "8 candies"},
            {"10 toys", "8 toys", "12 toys", "14 toys"}
    };

    private int[] multiplicationAnswers = {0, 1, 1, 3, 0};

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexercise);

        answerChoices = findViewById(R.id.answer_choices);
        submitButton = findViewById(R.id.submit_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer();
            }
        });

        displayNextQuestion();
    }

    private void checkAnswer() {
        int selectedId = answerChoices.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        int userAnswerIndex = answerChoices.indexOfChild(selectedRadioButton);

        if (userAnswerIndex == -1) {
            // No answer selected, handle this case accordingly
            return;
        }

        if (userAnswerIndex == multiplicationAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion == multiplicationQuestions.length) {
            showResultPage();
        } else {
            displayNextQuestion();
        }
    }





    private void displayNextQuestion() {
        answerChoices.clearCheck();

        TextView questionTextView = findViewById(R.id.question_text);
        questionTextView.setText(multiplicationQuestions[currentQuestion]);

        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        choice1.setText(multiplicationChoices[currentQuestion][0]);
        choice2.setText(multiplicationChoices[currentQuestion][1]);
        choice3.setText(multiplicationChoices[currentQuestion][2]);
        choice4.setText(multiplicationChoices[currentQuestion][3]);
    }

    private void showResultPage() {
        Intent intent = new Intent(this, multiresult.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", multiplicationQuestions.length);
        startActivity(intent);
        finish();
    }
}
