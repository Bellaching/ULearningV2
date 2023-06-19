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

public class divquiz extends AppCompatActivity {
    private int currentQuestion = 0;
    private int score = 0;
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
        finishButton.setVisibility(View.GONE);

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

    private void startQuiz() {
        currentQuestion = 0;
        score = 0;
        generateQuestion();
    }

    private void generateQuestion() {
        numbers[0] = (random.nextInt(5) + 1) * 2; // Generate a random even number between 2 and 10
        numbers[1] = (random.nextInt(4) + 1) * 2; // Generate a random even number between 2 and 8
        correctAnswer = numbers[0] / numbers[1];
        questionTextView.setText("What is " + numbers[0] + " ÷ " + numbers[1]);
        generateOptions();
    }

    private void generateOptions() {
        RadioButton option1 = findViewById(R.id.option1RadioButton);
        RadioButton option2 = findViewById(R.id.option2RadioButton);
        RadioButton option3 = findViewById(R.id.option3RadioButton);
        RadioButton option4 = findViewById(R.id.option4RadioButton);
        correctOptionIndex = random.nextInt(4);

        option1.setText(String.valueOf(correctAnswer));

        for (int i = 1; i <= 4; i++) {
            RadioButton option = getOptionRadioButton(i);
            int randomOption = (random.nextInt(4) + 1) * 2; // Generate a random even number between 2 and 8
            option.setText(String.valueOf(randomOption));
        }

        RadioButton correctOption = getOptionRadioButton(correctOptionIndex + 1);
        correctOption.setText(String.valueOf(correctAnswer));
        optionsRadioGroup.clearCheck();
    }

    private RadioButton getOptionRadioButton(int index) {
        int radioButtonId = getResources().getIdentifier("option" + index + "RadioButton", "id", getPackageName());
        return findViewById(radioButtonId);
    }

    private void submitAnswer(int selectedOption) {
        if (selectedOption == correctOptionIndex) {
            score++;
        }
        currentQuestion++;
        if (currentQuestion < 10) {
            generateQuestion();
        } else {
            finishButton.setVisibility(View.VISIBLE); // Show the finish button
            submitButton.setVisibility(View.GONE);
        }
    }

    private void finishQuiz() {
        Intent intent = new Intent(divquiz.this, divquizresult.class);
        intent.putExtra("score", score);
        startActivity(intent);
    }
}
