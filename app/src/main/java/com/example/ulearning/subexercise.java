package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class subexercise extends AppCompatActivity {
    private RadioGroup answerChoices;
    private Button submitButton;
    private int currentQuestion = 0;

    private String[] subtractionQuestions = {
            "If you have 20 apples and you eat 5 apples, how many apples do you have left?",
            "If you have 7 apples and you give away 3 apples, how many apples do you have now?",
            "If you have 23 apples and you sell 15 apples, how many apples do you have?",
            "If you buy 10 apples at the store and then return 4 apples, how many apples do you have in total?",
            "If you have 12 apples and your friend has 7 apples and you give him 5 apples, how many apples does your friend have?"
    };

    private String[][] subtractionChoices = {
            {"15 apples", "10 apples", "5 apples", "0 apples"},
            {"4 apples", "7 apples", "3 apples", "10 apples"},
            {"8 apples", "15 apples", "23 apples", "5 apples"},
            {"6 apples", "10 apples", "14 apples", "20 apples"},
            {"2 apples", "7 apples", "0 apples", "5 apples"}
    };

    private int[] subtractionAnswers = {0, 1, 0, 2, 1};

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

        if (userAnswerIndex == subtractionAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion == subtractionQuestions.length) {
            showResultPage();
        } else {
            displayNextQuestion();
        }
    }





    private void displayNextQuestion() {
        answerChoices.clearCheck();

        TextView questionTextView = findViewById(R.id.question_text);
        questionTextView.setText(subtractionQuestions[currentQuestion]);

        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        choice1.setText(subtractionChoices[currentQuestion][0]);
        choice2.setText(subtractionChoices[currentQuestion][1]);
        choice3.setText(subtractionChoices[currentQuestion][2]);
        choice4.setText(subtractionChoices[currentQuestion][3]);
    }

    private void showResultPage() {
        Intent intent = new Intent(this, AddResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions",  subtractionQuestions.length);
        startActivity(intent);
        finish();
    }
}
