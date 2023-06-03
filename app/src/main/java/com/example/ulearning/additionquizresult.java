package com.example.ulearning;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class additionquizresult extends AppCompatActivity {
    private int score;
    private static final String SCORE_PREFS = "ScorePrefs";
    private static final String KEY_SCORE = "score";
    private DatabaseReference databaseRef;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionquizresult);

        TextView additionScoreTextView = findViewById(R.id.additionScoreTextView);
        TextView resultTextView = findViewById(R.id.resultTextView);
        Button scoreButton = findViewById(R.id.scoreButton);

        // Retrieve the score from the intent
        score = getIntent().getIntExtra("score", 0);

        // Save the score in SharedPreferences
        saveScore(score);

        additionScoreTextView.setText("Score: " + score);

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
                startActivity(new Intent(additionquizresult.this, Scoretracking.class));
            }
        });

        // Get the username from the FirebaseUser object
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            username = currentUser.getDisplayName();
        } else {
            // Handle the case when the user is not logged in
            username = "";
        }

        // Save the score in Firebase under the username
        saveScoreToFirebase(score);
    }

    private void saveScore(int score) {
        SharedPreferences sharedPreferences = getSharedPreferences(SCORE_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("addition_score", score);
        editor.apply();
    }

    private void saveScoreToFirebase(int score) {
        if (!username.isEmpty()) {
            databaseRef = FirebaseDatabase.getInstance().getReference();
            databaseRef.child("scores").child(username).child("score").child("addition_score").setValue(score);
        }
    }
}
