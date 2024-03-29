package com.example.ulearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class mulquizresult extends AppCompatActivity {
    private int score;
    private static final String SCORE_PREFS = "ScorePrefs";
    private static final String KEY_SCORE = "score";

    private DatabaseReference databaseRef;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mulquizresult);

        TextView mulscoreTextView = findViewById(R.id.mulscoreTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button scoreButton = findViewById(R.id.scoreButton);

        // Retrieve the score from the intent
        score = getIntent().getIntExtra("score", 0);
        mulscoreTextView.setText("Score: " + score);

        // Set the result message based on the score
        if (score >= 7) {
            resultTextView.setText("Congratulations, \nyou passed!");
        } else {
            resultTextView.setText("Sorry, you did not pass. \nTry again!");
        }

        scoreButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate to the Scoretracking activity
                startActivity(new Intent(mulquizresult.this, Scoretracking.class));
            }
        });
        username = LogIn.usernametxt;
        // Save the score in Firebase under the username
        saveScoreToFirebase(score);
    }

    private void saveScoreToFirebase(int score) {
        if (!username.isEmpty()) {
            databaseRef = FirebaseDatabase.getInstance().getReference();
            databaseRef.child("scores").child(username).child("score").child("multiplication_score").setValue(score);
        }
    }


}
