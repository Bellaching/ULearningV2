package com.example.ulearning;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BasicCalculator extends AppCompatActivity implements View.OnClickListener {

    private EditText operand1EditText;
    private EditText operand2EditText;
    private Button addButton;
    private Button subtractButton;
    private Button multiplyButton;
    private Button divideButton;
    private Button equalButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_calculator);

        operand1EditText = findViewById(R.id.operand1EditText);
        operand2EditText = findViewById(R.id.operand2EditText);
        addButton = findViewById(R.id.addButton);
        subtractButton = findViewById(R.id.subtractButton);
        multiplyButton = findViewById(R.id.multiplyButton);
        divideButton = findViewById(R.id.divideButton);
        equalButton = findViewById(R.id.equalButton);
        resultTextView = findViewById(R.id.resultTextView);

        addButton.setOnClickListener(this);
        subtractButton.setOnClickListener(this);
        multiplyButton.setOnClickListener(this);
        divideButton.setOnClickListener(this);
        equalButton.setOnClickListener(this);

        // Set OnClickListener for number buttons
        findViewById(R.id.button0).setOnClickListener(this);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Handle number button clicks
        if (v instanceof Button) {
            String number = ((Button) v).getText().toString();
            EditText activeEditText = getActiveEditText();

            if (activeEditText != null) {
                activeEditText.append(number);
            }
        } else if (v.getId() == R.id.equalButton) {
            calculateResult();
        } else {
            // Handle operator button clicks
            char operator = '+';

            switch (v.getId()) {
                case R.id.addButton:
                    operator = '+';
                    break;
                case R.id.subtractButton:
                    operator = '-';
                    break;
                case R.id.multiplyButton:
                    operator = 'x';
                    break;
                case R.id.divideButton:
                    operator = '÷';
                    break;
            }

            // Set the operator as the hint in the second operand EditText
            operand2EditText.setHint(Character.toString(operator));
        }
    }

    private EditText getActiveEditText() {
        if (operand1EditText.isFocused()) {
            return operand1EditText;
        } else if (operand2EditText.isFocused()) {
            return operand2EditText;
        }
        return null;
    }

    private void calculateResult() {
        String operand1Str = operand1EditText.getText().toString();
        String operand2Str = operand2EditText.getText().toString();

        if (operand1Str.isEmpty() || operand2Str.isEmpty()) {
            resultTextView.setText("Please enter both operands");
            return;
        }

        double operand1 = Double.parseDouble(operand1Str);
        double operand2 = Double.parseDouble(operand2Str);

        double result;
        String explanation;

        char operator = operand2EditText.getHint().charAt(0);

        switch (operator) {
            case '+':
                result = operand1 + operand2;
                explanation = operand1 + " + " + operand2 + " = " + result;
                break;
            case '-':
                result = operand1 - operand2;
                explanation = operand1 + " - " + operand2 + " = " + result;
                break;
            case 'x':
                result = operand1 * operand2;
                explanation = operand1 + " x " + operand2 + " = " + result;
                break;
            case '÷':
                if (operand2 == 0) {
                    resultTextView.setText("Cannot divide by zero");
                    return;
                }
                result = operand1 / operand2;
                explanation = operand1 + " ÷ " + operand2 + " = " + result;
                break;
            default:
                resultTextView.setText("Invalid operator");
                return;
        }

        resultTextView.setText("Result: " + result);
        // Display the explanation
        resultTextView.append("\nExplanation: " + explanation);
    }
}
