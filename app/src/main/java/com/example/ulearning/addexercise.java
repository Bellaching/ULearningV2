package com.example.ulearning;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class addexercise extends AppCompatActivity {
    private RadioGroup answerChoices;
    private Button submitButton;
    private int currentQuestion = 0;
    private String[] questions = {
            "If your friend gives you 5 apples and your mom gives you 10 apples, how many apples do you have?",
            "If you have 3 apples and your friend gives you 1 apple, how many apples do you have now?",
            "If you have 8 apples and you buy 15 apples, how many apples do you have?",
            "If you buy 6 apples at the store and then buy 4 more, how many apples do you have in total?",
            "If you have 12 apples and your friend has 14 apples and you give him 5 apples, how many apples does your friend have?"
    };
    private String[][] choices = {
            {"5 apples", "10 apples", "15 apples", "20 apples"},
            {"4 apples", "1 apple", "2 apples", "3 apples"},
            {"23 apples", "10 apples", "6 apples", "20 apples"},
            {"6 apples", "8 apples", "10 apples", "12 apples"},
            {"22 apples", "23 apples", "19 apples", "26 apples"}
    };

    private int[] answers = {2, 1, 2, 0, 2};

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

        if (userAnswerIndex == answers[currentQuestion]) {
            score++;
        }

        currentQuestion++;

        if (currentQuestion == questions.length) {
            showResultPage();
        } else {
            displayNextQuestion();
        }
    }





    private void displayNextQuestion() {
        answerChoices.clearCheck();

        TextView questionTextView = findViewById(R.id.question_text);
        questionTextView.setText(questions[currentQuestion]);

        RadioButton choice1 = findViewById(R.id.choice1);
        RadioButton choice2 = findViewById(R.id.choice2);
        RadioButton choice3 = findViewById(R.id.choice3);
        RadioButton choice4 = findViewById(R.id.choice4);

        choice1.setText(choices[currentQuestion][0]);
        choice2.setText(choices[currentQuestion][1]);
        choice3.setText(choices[currentQuestion][2]);
        choice4.setText(choices[currentQuestion][3]);
    }

    private void showResultPage() {
        Intent intent = new Intent(this, AddResultActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("totalQuestions", questions.length);
        startActivity(intent);
        finish();
    }
}
