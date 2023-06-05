package com.example.ulearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Scoretracking extends AppCompatActivity implements View.OnClickListener {
    private int additionScore;
    private int subtractionScore;
    private int divisionScore;
    private int multiplicationScore;

    private DatabaseReference scoresRef;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    private static final String SCORE_PREFS = "ScorePrefs";

    private TextView additionScoreTextView;
    private TextView subtractionScoreTextView;
    private TextView divScoreTextView;
    private TextView mulscoreTextView;
    private Button addAgainButton;
    private Button subAgainButton;
    private Button divAgainButton;
    private Button mulAgainButton;
    private Button homeButton;

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoretracking);

        additionScoreTextView = findViewById(R.id.additionscore);
        subtractionScoreTextView = findViewById(R.id.subscore);
        divScoreTextView = findViewById(R.id.divscore);
        mulscoreTextView = findViewById(R.id.mulscore);
        addAgainButton = findViewById(R.id.addAgainButton);
        subAgainButton = findViewById(R.id.subAgainButton);
        divAgainButton = findViewById(R.id.divAgainButton);
        mulAgainButton = findViewById(R.id.mulAgainButton);
        homeButton = findViewById(R.id.homeback);

        addAgainButton.setOnClickListener(this);
        subAgainButton.setOnClickListener(this);
        divAgainButton.setOnClickListener(this);
        mulAgainButton.setOnClickListener(this);
        homeButton.setOnClickListener(this);

        additionScoreTextView.setText("Addition Quiz: " + additionScore);
        subtractionScoreTextView.setText("Subtraction Quiz: " + subtractionScore);
        divScoreTextView.setText("Division Quiz: " + divisionScore);
        mulscoreTextView.setText("Multiplication Quiz: " + multiplicationScore);
        username = LogIn.usernametxt;

        retriveScoreToFirebase();
        updateQuizResults();
    }



    private void updateQuizResults() {
        // Update Addition Quiz results
        if (additionScore < 7) {
            additionScoreTextView.append("\nI'm sorry, but you failed.");
            addAgainButton.setVisibility(View.VISIBLE);
        } else if (additionScore > 7) {
            additionScoreTextView.append("\nCongratulations, you passed!");
            addAgainButton.setVisibility(View.GONE);
        } else {
            additionScoreTextView.append("\nYou have not taken this quiz yet.");
            addAgainButton.setVisibility(View.GONE);
        }

        // Update Subtraction Quiz results
        if (subtractionScore < 7) {
            subtractionScoreTextView.append("\nI'm sorry, but you failed.");
            subAgainButton.setVisibility(View.VISIBLE);
        } else if (subtractionScore > 7) {
            subtractionScoreTextView.append("\nCongratulations, you passed!");
            subAgainButton.setVisibility(View.GONE);
        } else {
            subtractionScoreTextView.append("\nYou have not taken this quiz yet.");
            subAgainButton.setVisibility(View.GONE);
        }

        // Update Division Quiz results
        if (divisionScore < 7) {
            divScoreTextView.append("\nI'm sorry, but you failed.");
            divAgainButton.setVisibility(View.VISIBLE);
        } else if (divisionScore > 7) {
            divScoreTextView.append("\nCongratulations, you passed!");
            divAgainButton.setVisibility(View.GONE);
        } else {
            divScoreTextView.append("\nYou have not taken this quiz yet.");
            divAgainButton.setVisibility(View.GONE);
        }

        // Update Multiplication Quiz results
        if (multiplicationScore < 7) {
            mulscoreTextView.append("\nI'm sorry, but you failed.");
            mulAgainButton.setVisibility(View.VISIBLE);
        } else if (multiplicationScore > 7) {
            mulscoreTextView.append("\nCongratulations, you passed!");
            mulAgainButton.setVisibility(View.GONE);
        } else {
            mulscoreTextView.append("\nYou have not taken this quiz yet.");
            mulAgainButton.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.addAgainButton:
                intent = new Intent(Scoretracking.this, additionquiz.class);
                startActivity(intent);
                break;
            case R.id.subAgainButton:
                intent = new Intent(Scoretracking.this, subquiz.class);
                startActivity(intent);
                break;
            case R.id.divAgainButton:
                intent = new Intent(Scoretracking.this, divquiz.class);
                startActivity(intent);
                break;
            case R.id.mulAgainButton:
                intent = new Intent(Scoretracking.this, mulquiz.class);
                startActivity(intent);
                break;
            case R.id.homeback:
                intent = new Intent(Scoretracking.this, Home.class);
                startActivity(intent);
                break;
        }
    }

    public void retriveScoreToFirebase() {


        Toast.makeText(Scoretracking.this, "test", Toast.LENGTH_SHORT).show();

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        if (currentUser != null) {
            username = LogIn.usernametxt;
            scoresRef = FirebaseDatabase.getInstance().getReference().child("scores").child(username);

            scoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()) {
                        additionScore = dataSnapshot.child("addition_score").getValue(Integer.class);
                        subtractionScore = dataSnapshot.child("subtraction_score").getValue(Integer.class);
                        divisionScore = dataSnapshot.child("division_score").getValue(Integer.class);
                        multiplicationScore = dataSnapshot.child("multiplication_score").getValue(Integer.class);
                      //  scoresRef.child("scores").child(username).child("score").child("division_score").getValue(score);
                        // Update the UI on the main thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                additionScoreTextView.setText("Addition Quiz: " + additionScore);
                                subtractionScoreTextView.setText("Subtraction Quiz: " + subtractionScore);
                                divScoreTextView.setText("Division Quiz: " + divisionScore);
                                mulscoreTextView.setText("Multiplication Quiz: " + multiplicationScore);

                                updateQuizResults();
                            }
                        });
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }
}
