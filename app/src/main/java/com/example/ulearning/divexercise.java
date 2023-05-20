package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class divexercise extends AppCompatActivity {
    private RadioGroup answerChoices;
    private Button submitButton;
    private int currentQuestion = 0;
    private String[] divisionQuestions = {
            "If you have 15 apples and you share them equally among 3 friends, how many apples does each friend get?",
            "If you have 6 apples and you divide them equally into 2 baskets, how many apples are there in each basket?",
            "If you have 24 cookies and you want to distribute them evenly among 8 plates, how many cookies will be on each plate?",
            "If you have 10 candies and you want to divide them equally among 2 children, how many candies will each child get?",
            "If you have 18 pencils and you want to distribute them equally among 6 students, how many pencils will each student receive?"
    };

    private String[][] divisionChoices = {
            {"5 apples", "3 apples", "4 apples", "7 apples"},
            {"3 apples", "2 apples", "6 apples", "4 apples"},
            {"4 cookies", "3 cookies", "2 cookies", "6 cookies"},
            {"5 candies", "8 candies", "2 candies", "10 candies"},
            {"3 pencils", "6 pencils", "4 pencils", "2 pencils"}
    };

    private int[] divisionAnswers = {1, 0, 1, 2, 1};

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divexercise);

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

        if (userAnswerIndex == divisionAnswers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion == divisionQuestions .length) {
            showResultPage();
        } else {
            displayNextQuestion();
        }
    }





    private void displayNextQuestion() {
        answerChoices.clearCheck();

        TextView questionTextView = findViewById(R.id.question_text);
        questionTextView.setText(divisionQuestions [currentQuestion]);

        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        choice1.setText( divisionChoices [currentQuestion][0]);
        choice2.setText( divisionChoices [currentQuestion][1]);
        choice3.setText( divisionChoices [currentQuestion][2]);
        choice4.setText( divisionChoices [currentQuestion][3]);
    }

    private void showResultPage() {
        Intent intent = new Intent(this, divresult.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", divisionQuestions .length);
        startActivity(intent);
        finish();
    }
}
